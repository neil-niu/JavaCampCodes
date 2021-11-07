package com.neil.db.utils;

import java.sql.Date;
import java.sql.Timestamp;

public class DateUtil {
    public static Timestamp dtot(Date d) {
        if (null == d) {
            return null;
        }
        return new Timestamp(d.getTime());
    }

    public static Date ttod(Timestamp t) {
        if (null == t) {
            return null;
        }
        return new Date(t.getTime());
    }
}
