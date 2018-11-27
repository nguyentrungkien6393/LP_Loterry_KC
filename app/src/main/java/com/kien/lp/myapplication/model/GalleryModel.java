package com.kien.lp.myapplication.model;

public class GalleryModel {
    public String mPath;

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected;

    public GalleryModel() {
    }

    public GalleryModel(String mPath, boolean isSelected) {
        this.mPath = mPath;
        this.isSelected = isSelected;
    }
}
