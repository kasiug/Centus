package com.kgozdz.centus.utils;

public class Helpers {

    public static int getCurrentYear(){
        return java.time.LocalDate.now().getYear();
    }

    public static int getCurrentMonth(){
        return java.time.LocalDate.now().getMonth().getValue();
    }
}
