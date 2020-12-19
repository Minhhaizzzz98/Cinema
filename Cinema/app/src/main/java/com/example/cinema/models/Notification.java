package com.example.cinema.models;

public class Notification {
    int img;
    String title;
    String content;
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Notification(int img, String title, String content, int status) {
        this.img = img;
        this.status = status;
        this.title = title;
        this.content = content;
    }

    public Notification() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
