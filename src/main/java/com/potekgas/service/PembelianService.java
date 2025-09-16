package com.potekgas.service;

import com.potekgas.response.DtoResponse;
import com.potekgas.vo.PembelianVo;

public interface PembelianService {
    DtoResponse getAllPembelian();
    DtoResponse savePembelian(PembelianVo pembelianVo);
    DtoResponse findPembelianByTrsId(int id);
    DtoResponse countPembelian();
    DtoResponse getBestSellerObat();
}
