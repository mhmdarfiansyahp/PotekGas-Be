package com.potekgas.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tr_detail_pembelian")
public class DetailPembelian implements Serializable {
    @EmbeddedId
    private DetailPembelianPK detailPembelianPK;
    @Column(name = "jumlah")
    private Integer jumlah;

    public DetailPembelian() {
    }

    public DetailPembelian(DetailPembelianPK detailPembelianPK, Integer jumlah) {
        this.detailPembelianPK = detailPembelianPK;
        this.jumlah = jumlah;
    }

    public DetailPembelianPK getDetailPembelianPK() {
        return detailPembelianPK;
    }

    public void setDetailPembelianPK(DetailPembelianPK detailPembelianPK) {
        this.detailPembelianPK = detailPembelianPK;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
