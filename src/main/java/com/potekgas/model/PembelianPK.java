package com.potekgas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PembelianPK implements Serializable {
    @Column(name = "id_transaksi")
    private Integer id_transaksi;
    @Column(name = "id_user")
    private Integer id_user;

    public PembelianPK() {
    }

    public PembelianPK(Integer id_transaksi, Integer id_user) {
        this.id_transaksi = id_transaksi;
        this.id_user = id_user;
    }

    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }
}
