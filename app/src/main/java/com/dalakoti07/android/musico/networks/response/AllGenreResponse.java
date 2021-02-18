package com.dalakoti07.android.musico.networks.response;

import com.dalakoti07.android.musico.data.models.SongGenre;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class AllGenreResponse {
    @SerializedName("toptags")
    @Expose
    private Toptags toptags;

    public Toptags getToptags() {
        return toptags;
    }

    public void setToptags(Toptags toptags) {
        this.toptags = toptags;
    }

    public static class Toptags {

        @SerializedName("tag")
        @Expose
        private List<SongGenre> tag = null;

        public Toptags( List<SongGenre> tag) {
            this.tag = tag;
        }

        public List<SongGenre> getTag() {
            return tag;
        }

        public void setTag(List<SongGenre> tag) {
            this.tag = tag;
        }
    }
}
