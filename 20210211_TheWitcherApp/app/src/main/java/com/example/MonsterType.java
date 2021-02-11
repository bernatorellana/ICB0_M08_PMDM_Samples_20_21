
package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonsterType {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("entries")
    @Expose
    private List<Monster> entries = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Monster> getEntries() {
        return entries;
    }

    public void setEntries(List<Monster> entries) {
        this.entries = entries;
    }


    @Override
    public String toString() {
        return "Section{" +
                "title='" + title + '\'' +
               // ", subtitle='" + subtitle + '\'' +
                ", image='" + image + '\'' +
                ", entries=" + entries +
                '}';
    }
}
