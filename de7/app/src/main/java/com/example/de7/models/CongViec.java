package com.example.de7.models;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id, soluong;
    private String ten, ngaybatdau, chuyennganh, kichhoat, hocphi;

    public CongViec() {
    }

    public CongViec(int id, String ten, String ngaybatdau, String chuyennganh, String kichhoat, String hocphi) {
        this.id = id;
        this.ten = ten;
        this.ngaybatdau = ngaybatdau;
        this.chuyennganh = chuyennganh;
        this.kichhoat = kichhoat;
        this.hocphi = hocphi;
    }

    public CongViec(String ten, String ngaybatdau, String chuyennganh, String kichhoat, String hocphi) {
        this.ten = ten;
        this.ngaybatdau = ngaybatdau;
        this.chuyennganh = chuyennganh;
        this.kichhoat = kichhoat;
        this.hocphi = hocphi;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getChuyennganh() {
        return chuyennganh;
    }

    public void setChuyennganh(String chuyennganh) {
        this.chuyennganh = chuyennganh;
    }

    public String getKichhoat() {
        return kichhoat;
    }

    public void setKichhoat(String kichhoat) {
        this.kichhoat = kichhoat;
    }

    public String getHocphi() {
        return hocphi;
    }

    public void setHocphi(String hocphi) {
        this.hocphi = hocphi;
    }

    @Override
    public String toString() {
        return "CongViec{" +
                "id=" + id +
                ", soluong=" + soluong +
                ", ten='" + ten + '\'' +
                ", ngaybatdau='" + ngaybatdau + '\'' +
                ", chuyennganh='" + chuyennganh + '\'' +
                ", kichhoat='" + kichhoat + '\'' +
                ", hocphi='" + hocphi + '\'' +
                '}';
    }
}
