package com.dalakoti07.android.musico.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimelyGreetingsTest {
    static String goodMorningGreetings="Good Morning";
    static String goodAfterNoonGreetings="Good After Noon";
    static String goodEveningGreetings="Good Evening";
    static String dateTimeStamp=", 20/02/2021";

    @Before
    public void setUp(){
        //01:13:26 AM, 20/02/2021 is the type of format expected by SUT method
    }

    @Test
    public void morningTime_returnGoodMorning(){
        String dateAndTime="08:59:26 AM";
        String result=TimelyGreetings.getGreetings(dateAndTime+dateTimeStamp);
        assertEquals(goodMorningGreetings,result);
    }

    @Test
    public void afterNoonTime_returnGoodAfterNoon(){
        String dateAndTime="12:59:26 PM";
        String result=TimelyGreetings.getGreetings(dateAndTime+dateTimeStamp);
        assertEquals(goodAfterNoonGreetings,result);
    }

    @Test
    public void afterNoonTime2_returnGoodAfterNoon(){
        String dateAndTime="03:59:26 PM";
        String result=TimelyGreetings.getGreetings(dateAndTime+dateTimeStamp);
        assertEquals(goodAfterNoonGreetings,result);
    }

    @Test
    public void eveningTime_returnGoodEvening(){
        String dateAndTime="08:59:26 PM";
        String result=TimelyGreetings.getGreetings(dateAndTime+dateTimeStamp);
        assertEquals(goodEveningGreetings,result);
    }

    @Test
    public void lateNightTime_returnGoodEvening(){
        String dateAndTime="10:59:26 PM";
        String result=TimelyGreetings.getGreetings(dateAndTime+dateTimeStamp);
        assertEquals(goodEveningGreetings,result);
    }

    @Test
    public void afterMidNightBefore5Am_returnGoodEvening(){
        String dateAndTime="01:59:26 AM";
        String result=TimelyGreetings.getGreetings(dateAndTime+dateTimeStamp);
        assertEquals(goodEveningGreetings,result);
    }

    @Test
    public void invalidTime_returnsEmptyString(){
        String dateAndTime="01:59:am";
        String result=TimelyGreetings.getGreetings(dateAndTime);
        assertEquals("",result);
    }

    @Test
    public void nullTime_returnEmptyString(){
        String result=TimelyGreetings.getGreetings(null);
        assertEquals("",result);
    }
}