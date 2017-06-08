package com.rahimi.kayvon.pottersmagicalbookbrowser;

import java.io.Serializable;

/**
 * Created by Kayvon Rahimi on 14-3-2017.
 */

public class Product implements Serializable {
    private String title, specsTag, summary, longDescription, smallImageUrl, largeImageUrl;



    //Logging tag
    private final String TAG = this.getClass().getSimpleName();

    public Product(String title, String specsTag, String summary, String longDescription, String smallImageUrl, String largeImageUrl) {
        this.title = title;
        this.specsTag = specsTag;
        this.summary = summary;
        this.longDescription = longDescription;
        this.smallImageUrl = smallImageUrl;
        this.largeImageUrl = largeImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecsTag() {
        return specsTag;
    }

    public void setSpecsTag(String specsTag) {
        this.specsTag = specsTag;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    //Comment
    @Override
    public String toString(){
        return "Product{" +
                "Title: '" + title + '\'' +
                ", Author: '" + specsTag + '\'';
    }
}
