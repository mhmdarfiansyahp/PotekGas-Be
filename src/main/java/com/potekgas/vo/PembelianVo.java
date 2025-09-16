package com.potekgas.vo;

import com.potekgas.model.DetailPembelian;
import com.potekgas.model.Pembelian;

import java.util.Date;
import java.util.Optional;

public class PembelianVo {
    private Integer idTransaksi;
    private Integer idUser;
    private String namaUser;
    private Date tglPembelian;
    private Double totalHarga;

    public PembelianVo() {
    }

    public PembelianVo(Pembelian pembelian) {
        this.idTransaksi = pembelian.getPembelianPK().getId_transaksi();
        this.idUser = pembelian.getPembelianPK().getId_user();
        this.tglPembelian = pembelian.getTgl_transaksi();
        this.totalHarga = pembelian.getTotal_harga();
    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public Date getTglPembelian() {
        return tglPembelian;
    }

    public void setTglPembelian(Date tglPembelian) {
        this.tglPembelian = tglPembelian;
    }

    public Double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Double totalHarga) {
        this.totalHarga = totalHarga;
    }
}
