package com.dalakoti07.android.musico.networks.response;

import com.dalakoti07.android.musico.data.models.TrackModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllTracksResponse {

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public static class Tracks {

        @SerializedName("track")
        @Expose
        private List<TrackModel> track = null;

        public List<TrackModel> getTrack() {
            return track;
        }

        public void setTrack(List<TrackModel> track) {
            this.track = track;
        }

    }
}
