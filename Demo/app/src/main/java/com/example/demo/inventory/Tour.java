package com.example.demo.inventory;

public class Tour {
    public static String XEMAY ="xemay", OTO = "oto", MAYBAY="maybay";
    private String lichtrinh, thoigian;
    private String phgtien;

    public Tour() {
    }

    public Tour(String lichtrinh, String thoigian, String phgtien) {
        this.lichtrinh = lichtrinh;
        this.thoigian = thoigian;
        this.phgtien = phgtien;
    }

    public String getLichtrinh() {
        return lichtrinh;
    }

    public void setLichtrinh(String lichtrinh) {
        this.lichtrinh = lichtrinh;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getPhgtien() {
        return phgtien;
    }

    public void setPhgtien(String phgtien) {
        this.phgtien = phgtien;
    }
}
