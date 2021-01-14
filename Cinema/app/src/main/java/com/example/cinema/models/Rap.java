package com.example.cinema.models;

public class Rap {
    int id;
    int img;
    String TenRap;
    String DiaChi;
    String SDT;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rap(int id, String tenRap) {
        this.id = id;
        TenRap = tenRap;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return TenRap;
    }

    public void setName(String name) {
        this.TenRap = name;
    }

    public String getAddress() {
        return DiaChi;
    }

    public void setAddress(String address) {
        this.DiaChi = address;
    }

    public String getPhone() {
        return SDT;
    }

    public void setPhone(String phone) {
        this.SDT = phone;
    }

    public Rap() {
    }

    public Rap(String name, String address, String phone) {

        this.TenRap = name;
        this.DiaChi = address;
        this.SDT = phone;
    }
}
