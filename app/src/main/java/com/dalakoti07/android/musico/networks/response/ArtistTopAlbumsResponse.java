package com.dalakoti07.android.musico.networks.response;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistTopAlbumsResponse {
    @SerializedName("topalbums")
    @Expose
    private TopAlbumsWrapper topAlbumsWrapper;

    public TopAlbumsWrapper getTopAlbumsWrapper() {
        return topAlbumsWrapper;
    }

    public static class TopAlbumsWrapper{

        @SerializedName("album")
        @Expose
        private List<AlbumModel> albums;

        public List<AlbumModel> getAlbums() {
            return albums;
        }
    }

}
