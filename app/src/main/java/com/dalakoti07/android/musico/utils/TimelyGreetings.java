package com.dalakoti07.android.musico.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class TimelyGreetings {

    /**
     * A function which returns greeting as per current time
     * Example Good morning:5 AM to 12 PM
     * Good afternoon: 12 PM to 6 PM
     * Good evening:6 PM to 5AM
     * it never says good night
     * @return correct greetings
     */
    public static String getGreetings(){
        DateFormat df = new SimpleDateFormat("KK:mm:ss a, dd/MM/yyyy", Locale.getDefault());
        String currentDateAndTime = df.format(new Date());
        //01:13:26 AM, 20/02/2021
        Timber.d("time and date %s", currentDateAndTime);
        if(currentDateAndTime.toLowerCase().contains("am"))
            return "Good Morning";
//        else if(){
//
//        }
        else
            return "Good evening";
    }
}
