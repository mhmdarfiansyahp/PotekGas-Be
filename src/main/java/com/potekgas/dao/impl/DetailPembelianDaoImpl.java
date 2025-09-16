package com.potekgas.dao.impl;

import com.potekgas.dao.DetailPembelianDao;
import com.potekgas.model.DetailPembelian;
import com.potekgas.model.Obat;
import com.potekgas.model.Pembelian;
import com.potekgas.model.User;
import com.potekgas.repository.DetailPembelianRepository;
import com.potekgas.repository.ObatRepository;
import com.potekgas.repository.PembelianRepository;
import com.potekgas.repository.UserRepository;
import com.potekgas.vo.DetailObatVo;
import com.potekgas.vo.DetailPembelianVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DetailPembelianDaoImpl implements DetailPembelianDao {
    @Autowired
    private DetailPembelianRepository detailPembelianRepository;
    @Autowired
    private ObatRepository obatRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DetailPembelianVo> getAllDetailPembelian() {
        Iterable<DetailPembelian> detailPembelians = detailPembelianRepository.findAll();
        List<DetailPembelianVo> detailPembelianVos = new ArrayList<>();

        for (DetailPembelian item : detailPembelians){
            DetailPembelianVo detailPembelianVo = new DetailPembelianVo(item);

            Obat obat = obatRepository.findById(detailPembelianVo.getIdObat()).orElse(null);
            if (obat != null) {
               detailPembelianVo.setNamaObat(obat.getNama_obat());
               detailPembelianVo.setDeskripsiObat(obat.getKeterangan());
               detailPembelianVo.setHargaObat(obat.getHarga());
            }

//            User user = userRepository.findById(detailPembelianVo.getDetailObat().getIdObat()).orElse(null);
//            if (user != null) {
//                detailPembelianVo.setIdUser(user.getId_user());
//                detailPembelianVo.setIdUser(user.getId_user());
//            }

            detailPembelianVos.add(detailPembelianVo);
        }
        return detailPembelianVos;
    }

    @Override
    public List<DetailPembelianVo> findDetailPembelianByTrsId(int id) {
//        return detailPembelianRepository.findDetailPembelianByTrsId(id);
        Iterable<DetailPembelian> detailPembelians = detailPembelianRepository.findDetailPembelianByTrsId(id);
        List<DetailPembelianVo> detailPembelianVos = new ArrayList<>();

        for (DetailPembelian item : detailPembelians){
            DetailPembelianVo detailPembelianVo = new DetailPembelianVo(item);

            Obat obat = obatRepository.findById(detailPembelianVo.getIdObat()).orElse(null);
            if (obat != null) {
                detailPembelianVo.setNamaObat(obat.getNama_obat());
                detailPembelianVo.setDeskripsiObat(obat.getKeterangan());
                detailPembelianVo.setHargaObat(obat.getHarga());
            }

//            User user = userRepository.findById(detailPembelianVo.getDetailObat().getIdObat()).orElse(null);
//            if (user != null) {
//                detailPembelianVo.setIdUser(user.getId_user());
//                detailPembelianVo.setIdUser(user.getId_user());
//            }

            detailPembelianVos.add(detailPembelianVo);
        }
        return detailPembelianVos;
    }

    @Override
    public ArrayList findLastIdDetail() {
        return detailPembelianRepository.findLastIdDetail();
    }
}
