package com.example.cinema.models;

public class GioChieu {
    private int id_suat;
    private int giaSuatChieu;
    private int id;
    private String thoigian;
    private int id_phong;

    public GioChieu(int id_suat, int id, String thoigian, int giaSuatChieu, int id_phong) {
        this.id_suat = id_suat;
        this.id = id;
        this.thoigian = thoigian;
        this.giaSuatChieu= giaSuatChieu;
        this.id_phong= id_phong;
    }

    public int getGiaSuatChieu() {
        return giaSuatChieu;
    }

    public void setGiaSuatChieu(int giaSuatChieu) {
        this.giaSuatChieu = giaSuatChieu;
    }

    public int getId_suat() {
        return id_suat;
    }

    public void setId_suat(int id_suat) {
        this.id_suat = id_suat;
    }



    public GioChieu() {
    }

    public GioChieu(int id_suat, int id, String thoigian, int id_phong) {
        this.id_suat = id_suat;
        this.id = id;
        this.thoigian = thoigian;
        this.id_phong = id_phong;
    }

    public int getId_phong() {
        return id_phong;
    }

    public void setId_phong(int id_phong) {
        this.id_phong = id_phong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public GioChieu(int id, String thoigian) {
        this.id = id;
        this.thoigian = thoigian;
    }
}
