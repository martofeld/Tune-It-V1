package com.tuneit.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;

import com.tuneit.R;

public class StartupActivity extends ActionBarActivity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO STARTUP ACTIVITY");
		super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);        
        ActionBar ab = getSupportActionBar();
        ab.hide();
        animarStartup();
        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);
        
            
    }
	private void animarStartup(){
    	ImageView imgView = (ImageView)findViewById(R.id.btnOthers);
    	imgView.setVisibility(ImageView.VISIBLE);
    	imgView.setBackgroundResource(R.drawable.frame_animation);
    	AnimationDrawable frame = (AnimationDrawable) imgView.getBackground();
    	if(frame.isRunning()){
    		frame.stop();
    	}else{
    		frame.stop();
    		frame.start();
    	}
    	
    }
}
