package com.dalakoti07.android.musico.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class TimelyGreetings {

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
