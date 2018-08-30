package com.example.hayden.blogtest;

/**
 * Created by Hayden on 1/14/2018.
 */

public class Blog {

    private String title, desc, image, username;

    public Blog(String title, String desc, String image, String username) {
        this.title = title;
        this.desc = desc;
        this.image=image;
        this.username = username;
    }

    public Blog() {
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }}

