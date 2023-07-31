package com.lojavirtual.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {
    public String parseDate(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }
}
