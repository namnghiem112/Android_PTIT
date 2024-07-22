package com.example.crud_android.model;

public class Employee {
    public static String MALE ="male", FEMALE ="female";
    private String gioitinh;
    private String manv, tennv;

    public Employee() {
    }

    public Employee(String manv, String tennv, String gioitinh) {
        this.gioitinh = gioitinh;
        this.manv = manv;
        this.tennv = tennv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }
}
