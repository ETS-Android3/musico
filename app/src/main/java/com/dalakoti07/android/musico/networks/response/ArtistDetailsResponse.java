package com.dalakoti07.android.musico.networks.response;

import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistDetailsResponse {

    @SerializedName("artist")
    @Expose
    public ArtistModel artist;

    public ArtistDetailsResponse(ArtistModel artist) {
        this.artist = artist;
    }

    public ArtistModel getArtist() {
        return artist;
    }
}
