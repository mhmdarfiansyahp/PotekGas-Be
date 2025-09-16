package com.potekgas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ms_obat")
//@Data
//@Builder
public class Obat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obat")
    private Integer id_obat;
    @Column(name = "nama_obat")
    private String nama_obat;
    @Column(name = "merk_obat")
    private String merk_obat;
    @Column(name = "jenis_obat")
    private String jenis_obat;
    @Column(name = "tgl_kadaluarsa")
    private Date tgl_kadaluarsa;
    @Column(name = "harga")
    private Float harga;
    @Column(name = "stok")
    private Integer stok;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "status")
    private Integer status;
    @Lob
    @Column(name = "gambar", length = Integer.MAX_VALUE, nullable = true)
    private byte[] gambar;

    public Obat() {
    }

    public Obat(Integer id_obat, String nama_obat, String merk_obat, String jenis_obat, Date tgl_kadaluarsa, Float harga, Integer stok, String keterangan, Integer status) {
        this.id_obat = id_obat;
        this.nama_obat = nama_obat;
        this.merk_obat = merk_obat;
        this.jenis_obat = jenis_obat;
        this.tgl_kadaluarsa = tgl_kadaluarsa;
        this.harga = harga;
        this.stok = stok;
        this.keterangan = keterangan;
        this.status = status;
    }

    public Obat(String nama_obat, String merk_obat, String jenis_obat, Date tgl_kadaluarsa, Float harga, Integer stok, String keterangan, Integer status, byte[] gambar) {
        this.nama_obat = nama_obat;
        this.merk_obat = merk_obat;
        this.jenis_obat = jenis_obat;
        this.tgl_kadaluarsa = tgl_kadaluarsa;
        this.harga = harga;
        this.stok = stok;
        this.keterangan = keterangan;
        this.status = status;
        this.gambar = gambar;
    }

    public Integer getId_obat() {
        return id_obat;
    }

    public void setId_obat(Integer id_obat) {
        this.id_obat = id_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public String getMerk_obat() {
        return merk_obat;
    }

    public void setMerk_obat(String merk_obat) {
        this.merk_obat = merk_obat;
    }

    public String getJenis_obat() {
        return jenis_obat;
    }

    public void setJenis_obat(String jenis_obat) {
        this.jenis_obat = jenis_obat;
    }

    public Date getTgl_kadaluarsa() {
        return tgl_kadaluarsa;
    }

    public void setTgl_kadaluarsa(Date tgl_kadaluarsa) {
        this.tgl_kadaluarsa = tgl_kadaluarsa;
    }

    public Float getHarga() {
        return harga;
    }

    public void setHarga(Float harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }
}
