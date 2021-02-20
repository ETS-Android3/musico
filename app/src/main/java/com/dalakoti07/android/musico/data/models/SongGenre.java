package com.dalakoti07.android.musico.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
    This model used in home screen to show all genres
 */
public class SongGenre {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("reach")
    @Expose
    private Integer reach;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getReach() {
        return reach;
    }

    public void setReach(Integer reach) {
        this.reach = reach;
    }

}
