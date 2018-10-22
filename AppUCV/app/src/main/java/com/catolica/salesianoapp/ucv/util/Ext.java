package com.catolica.salesianoapp.ucv.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Meycon Augusto on 11/02/2018.
 */

public final class Ext {
    public static final LayoutInflater getLayoutInflater(Context receiver){
        @SuppressLint("WrongConstant") Object object = receiver.getSystemService("layout_inflater");
        return (LayoutInflater) object;
    }
    public static final LayoutInflater getLayoutInflater(View receiver){
        return getLayoutInflater(receiver.getContext());
    }

}
