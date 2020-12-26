package com.example.cinema.models;

public class Lich {
    private String thu;
    private String ngay;

    public Lich() {
    }

    public Lich(String thu, String ngay) {
        this.thu = thu;
        this.ngay = ngay;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
