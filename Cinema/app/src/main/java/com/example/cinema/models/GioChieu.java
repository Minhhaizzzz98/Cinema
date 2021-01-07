package com.example.cinema.models;

public class GioChieu {
    private int id;
    private String thoigian;

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
