package com.potekgas.dao;

import com.potekgas.model.DetailPembelian;
import com.potekgas.vo.DetailPembelianVo;
import com.potekgas.vo.PembelianVo;

import java.util.ArrayList;
import java.util.List;

public interface DetailPembelianDao {
    List<DetailPembelianVo> getAllDetailPembelian();
    List<DetailPembelianVo> findDetailPembelianByTrsId(int id);
    ArrayList findLastIdDetail();
}
