package com.example.cinema.models;

public class NhanVien {
    private String name;
    private String image;
    private String SDT;
    private String Email;
    private String Status;

    public NhanVien(String name, String image, String SDT, String email, String status) {
        this.name = name;
        this.image = image;
        this.SDT = SDT;
        Email = email;
        Status = status;
    }

    public NhanVien() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

