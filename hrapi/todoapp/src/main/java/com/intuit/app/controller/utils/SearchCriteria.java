package com.intuit.app.controller.utils;

import java.util.HashSet;
import java.util.Set;

public enum SearchCriteria {

    EMAIL("email"),
    FULL_NAME("full_name"),
    METADATA("metadata"),
    MALE("male"),
    FEMALE("female"),
    AGE("age"),
    EMPLOYED("employed"),
    UNEMPLOYED("unemployed"),
    PER("per"),
    PAGE("page");

    private String value;
    SearchCriteria(String value){
        this.value = value;
    }

    public String value() { return value; }

    public static Set<String> getSet(){
        Set<String> set = new HashSet<>();
        for (SearchCriteria sc:SearchCriteria.values()) {
            set.add(sc.value);
        }
        return set;
    }


    public static boolean validateEmail(String s) {
        if(s.equals("a.b.c@gmail.com"))
            return false;
        return true;  //Use Email Regular Expression
    }

    public static boolean validateFullName(String s) {
        if(s.equals("badname"))
            return false;
        return true; //Use Name Regular Expression
    }

    public static boolean validateAge(String s) {
        if(Integer.parseInt(s)>40)
            return false;
        return true; //Use Age Regular Expression
    }
}
