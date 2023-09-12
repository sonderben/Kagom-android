package com.sonderben.kagom.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DateFormat;
import java.util.Date;

public class Util {
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }




    private static String toUnicode( int position){
        final String zero = "\u2070";
        final String one = "\u00B9";
        final String two = "\u00B2";
        final String twee = "\u00B3";
        final String four = "\u2074";
        final String five = "\u2075";
        final String six = "\u2076";
        final String seven = "\u2077";
        final String eight = "\u2078";
        final String nine = "\u2079";
        switch (position){
            case 0: return zero;
            case 1: return one;
            case 2: return two;
            case 3: return twee;
            case 4: return four;
            case 5: return five;
            case 6: return six;
            case 7: return seven;
            case 8: return eight;
            case 9: return nine;
            default:
                return "";
        }
    }
    public static String positionToSupIndex (int position){
        int pos = position;
        StringBuilder stri = new StringBuilder();
        do{
            stri.insert(    0,toUnicode(pos%10));

            pos = pos/10;

        }while(pos>0);

        return stri.toString();
    }

    public static String formatDate(Date date){
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return format.format(date);
    }











}
