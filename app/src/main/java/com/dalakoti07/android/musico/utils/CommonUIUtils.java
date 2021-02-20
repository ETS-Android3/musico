package com.dalakoti07.android.musico.utils;

public class CommonUIUtils {

    public static String getArtistImage(String artist,String url){
        artist=artist.toLowerCase();
        switch (artist){
            case "justin": return "https://lh3.googleusercontent.com/pW7Jv2o8g0bkXFi11hrumm_N0e7KAf5pc5bawoSdD44uTLAYQi-Eeh1t1HileeiMx-9pXN6hQROW-OBEzWQWcEs2" ;
            case "coldplay": return "https://yt3.ggpht.com/ytc/AAUvwniJQKE6BirVS0nIpVjL3X_-ZRmWfK5cZiQ2BqHc3A=s900-c-k-c0x00ffffff-no-rj";
            default: break;
        }
        if(url.contains("2a96cbd8b46e442fc41c2b86b821562f"))
            return "https://cdn.buttercms.com/4YT6ubmSRejqbHCARAHX";
        return url;
    }
}
