package com.laberit.sina.bootcamp.modulo3.spring_web.utils;

import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;

public class EnumUtils {
    public static boolean isValidCategory(String category) {
        try {
            Category.valueOf(category.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}