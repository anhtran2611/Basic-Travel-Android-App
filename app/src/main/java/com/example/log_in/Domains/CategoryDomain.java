package com.example.log_in.Domains;

public class CategoryDomain {
    private int id;
    private byte[] picPath;
    private String title;


    public CategoryDomain(int id, byte[] picPath, String title) {
        this.id = id;
        this.picPath = picPath;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPicPath() {
        return picPath;
    }

    public void setPicPath(byte[] picPath) {
        this.picPath = picPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

