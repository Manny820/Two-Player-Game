package com.example.twoplayergame;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.twoplayergame.Statistics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrefConfig {
    private static final String LIST_KEY = "list_key";
    public static void write(Context context, ArrayList<Statistics> arrayList){
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(LIST_KEY,json);
        editor.apply();
    }
    public static ArrayList<Statistics> read(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString(LIST_KEY,"");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Statistics>>(){}.getType();
        ArrayList<Statistics> arrayList = gson.fromJson(json,type);

        if(arrayList == null)
            arrayList = new ArrayList<>();

        return arrayList;
    }
}