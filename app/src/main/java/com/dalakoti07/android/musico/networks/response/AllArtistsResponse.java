package com.dalakoti07.android.musico.networks.response;

import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllArtistsResponse {

    @SerializedName("topartists")
    @Expose
    private Topartists topartists;

    public Topartists getTopartists() {
        return topartists;
    }

    public void setTopartists(Topartists topartists) {
        this.topartists = topartists;
    }

    public static class Topartists {

        @SerializedName("artist")
        @Expose
        private List<ArtistModel> artist = null;

        public List<ArtistModel> getArtist() {
            return artist;
        }

        public void setArtist(List<ArtistModel> artist) {
            this.artist = artist;
        }
    }
}
