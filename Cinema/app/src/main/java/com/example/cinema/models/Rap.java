package com.example.cinema.models;

public class Rap {
    int img;
    String name;
    String address;
    String phone;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Rap() {
    }

    public Rap(int img, String name, String address, String phone) {
        this.img = img;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
