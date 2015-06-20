package com.tuneit.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.tuneit.R;


public class TrophiesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO TROPHIES ACTIVITY");
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.trofeos);
    	ActionBar x = getSupportActionBar();
    	x.hide();
    }
        
}