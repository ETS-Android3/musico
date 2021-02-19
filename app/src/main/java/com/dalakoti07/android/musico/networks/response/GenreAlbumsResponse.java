package com.dalakoti07.android.musico.networks.response;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreAlbumsResponse {

    @SerializedName("albums")
    @Expose
    private Albums albums;

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    public static class Albums{
        @SerializedName("album")
        @Expose
        private List<AlbumModel> album = null;


        public List<AlbumModel> getAlbum() {
            return album;
        }

        public void setAlbum(List<AlbumModel> album) {
            this.album = album;
        }


    }

}
