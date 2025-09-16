package com.potekgas.dao;

import com.potekgas.model.Pembelian;
import com.potekgas.vo.PembelianVo;

import java.util.ArrayList;
import java.util.List;

public interface PembelianDao {
    List<PembelianVo> getAllPembelian();
    List<Pembelian> findPembelianByTrsId(int id);
    ArrayList countPembelian();
    ArrayList getBestSellerObat();
}
