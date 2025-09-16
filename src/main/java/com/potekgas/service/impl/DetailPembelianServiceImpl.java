package com.potekgas.service.impl;

import com.potekgas.dao.DetailPembelianDao;
import com.potekgas.model.DetailPembelian;
import com.potekgas.model.DetailPembelianPK;
import com.potekgas.model.Obat;
import com.potekgas.repository.DetailPembelianRepository;
import com.potekgas.repository.ObatRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.DetailPembelianService;
import com.potekgas.vo.DetailPembelianVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.potekgas.constant.DetailPembelianConstant.*;

@Service
@Transactional
public class DetailPembelianServiceImpl implements DetailPembelianService {
    @Autowired
    DetailPembelianDao detailPembelianDao;
    @Autowired
    DetailPembelianRepository detailPembelianRepository;
    @Autowired
    ObatRepository obatRepository;

    @Override
    public DtoResponse getAllDetailPembelian() {
        if (detailPembelianDao.getAllDetailPembelian() != null)
            return new DtoResponse(200, detailPembelianDao.getAllDetailPembelian());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse findDetailPembelianByTrsId(int id) {
        if (detailPembelianDao.findDetailPembelianByTrsId(id) != null)
            return new DtoResponse(200, detailPembelianDao.findDetailPembelianByTrsId(id));
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse saveDetailPembelian(List<DetailPembelianVo> detailPembelianList) {
        try {
            for (DetailPembelianVo detailPembelianVo : detailPembelianList) {
                DetailPembelianPK detailPembelianPK = new DetailPembelianPK();
                detailPembelianPK.setId_detail(detailPembelianVo.getIdDetail());
                detailPembelianPK.setId_transaksi(detailPembelianVo.getIdTransaksi());
                detailPembelianPK.setId_obat(detailPembelianVo.getIdObat());

//                List<DetailPembelianVo> listData = detailPembelianDao.getAllDetailPembelian();
//                if (listData.isEmpty()) {
//                    detailPembelianPK.setId_detail(1);
//                } else {
//                    DetailPembelianVo lastData = listData.get(listData.size() - 1);
//                    detailPembelianPK.setId_detail(lastData.getIdDetail() + 1);
//                }
                // Mendapatkan data obat dari repository
                Obat existingObat = obatRepository.findById(detailPembelianVo.getIdObat()).orElse(null);
                if (existingObat == null) {
                    return new DtoResponse(404, null, mEmptyObat);
                }

                // Validasi jika stok obat kurang dari jumlah yang akan dibeli
                if (existingObat.getStok() < detailPembelianVo.getJumlah()) {
                    return new DtoResponse(400, null, "Stok obat tidak mencukupi untuk pembelian ini");
                }

                // Mengurangi stok obat
                existingObat.setStok(existingObat.getStok() - detailPembelianVo.getJumlah());
                obatRepository.save(existingObat);

                ArrayList<Integer> findLastIdDetail = detailPembelianDao.findLastIdDetail();
                System.out.println("id : "+findLastIdDetail.get(0));
                if (findLastIdDetail.get(0) == null) {
                    detailPembelianPK.setId_detail(1);
                } else {
                    detailPembelianPK.setId_detail(findLastIdDetail.get(0) + 1);
                }


                DetailPembelian detailPembelian = new DetailPembelian();
                detailPembelian.setDetailPembelianPK(detailPembelianPK);
                detailPembelian.setJumlah(detailPembelianVo.getJumlah());

                detailPembelianRepository.save(detailPembelian);
            }
            return new DtoResponse(200, detailPembelianList, mCreateSuccess);
        } catch (Exception e) {
            return new DtoResponse(500, null, mCreateFailed + e.getMessage());
        }
    }
}
