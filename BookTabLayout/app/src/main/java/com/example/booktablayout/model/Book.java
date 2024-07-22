package com.example.booktablayout.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String ten,cautruc,ngayxuathien,vacxin,soluongVN, soluongTG;

    public Book() {
    }

    public Book(int id, String ten, String cautruc, String ngayxuathien, String vacxin, String soluongVN, String soluongTG) {
        this.id = id;
        this.ten = ten;
        this.cautruc = cautruc;
        this.ngayxuathien = ngayxuathien;
        this.vacxin = vacxin;
        this.soluongVN = soluongVN;
        this.soluongTG = soluongTG;
    }

    public Book(String ten, String cautruc, String ngayxuathien, String vacxin, String soluongVN, String soluongTG) {
        this.ten = ten;
        this.cautruc = cautruc;
        this.ngayxuathien = ngayxuathien;
        this.vacxin = vacxin;
        this.soluongVN = soluongTG;
        this.soluongTG = soluongTG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCautruc() {
        return cautruc;
    }

    public void setCautruc(String cautruc) {
        this.cautruc = cautruc;
    }

    public String getNgayxuathien() {
        return ngayxuathien;
    }

    public void setNgayxuathien(String ngayxuathien) {
        this.ngayxuathien = ngayxuathien;
    }

    public String getVacxin() {
        return vacxin;
    }

    public void setVacxin(String vacxin) {
        this.vacxin = vacxin;
    }

    public String getSoluongVN() {
        return soluongVN;
    }

    public void setSoluongVN(String soluongVN) {
        this.soluongVN = soluongVN;
    }

    public String getSoluongTG() {
        return soluongTG;
    }

    public void setSoluongTG(String soluongTG) {
        this.soluongTG = soluongTG;
    }
}
