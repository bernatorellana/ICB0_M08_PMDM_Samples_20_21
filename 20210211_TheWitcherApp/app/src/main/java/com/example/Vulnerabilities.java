
package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vulnerabilities {

    @SerializedName("bombs")
    @Expose
    private List<String> bombs = null;
    @SerializedName("oils")
    @Expose
    private List<String> oils = null;
    @SerializedName("potions")
    @Expose
    private List<String> potions = null;
    @SerializedName("signs")
    @Expose
    private List<String> signs = null;

    public List<String> getBombs() {
        return bombs;
    }

    public void setBombs(List<String> bombs) {
        this.bombs = bombs;
    }

    public List<String> getOils() {
        return oils;
    }

    public void setOils(List<String> oils) {
        this.oils = oils;
    }

    public List<String> getPotions() {
        return potions;
    }

    public void setPotions(List<String> potions) {
        this.potions = potions;
    }

    public List<String> getSigns() {
        return signs;
    }

    public void setSigns(List<String> signs) {
        this.signs = signs;
    }

}
