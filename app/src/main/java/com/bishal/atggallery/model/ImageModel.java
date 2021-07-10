package com.bishal.atggallery.model;

public class ImageModel {
    public ImageModel(String title, String url_s, int width_s, int height_s) {
        this.title = title;
        this.url_s = url_s;
        this.width_s = width_s;
        this.height_s = height_s;
    }

    private String title;
    private String url_s;
    private int width_s;
    private int height_s;



    public ImageModel(){

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_s() {
        return url_s;
    }

    public void setUrl_s(String url_s) {
        this.url_s = url_s;
    }

    public int getWidth_s() {
        return width_s;
    }

    public void setWidth_s(int width_s) {
        this.width_s = width_s;
    }

    public int getHeight_s() {
        return height_s;
    }

    public void setHeight_s(int height_s) {
        this.height_s = height_s;
    }
}
