
package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bestiary {

    @SerializedName("sections")
    @Expose
    private List<MonsterType> sections = null;

    public List<MonsterType> getSections() {
        return sections;
    }

    public void setSections(List<MonsterType> sections) {
        this.sections = sections;
    }


    @Override
    public String toString() {
        return "Bestiary{" +
                "sections=" + sections +
                '}';
    }
}
