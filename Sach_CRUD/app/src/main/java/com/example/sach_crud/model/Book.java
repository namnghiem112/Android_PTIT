package com.example.sach_crud.model;

public class Book {
    private String tensach, tacgia, ngayxuatban, theloai;

    public Book() {
    }

    public Book(String tensach, String tacgia, String ngayxuatban, String theloai) {
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.ngayxuatban = ngayxuatban;
        this.theloai = theloai;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNgayxuatban() {
        return ngayxuatban;
    }

    public void setNgayxuatban(String ngayxuatban) {
        this.ngayxuatban = ngayxuatban;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}
