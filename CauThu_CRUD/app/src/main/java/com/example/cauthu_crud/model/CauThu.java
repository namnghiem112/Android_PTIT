package com.example.cauthu_crud.model;

public class CauThu {
    public static String MALE ="male", FEMALE ="female";
    private String tencauthu, ngaysinh, gioitinh, vitrida;

    public CauThu() {
    }

    public CauThu(String tencauthu, String ngaysinh, String gioitinh, String vitrida) {
        this.tencauthu = tencauthu;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.vitrida = vitrida;
    }

    public String getTencauthu() {
        return tencauthu;
    }

    public void setTencauthu(String tencauthu) {
        this.tencauthu = tencauthu;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getVitrida() {
        return vitrida;
    }

    public void setVitrida(String vitrida) {
        this.vitrida = vitrida;
    }

    @Override
    public String toString() {
        return "CauThu{" +
                "tencauthu='" + tencauthu + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", vitrida='" + vitrida + '\'' +
                '}';
    }
}
