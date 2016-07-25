package com.stone.beautyshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.stone.beautyshare.config.ClassifyConfig;


public class PreloaderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloader);
        ClassifyConfig.getDataByVolley(this);
    }

    public void loadComplete() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.d("TAG", "load complete");
    }
}
