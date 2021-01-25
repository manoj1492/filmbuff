package com.mycompany.filmbuff.util.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DateUtility {

    public static long getLocalDateTimeInMillis(final LocalDateTime dateTime) {
        return LocalDateTime.of(1970, 1, 1, 0, 0, 0).until(dateTime,
                ChronoUnit.MILLIS);
    }

    public static LocalDateTime millsToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }
    
}
