package com.example.log_in.Activities;

public class DataClass {
    private String dataTitle;
    private String dataImage;

    public DataClass(String dataTitle, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataImage = dataImage;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }
}
