package com.lojavirtual.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

@Component
public class DateUtils {
    public String parseDate(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    public Date parseStringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.parse(date);
    }

    public Date getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.systemDefault());

        Instant instant = zonedDateTime.toInstant();

        return Date.from(instant);
    }
}
