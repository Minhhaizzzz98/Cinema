package com.example.cinema.models;

public class Actor {
    int img;
    String content;
    String name;

    public Actor() {
    }

    public Actor(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public Actor(int img, String content, String name) {
        this.img = img;
        this.content = content;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
