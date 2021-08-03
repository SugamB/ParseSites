package com.example.parsesites;

public class ParseItem {
    private String imgUrl;
    private String title;
    private String httplink;
    private String imdb;
    private String genre;

    public ParseItem() {
    }

    public ParseItem(String imgUrl, String title, String httplink, String imdb, String genre) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.httplink=httplink;
        this.imdb=imdb;
        this.genre=genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getHttplink() {
        return httplink;
    }

    public void setHttplink(String httplink) {
        this.httplink = httplink;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
