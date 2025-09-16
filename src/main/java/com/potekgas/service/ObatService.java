package com.potekgas.service;

import com.potekgas.model.Obat;
import com.potekgas.response.DtoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface ObatService {
    DtoResponse getAllObat();
    DtoResponse getObatActive();
    DtoResponse countObat();
    DtoResponse getObatById(int id);
    DtoResponse saveObat(String namaObat, String merkObat, String jenisObat, Date tglKadaluarsa, Float harga, Integer stok, String keterangan, Integer status, MultipartFile gambar);
    DtoResponse updateObat(Integer idObat, String namaObat, String merkObat, String jenisObat, Date tglKadaluarsa, Float harga, Integer stok, String keterangan, Integer status, MultipartFile gambar);
    DtoResponse deleteObat(Obat obat);
    public byte[] getGambarById(int id);
    DtoResponse kurangiStokObat(int idObat, int jumlahPemakaian);
}
