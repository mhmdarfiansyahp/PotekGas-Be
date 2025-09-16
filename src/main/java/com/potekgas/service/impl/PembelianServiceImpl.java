package com.potekgas.service.impl;

import com.potekgas.dao.PembelianDao;
import com.potekgas.model.Pembelian;
import com.potekgas.model.PembelianPK;
import com.potekgas.model.User;
import com.potekgas.repository.PembelianRepository;
import com.potekgas.repository.UserRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.PembelianService;
import com.potekgas.vo.PembelianVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.potekgas.constant.PembelianConstant.*;
import static com.potekgas.constant.UserConstant.mEmptyData;

@Service
@Transactional
public class PembelianServiceImpl implements PembelianService {
    @Autowired
    PembelianDao pembelianDao;
    @Autowired
    PembelianRepository pembelianRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public DtoResponse getAllPembelian() {
        List response = new ArrayList();
        if (pembelianDao.getAllPembelian() != null) {
            return new DtoResponse(200, pembelianDao.getAllPembelian());
        }
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse savePembelian(PembelianVo pembelianVo) {
        try {
            PembelianPK pembelianPK = new PembelianPK();
            pembelianPK.setId_transaksi(pembelianVo.getIdTransaksi());
            pembelianPK.setId_user(pembelianVo.getIdUser());

            List<PembelianVo> listData = pembelianDao.getAllPembelian();
            if (listData.isEmpty()) {
                pembelianPK.setId_transaksi(1);
            } else {
                PembelianVo lastData = listData.get(listData.size() - 1);
                pembelianPK.setId_transaksi(lastData.getIdTransaksi() + 1);
            }

            User existingUser = userRepository.findById(pembelianVo.getIdUser()).orElse(null);
            if (existingUser == null) {
                return new DtoResponse(404, null, mEmptyUser);
            }

            Date today = new Date();
            Pembelian pembelian = new Pembelian();
            pembelian.setPembelianPK(pembelianPK);
            pembelian.setTgl_transaksi(today);
            pembelian.setTotal_harga(pembelianVo.getTotalHarga());

            pembelianRepository.save(pembelian);
            return new DtoResponse(200, pembelian, mCreateSuccess);
        } catch (Exception e) {
            return new DtoResponse(500, pembelianVo, mCreateFailed);
        }
    }

    @Override
    public DtoResponse findPembelianByTrsId(int id) {
        if (pembelianDao.findPembelianByTrsId(id) != null) {
            return new DtoResponse(200, pembelianDao.findPembelianByTrsId(id));
        }
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse countPembelian() {
        return new DtoResponse(200, pembelianDao.countPembelian());
    }

    @Override
    public DtoResponse getBestSellerObat() {
        return new DtoResponse(200, pembelianDao.getBestSellerObat());
    }
}
