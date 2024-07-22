package com.example.kiemtra.model;

public class Bao {
    private String tenbaibao, tentacgia, gioduatin, kieubai;

    public Bao() {
    }

    public Bao(String tenbaibao, String tentacgia, String gioduatin, String kieubai) {
        this.tenbaibao = tenbaibao;
        this.tentacgia = tentacgia;
        this.gioduatin = gioduatin;
        this.kieubai = kieubai;
    }

    public String getTenbaibao() {
        return tenbaibao;
    }

    public void setTenbaibao(String tenbaibao) {
        this.tenbaibao = tenbaibao;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public String getGioduatin() {
        return gioduatin;
    }

    public void setGioduatin(String gioduatin) {
        this.gioduatin = gioduatin;
    }

    public String getKieubai() {
        return kieubai;
    }

    public void setKieubai(String kieubai) {
        this.kieubai = kieubai;
    }

    @Override
    public String toString() {
        return "Bao{" +
                "tenbaibao='" + tenbaibao + '\'' +
                ", tentacgia='" + tentacgia + '\'' +
                ", gioduatin='" + gioduatin + '\'' +
                ", kieubai='" + kieubai + '\'' +
                '}';
    }
}
