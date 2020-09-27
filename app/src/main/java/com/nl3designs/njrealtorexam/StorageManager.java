package com.nl3designs.njrealtorexam;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StorageManager {
    private SharedPreferences pref;
    private FirebaseFirestore firestore;

    public StorageManager(Context context){
        pref = context.getSharedPreferences("skipBox",Context.MODE_PRIVATE);
        firestore = FirebaseFirestore.getInstance();

        //TODO: code used to use to clear lb array, clean up
        //  ************************************************
        // SharedPreferences.Editor editor = pref.edit();  // use this code to clear lb array
        // editor.clear().apply();  // use this code to clear lb array

    }

    public void save(String text, String tag){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(tag,text);
        editor.apply();

    }

    public void save(String text, String tag, OnCompleteListener listener){
        Map<String,String> score = new HashMap<>();
        score.put("scores",text);
        firestore.collection(tag).document("data").set(score).addOnCompleteListener(listener);

    }


    public String load(String tag){
        return pref.getString(tag,"");
    }

    public void load(String tag, OnCompleteListener listener){
        firestore.collection(tag).document("data").get().addOnCompleteListener(listener);

    }
}