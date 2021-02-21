package com.dalakoti07.android.musico.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUIUtilsTest {

    // testing getCountInThousandsOrMillions function

    @Test
    public void numberInThousands_returnNumberWithK(){
        String number="123456";
        String result=CommonUIUtils.getCountInThousandsOrMillions(number);
        assertEquals("123K",result);
    }

    @Test
    public void numberLessThanThousands_returnNumberWithK(){
        String number="123";
        String result=CommonUIUtils.getCountInThousandsOrMillions(number);
        assertEquals("123",result);
    }

    @Test
    public void numberInThousands2_returnNumberWithK(){
        String number="1234";
        String result=CommonUIUtils.getCountInThousandsOrMillions(number);
        assertEquals("1K",result);
    }

    @Test
    public void numberInMillion_returnNumberWithK(){
        String number="1234567";
        String result=CommonUIUtils.getCountInThousandsOrMillions(number);
        assertEquals("1M",result);
    }

    @Test
    public void numberInMillion2_returnNumberWithK(){
        String number="123456789";
        String result=CommonUIUtils.getCountInThousandsOrMillions(number);
        assertEquals("123M",result);
    }
}