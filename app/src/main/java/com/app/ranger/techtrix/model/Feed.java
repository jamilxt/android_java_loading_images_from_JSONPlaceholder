package com.app.ranger.techtrix.model;

public class Feed {

    private int albumID;
    private int id;
    private String title;
    private String imageUrl;
    private String thumbUrl;

    public Feed() {
    }

    public Feed(int albumID, int id, String title, String imageUrl, String thumbUrl) {
        this.albumID = albumID;
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.thumbUrl = thumbUrl;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
