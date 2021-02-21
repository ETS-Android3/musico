package com.dalakoti07.android.musico.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

/**
 * A function which returns greeting as per current time
 * Example Good morning:5 AM to 12 PM
 * Good afternoon: 12 PM to 6 PM
 * Good evening:6 PM to 5AM
 * it never says good night
 * @return correct greetings
 */
public class TimelyGreetings {

    public static String getGreetings(String dateTime){
        //01:13:26 AM, 20/02/2021
        if(dateTime==null)
            return "";

        if(dateTime.length()<=12)
            return "";

        Timber.d("time and date %s", dateTime);
        if(dateTime.toLowerCase().contains("am")){
            //contains am
            int hour=Integer.parseInt(dateTime.substring(0,2));
            if(hour>=5)
                return "Good Morning";
            else
                return "Good Evening";
        } else{
            // contains pm
            int hour=Integer.parseInt(dateTime.substring(0,2));
            if(hour==12 || hour<6)
                return "Good AfterNoon";
            else
                return "Good Evening";
        }
    }
}
