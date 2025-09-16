package com.potekgas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DetailPembelianPK implements Serializable {
    @Column(name = "id_detail")
    private Integer id_detail;
    @Column(name = "id_transaksi")
    private Integer id_transaksi;
    @Column(name = "id_obat")
    private Integer id_obat;

    public DetailPembelianPK() {
    }

    public DetailPembelianPK(Integer id_detail, Integer id_transaksi, Integer id_obat) {
        this.id_detail = id_detail;
        this.id_transaksi = id_transaksi;
        this.id_obat = id_obat;
    }

    public Integer getId_detail() {
        return id_detail;
    }

    public void setId_detail(Integer id_detail) {
        this.id_detail = id_detail;
    }

    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Integer getId_obat() {
        return id_obat;
    }

    public void setId_obat(Integer id_obat) {
        this.id_obat = id_obat;
    }
}
