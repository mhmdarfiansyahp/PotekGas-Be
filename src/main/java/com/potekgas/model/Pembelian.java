package com.potekgas.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tr_pembelian")
public class Pembelian implements Serializable {
    @EmbeddedId
    private PembelianPK pembelianPK;
    @Column(name = "tgl_transaksi")
    private Date tgl_transaksi;
    @Column(name = "total_harga")
    private Double total_harga;

    public Pembelian() {
    }

    public Pembelian(PembelianPK pembelianPK, Date tgl_transaksi, Double total_harga) {
        this.pembelianPK = pembelianPK;
        this.tgl_transaksi = tgl_transaksi;
        this.total_harga = total_harga;
    }

    public PembelianPK getPembelianPK() {
        return pembelianPK;
    }

    public void setPembelianPK(PembelianPK pembelianPK) {
        this.pembelianPK = pembelianPK;
    }

    public Date getTgl_transaksi() {
        return tgl_transaksi;
    }

    public void setTgl_transaksi(Date tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
    }

    public Double getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Double total_harga) {
        this.total_harga = total_harga;
    }
}
