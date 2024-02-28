package com.example.fastfood.utils;

import java.time.LocalDateTime;

public class DateUtil {
    public static boolean checkDateIsExpired(LocalDateTime localDateTime){
        return localDateTime.isAfter(LocalDateTime.now());
    }
}
