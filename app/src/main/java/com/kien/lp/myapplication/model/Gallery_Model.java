package com.kien.lp.myapplication.model;

public class Gallery_Model {
    String Images;
    boolean check;

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Gallery_Model(String images, boolean check) {
        Images = images;
        this.check = check;
    }
}
