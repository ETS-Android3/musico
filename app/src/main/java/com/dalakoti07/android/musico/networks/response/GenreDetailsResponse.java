package com.dalakoti07.android.musico.networks.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreDetailsResponse {
    @SerializedName("tag")
    @Expose
    private MusicTag tag;

    public MusicTag getMusicTag() {
        return tag;
    }

    public void setMusicTag(MusicTag tag) {
        this.tag = tag;
    }

    public static class MusicTag{
        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("total")
        @Expose
        private Integer total;

        @SerializedName("reach")
        @Expose
        private Integer reach;

        @SerializedName("wiki")
        @Expose
        private MusicWiki wiki;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getReach() {
            return reach;
        }

        public void setReach(Integer reach) {
            this.reach = reach;
        }

        public MusicWiki getWiki() {
            return wiki;
        }

        public void setWiki(MusicWiki wiki) {
            this.wiki = wiki;
        }

    }

    public static class MusicWiki {
        @SerializedName("published")
        @Expose
        private String published;

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        @SerializedName("summary")
        @Expose
        private String summary;

        @SerializedName("content")
        @Expose
        private String content;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }
}
