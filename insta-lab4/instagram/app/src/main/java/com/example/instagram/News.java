package com.example.instagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class News implements Serializable {
    public static List<News> newsList = new ArrayList<>();
    public static List<News> saves = new ArrayList<>();
    private int id;
    private String author;
    private String date;
    private int profilePhoto;
    private int likesCnt;
    private int postImage;
    private String postText;
    private int likeBtn;
    private boolean isLiked;


    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikeBtn() {
        return likeBtn;
    }

    public void setLikeBtn(int likeBtn) {
        this.likeBtn = likeBtn;
    }

    public News(int id, String author, String date, int profilePhoto, int likesCnt, int postImage, String postText) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.profilePhoto = profilePhoto;
        this.likesCnt = likesCnt;
        this.postImage = postImage;
        this.postText = postText;
        this.likeBtn = R.drawable.like;
        this.isLiked = false;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public int getLikesCnt() {
        return likesCnt;
    }

    public void setLikesCnt(int likesCnt) {
        this.likesCnt = likesCnt;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}

