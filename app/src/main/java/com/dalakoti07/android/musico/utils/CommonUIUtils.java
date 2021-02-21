package com.dalakoti07.android.musico.utils;

import com.google.gson.internal.$Gson$Preconditions;

public class CommonUIUtils {

    /**
     * Since the artist images are same and non trivial so we have returning better image as per our use case
     * @param artist artist's name
     * @param url artist's image url
     * @return returns a better url
     */
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

    /**
     *
     * @param number in string
     * @return String which is of format {x}K or {x}M where x is < 1000
     */
    public static String getCountInThousandsOrMillions(String number){
        int intNum=Integer.parseInt(number);
        if(intNum<1000)
            return intNum+"";
        intNum=intNum/1000;
        if(intNum<1000)
            return intNum+"K";
        intNum=intNum/1000;
        return intNum+"M";
    }
}
