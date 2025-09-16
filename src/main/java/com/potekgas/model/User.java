package com.potekgas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ms_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id_user;
    @Column(name = "nama_user")
    private String nama_user;
    @Column(name = "no_telp")
    private String no_telp;
    @Column(name = "role")
    private Integer role;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Integer status;
    @Lob
    @Column(name = "foto", length = Integer.MAX_VALUE, nullable = true)
    private byte[] foto;

    public User() {
    }

    public User(Integer id_user, String nama_user, String no_telp, Integer role, String username, String password, Integer status) {
        this.id_user = id_user;
        this.nama_user = nama_user;
        this.no_telp = no_telp;
        this.role = role;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public User(Integer id_user, String nama_user, String no_telp, Integer role, String username, String password, Integer status, byte[] foto) {
        this.id_user = id_user;
        this.nama_user = nama_user;
        this.no_telp = no_telp;
        this.role = role;
        this.username = username;
        this.password = password;
        this.status = status;
        this.foto = foto;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
