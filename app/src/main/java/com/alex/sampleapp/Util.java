package com.alex.sampleapp;

import android.text.TextUtils;

public class Util {

    private Util() {
    }

    public static boolean canBeConvertToInt(String input) {
        boolean result = false;
        if (!TextUtils.isEmpty(input)) {
            try {
                Integer.parseInt(input);
                result = true;
            } catch (NumberFormatException e) {
                result = false;
            }
        }
        return result;
    }
}
