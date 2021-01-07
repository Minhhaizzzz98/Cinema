package com.example.cinema.models;

public class Ve {
    private int id;
    private  int khach_hang_id;
    private int suat_chieu_id;
    private int  ghe_id;
    private  double gia_ve;
    private int trang_thai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKhach_hang_id() {
        return khach_hang_id;
    }

    public void setKhach_hang_id(int khach_hang_id) {
        this.khach_hang_id = khach_hang_id;
    }

    public int getSuat_chieu_id() {
        return suat_chieu_id;
    }

    public void setSuat_chieu_id(int suat_chieu_id) {
        this.suat_chieu_id = suat_chieu_id;
    }

    public int getGhe_id() {
        return ghe_id;
    }

    public void setGhe_id(int ghe_id) {
        this.ghe_id = ghe_id;
    }

    public double getGia_ve() {
        return gia_ve;
    }

    public void setGia_ve(double gia_ve) {
        this.gia_ve = gia_ve;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

    public Ve(int id, int khach_hang_id, int suat_chieu_id, int ghe_id, double gia_ve, int trang_thai) {
        this.id = id;
        this.khach_hang_id = khach_hang_id;
        this.suat_chieu_id = suat_chieu_id;
        this.ghe_id = ghe_id;
        this.gia_ve = gia_ve;
        this.trang_thai = trang_thai;
    }
}
