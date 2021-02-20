package com.dalakoti07.android.musico.networks.response;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumDetailsResponse {

    @SerializedName("album")
    @Expose
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public static class Album{
        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("artist")
        @Expose
        private String artist;

        @SerializedName("mbid")
        @Expose
        private String mbid;

        @SerializedName("url")
        @Expose
        private String url;

        @SerializedName("image")
        @Expose
        private List<AlbumModel.Image> image = null;

        @SerializedName("listeners")
        @Expose
        private String listeners;

        @SerializedName("playcount")
        @Expose
        private String playcount;

        @SerializedName("tracks")
        @Expose
        private AllTracksResponse.Tracks tracks;

        @SerializedName("tags")
        @Expose
        private ArtistModel.MusicTagsWrapper tags;

        @SerializedName("wiki")
        @Expose
        private GenreDetailsResponse.MusicWiki wiki;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
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

        public List<AlbumModel.Image> getImage() {
            return image;
        }

        public void setImage(List<AlbumModel.Image> image) {
            this.image = image;
        }

        public String getListeners() {
            return listeners;
        }

        public void setListeners(String listeners) {
            this.listeners = listeners;
        }

        public String getPlaycount() {
            return playcount;
        }

        public void setPlaycount(String playcount) {
            this.playcount = playcount;
        }

        public AllTracksResponse.Tracks getTracks() {
            return tracks;
        }

        public void setTracks(AllTracksResponse.Tracks tracks) {
            this.tracks = tracks;
        }

        public ArtistModel.MusicTagsWrapper getTags() {
            return tags;
        }

        public void setTags(ArtistModel.MusicTagsWrapper tags) {
            this.tags = tags;
        }

        public GenreDetailsResponse.MusicWiki getWiki() {
            return wiki;
        }

        public void setWiki(GenreDetailsResponse.MusicWiki wiki) {
            this.wiki = wiki;
        }
    }

    public static class MusicTags{
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("url")
        @Expose
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

}
