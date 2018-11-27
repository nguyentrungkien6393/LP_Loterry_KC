package com.kien.lp.myapplication.model;

public class Gallery_Image_Library {
    String image;
    String url;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Gallery_Image_Library(String image, String url) {

        this.image = image;
        this.url = url;
    }
}
