package com.example.cinema.models;

public class Ghe {
    private int id;

    public Ghe(int id, String day, String sort) {
        this.id = id;
        this.day = day;
        this.sort = sort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String day;
    private String sort;

    public Ghe() {
    }

    public Ghe(String day, String sort) {
        this.day = day;
        this.sort = sort;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
