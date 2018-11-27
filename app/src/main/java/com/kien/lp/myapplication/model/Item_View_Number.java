package com.kien.lp.myapplication.model;

public class Item_View_Number {
    String chuoi;

    public String getChuoi() {
        return chuoi;
    }

    public void setChuoi(String chuoi) {
        this.chuoi = chuoi;
    }

    public String getThu_tu() {
        return thu_tu;
    }

    public void setThu_tu(String thu_tu) {
        this.thu_tu = thu_tu;
    }

    public Item_View_Number(String chuoi, String thu_tu) {
        this.chuoi = chuoi;
        this.thu_tu = thu_tu;
    }

    String thu_tu;
}
