package com.example.cinema.models;

public class Movie {

    private String title;
    private String description;
    private int thumbnail;
    private String studio;
    private String rating;
    private String streamingLink;
<<<<<<< HEAD
    private int coverPhoto;

=======
    private String loaiPhim;

    public String getLoaiPhim() {
        return loaiPhim;
    }

    public void setLoaiPhim(String loaiPhim) {
        this.loaiPhim = loaiPhim;
    }

    private int coverPhoto;

>>>>>>> 7e4789ce2cdf8b1180ac7eab2ad5f6244a6795f8
    public Movie(String title, int thumbnail, int coverPhoto) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
    }

    public Movie(String title, int thumbnail, String streamingLink,String loai){
        this.title = title;
        this.thumbnail = thumbnail;
        this.streamingLink = streamingLink;
        this.loaiPhim=loai;
    }
    public Movie(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public Movie(String title, String description, int thumbnail, String studio, String rating, String streamingLink) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.streamingLink = streamingLink;
    }


    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getStudio() {
        return studio;
    }

    public String getRating() {
        return rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }

}
