package com.example.gallery;

/**
 * Created by Nish on 4/18/15.
 */
public class GalleryItem {
    private String url;
    private String title;

    public GalleryItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }
}
