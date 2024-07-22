package com.example.btlandroid.model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private int id, soluong;
    private String tensinhvien, email, sodienthoai, ngaysinh, quequan ,kythuctap;

    public SinhVien(int id, String tensinhvien, String email, String sodienthoai, String ngaysinh, String quequan, String kythuctap) {
        this.id = id;
        this.tensinhvien = tensinhvien;
        this.email = email;
        this.sodienthoai = sodienthoai;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.kythuctap = kythuctap;
    }

    public SinhVien(String tensinhvien, String email, String sodienthoai, String ngaysinh, String quequan, String kythuctap) {
        this.tensinhvien = tensinhvien;
        this.email = email;
        this.sodienthoai = sodienthoai;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.kythuctap = kythuctap;
    }

    public SinhVien(int soluong, String kythuctap) {
        this.soluong = soluong;
        this.kythuctap = kythuctap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTensinhvien() {
        return tensinhvien;
    }

    public void setTensinhvien(String tensinhvien) {
        this.tensinhvien = tensinhvien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getKythuctap() {
        return kythuctap;
    }

    public void setKythuctap(String kythuctap) {
        this.kythuctap = kythuctap;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "id=" + id +
                ", soluong=" + soluong +
                ", tensinhvien='" + tensinhvien + '\'' +
                ", email='" + email + '\'' +
                ", sodienthoai='" + sodienthoai + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", quequan='" + quequan + '\'' +
                ", kythuctap='" + kythuctap + '\'' +
                '}';
    }
}
