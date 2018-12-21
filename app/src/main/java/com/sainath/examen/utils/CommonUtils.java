package com.sainath.examen.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    private static ProgressDialog mProgressDialog;

    public static void startProgressBarDialog(Context context, String message) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(message);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public static void stopProgressBarDialog() {
        mProgressDialog.dismiss();
    }

    public static String todayDate(String separator) {
        try {
            String day, month, year;
            day = "dd";
            month = "MM";
            year = "yyyy";
            Date dNow = new Date();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat ft = new SimpleDateFormat(day + separator + month + separator + year);
            return ft.format(dNow);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String todayDateDiffFormat(String separator) {
        try {
            String day, month, year;
            day = "dd";
            month = "MM";
            year = "yyyy";
            Date dNow = new Date();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat ft = new SimpleDateFormat(year + separator + month + separator + day);
            return ft.format(dNow);
        } catch (Exception e) {
            throw e;
        }
    }
}

