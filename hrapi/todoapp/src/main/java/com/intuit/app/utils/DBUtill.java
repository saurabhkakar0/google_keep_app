package com.intuit.app.utils;

import org.joda.time.DateTime;

public class DBUtill {

    public static java.sql.Timestamp convertToJavaSqlTimeStamp(DateTime dateTime) {
        java.sql.Timestamp _timeStamp = new java.sql.Timestamp(dateTime.getMillis());
        return _timeStamp;
    }
}
