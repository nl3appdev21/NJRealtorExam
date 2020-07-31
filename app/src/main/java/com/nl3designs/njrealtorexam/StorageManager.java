package com.nl3designs.njrealtorexam;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageManager {
    SharedPreferences pref;

    public StorageManager(Context context){
        pref = context.getSharedPreferences("skipBox",Context.MODE_PRIVATE);

        //TODO: code used to use to clear lb array, clean up
        //  SharedPreferences.Editor editor = pref.edit();  // use this code to clear lb array
        //  editor.clear().apply();  // use this code to clear lb array

    }

    public void save(String text, String tag){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(tag,text);
        editor.apply();
    }

    public String load(String tag){
        return pref.getString(tag,"");
    }
}