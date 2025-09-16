package com.potekgas.vo;

import com.potekgas.model.DetailPembelian;

public class DetailObatVo {
    private Integer idDetail; // tambahkan kembali properti idDetail
    private Integer idObat;
    private String namaObat;
    private String deskripsiObat;
    private Float hargaObat;
    private Integer jumlah;

    public DetailObatVo() {
    }

    public DetailObatVo(DetailPembelian detailPembelian) {
        this.idDetail = detailPembelian.getDetailPembelianPK().getId_detail(); // tambahkan kembali inisialisasi idDetail
        this.idObat = detailPembelian.getDetailPembelianPK().getId_obat();
        this.jumlah = detailPembelian.getJumlah();
    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public Integer getIdObat() {
        return idObat;
    }

    public void setIdObat(Integer idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getDeskripsiObat() {
        return deskripsiObat;
    }

    public void setDeskripsiObat(String deskripsiObat) {
        this.deskripsiObat = deskripsiObat;
    }

    public Float getHargaObat() {
        return hargaObat;
    }

    public void setHargaObat(Float hargaObat) {
        this.hargaObat = hargaObat;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}