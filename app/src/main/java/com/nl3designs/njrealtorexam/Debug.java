package com.nl3designs.njrealtorexam;

import android.util.Log;

public class Debug {

    static boolean active = true;

    static public void print(String val){
        if(active) {
            Log.d("skip", val);
        }
    }
}
