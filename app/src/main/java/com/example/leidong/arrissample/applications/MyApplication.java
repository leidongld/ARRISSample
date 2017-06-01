package com.example.leidong.arrissample.applications;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.leidong.arrissample.constants.Constants;

/**
 * Created by leidong on 2017/6/1
 */

public class MyApplication extends Application {


    @Override
    public void onCreate(){
        super.onCreate();
        SharedPreferences sp = getSharedPreferences(Constants.URL_SP_PARAMS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constants.URL_SP, "");
        editor.apply();
    }
}
