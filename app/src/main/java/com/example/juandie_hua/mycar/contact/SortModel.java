package com.example.juandie_hua.mycar.contact;

import android.graphics.Bitmap;

public class SortModel {

    private String name;
    private String sortLetters;
    private Bitmap bitmap;
    private String phonenum;
    int point;

    public SortModel(String name, String sortLetters, Bitmap bitmap,
                     String phonenum, int point) {
        this.name = name;
        this.sortLetters = sortLetters;
        this.bitmap = bitmap;
        this.phonenum = phonenum;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
