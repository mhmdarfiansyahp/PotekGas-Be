package com.potekgas.vo;

import com.potekgas.model.Obat;

import java.util.Base64;
import java.util.Date;

public class ObatVo {
    private int id;
    private String namaObat;
    private String merk;
    private String jenis;
    private Float harga;
    private String keterangan;
    private String status;
    private Date tgl_kadaluarsa;
    private Integer stok;
    private String gambar;

    public ObatVo() {
    }

    public ObatVo(Obat obat) {
        this.id = obat.getId_obat();
        this.namaObat = obat.getNama_obat();
        this.merk = obat.getMerk_obat();
        this.jenis = obat.getJenis_obat();
        this.harga = obat.getHarga();
        this.keterangan = obat.getKeterangan();
        this.status = obat.getStatus().toString();
        this.tgl_kadaluarsa = obat.getTgl_kadaluarsa();
        this.stok = obat.getStok();

//        if (obat.getGambarUrl() != null) {
//            this.gambarUrl = Base64.getEncoder().encodeToString(obat.getGambarUrl());
//        }

        if (obat.getGambar() != null) {
//                        this.gambar = Base64.getEncoder().encodeToString(obat.getGambar());
            this.gambar = "obats/gambarUrl/" + obat.getId_obat(); // Sesuaikan dengan URL yang digunakan untuk menyimpan gambarUrl
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Float getHarga() {
        return harga;
    }

    public void setHarga(Float harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTgl_kadaluarsa() {
        return tgl_kadaluarsa;
    }

    public void setTgl_kadaluarsa(Date tgl_kadaluarsa) {
        this.tgl_kadaluarsa = tgl_kadaluarsa;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
