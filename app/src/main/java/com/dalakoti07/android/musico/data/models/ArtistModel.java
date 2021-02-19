package com.dalakoti07.android.musico.data.models;

import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;
import com.dalakoti07.android.musico.networks.response.GenreDetailsResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

public class ArtistModel implements UIData{
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mbid")
    @Expose
    private String mbid;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("streamable")
    @Expose
    private String streamable;

    @SerializedName("image")
    @Expose
    private List<AlbumModel.Image> image = null;

    @SerializedName("bio")
    @Expose
    public ArtistBio bio;

    @SerializedName("similar")
    @Expose
    public SimilarArtist similar;

    @SerializedName("tags")
    @Expose
    public MusicTagsWrapper tags;

    public void setBio(ArtistBio bio) {
        this.bio = bio;
    }

    public void setSimilar(SimilarArtist similar) {
        this.similar = similar;
    }

    public void setTags(MusicTagsWrapper tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public List<AlbumModel.Image> getImage() {
        return image;
    }

    public void setImage(List<AlbumModel.Image> image) {
        this.image = image;
    }

    public ArtistBio getBio() {
        return bio;
    }

    public SimilarArtist getSimilar() {
        return similar;
    }

    public MusicTagsWrapper getTags() {
        return tags;
    }

    public static class ArtistBio {
        @SerializedName("published")
        @Expose
        public String published;

        @SerializedName("summary")
        @Expose
        public String summary;

        @SerializedName("content")
        @Expose
        public String content;
    }

    public static class SimilarArtist{
        @SerializedName("artist")
        @Expose
        public ArrayList<ArtistModel> similarArtist;

        public ArrayList<ArtistModel> getSimilarArtist() {
            return similarArtist;
        }

        public void setSimilarArtist(ArrayList<ArtistModel> similarArtist) {
            this.similarArtist = similarArtist;
        }
    }

    public static class MusicTagsWrapper{
        @SerializedName("tag")
        @Expose
        public ArrayList<AlbumDetailsResponse.MusicTags> published;

        public ArrayList<AlbumDetailsResponse.MusicTags> getPublished() {
            return published;
        }

        public void setPublished(ArrayList<AlbumDetailsResponse.MusicTags> published) {
            this.published = published;
        }
    }

}
