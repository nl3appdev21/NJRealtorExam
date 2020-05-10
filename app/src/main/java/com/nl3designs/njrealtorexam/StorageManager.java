package com.nl3designs.njrealtorexam;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class StorageManager {
    SharedPreferences pref;

    public StorageManager(Context context){
        pref = context.getSharedPreferences("skipBox",Context.MODE_PRIVATE);
        //  SharedPreferences.Editor editor = pref.edit();  //  ??  use to clear lb array  ??
        //  editor.clear().apply();  //  ??  use to clear lb array ??
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