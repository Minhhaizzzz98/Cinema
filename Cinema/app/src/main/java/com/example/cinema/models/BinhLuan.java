package com.example.cinema.models;

public class BinhLuan {
    private int id;
    private String noidung;
    private String tenkhachhang;
    private int trangthai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public BinhLuan() {
    }

    public BinhLuan(int id, String noidung, String tenkhachhang, int trangthai) {
        this.id = id;
        this.noidung = noidung;
        this.tenkhachhang = tenkhachhang;
        this.trangthai = trangthai;
    }
}
