package com.potekgas.vo;

import com.potekgas.model.DetailPembelian;
import com.potekgas.model.Pembelian;

import java.util.Date;

public class DetailPembelianVo {
    private Integer idTransaksi;
    private Integer idDetail;
    private Integer idObat;
    private String namaObat;
    private String deskripsiObat;
    private Float hargaObat;
    private Integer jumlah;

    public DetailPembelianVo() {
    }

    public DetailPembelianVo(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public DetailPembelianVo(Integer idTransaksi, Integer idDetail, Integer idObat, String namaObat, String deskripsiObat, Float hargaObat, Integer jumlah) {
        this.idTransaksi = idTransaksi;
        this.idDetail = idDetail;
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.deskripsiObat = deskripsiObat;
        this.hargaObat = hargaObat;
        this.jumlah = jumlah;
    }

    public DetailPembelianVo(DetailPembelian detailPembelian) {
        this.idDetail = detailPembelian.getDetailPembelianPK().getId_detail();
        this.idTransaksi = detailPembelian.getDetailPembelianPK().getId_transaksi();
        this.idObat = detailPembelian.getDetailPembelianPK().getId_obat();
        this.jumlah = detailPembelian.getJumlah();
    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
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
