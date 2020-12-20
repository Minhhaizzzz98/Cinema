package com.example.cinema.models;

public class GiaoDich {
    String TenPhim, NgayMua;
    int SoTien;

    public GiaoDich(String tenPhim, String ngayMua, int soTien) {
        TenPhim = tenPhim;
        NgayMua = ngayMua;
        SoTien = soTien;
    }

    public GiaoDich() {
    }

    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String tenPhim) {
        TenPhim = tenPhim;
    }

    public String getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(String ngayMua) {
        NgayMua = ngayMua;
    }

    public int getSoTien() {
        return SoTien;
    }

    public void setSoTien(int soTien) {
        SoTien = soTien;
    }
}
