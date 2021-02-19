package com.dalakoti07.android.musico.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Attr;

import java.util.List;

public class TrackModel implements UIData {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("mbid")
    @Expose
    private String mbid;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("artist")
    @Expose
    private ArtistModel artist;

    @SerializedName("image")
    @Expose
    private List<AlbumModel.Image> image = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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


    public ArtistModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistModel artist) {
        this.artist = artist;
    }

    public List<AlbumModel.Image> getImage() {
        return image;
    }

    public void setImage(List<AlbumModel.Image> image) {
        this.image = image;
    }
}
