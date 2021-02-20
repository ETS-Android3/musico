package com.dalakoti07.android.musico.networks.response;

import com.dalakoti07.android.musico.data.models.TrackModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistTopTrackResponse {

    @SerializedName("toptracks")
    @Expose
    private TopTrackWrapper topTrackWrapper;

    public TopTrackWrapper getTopTrackWrapper() {
        return topTrackWrapper;
    }

    public static class TopTrackWrapper{

        @SerializedName("track")
        @Expose
        private List<TrackModel> musicTags;

        public List<TrackModel> getMusicTags() {
            return musicTags;
        }
    }

}
