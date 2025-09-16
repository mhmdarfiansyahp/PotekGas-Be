package com.potekgas.dao.impl;

import com.potekgas.dao.PembelianDao;
import com.potekgas.model.*;
import com.potekgas.repository.DetailPembelianRepository;
import com.potekgas.repository.ObatRepository;
import com.potekgas.repository.PembelianRepository;
import com.potekgas.repository.UserRepository;
import com.potekgas.vo.DetailPembelianVo;
import com.potekgas.vo.PembelianVo;
import com.potekgas.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PembelianDaoImpl implements PembelianDao {
    @Autowired
    private PembelianRepository pembelianRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DetailPembelianRepository detailPembelianRepository;
    @Autowired
    private ObatRepository obatRepository;

    @Override
    public List<PembelianVo> getAllPembelian() {
        Iterable<Pembelian> pembelians = pembelianRepository.findAll();
        List<PembelianVo> pembelianVos = new ArrayList<>();

        for (Pembelian item : pembelians) {
            PembelianVo pembelianVo = new PembelianVo(item);

            User user = userRepository.findById(pembelianVo.getIdUser()).orElse(null);
            if (user != null) {
                pembelianVo.setNamaUser(user.getNama_user());
            }

            pembelianVos.add(pembelianVo);
        }
        return pembelianVos;
    }

    @Override
    public List<Pembelian> findPembelianByTrsId(int id) {
        return pembelianRepository.findPembelianByTrsId(id);
    }

    @Override
    public ArrayList countPembelian() {
        return pembelianRepository.countPembelian();
    }

    @Override
    public ArrayList getBestSellerObat() {
        return pembelianRepository.getBestSellerObat();
    }
}
