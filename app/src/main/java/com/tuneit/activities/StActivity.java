package com.tuneit.activities;

import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;

import com.tuneit.R;


public class StActivity extends ActionBarActivity{
	Intent ant;
	BitmapFactory.Options opts = new BitmapFactory.Options();
		
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO ST ACTIVITY");
		super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Config.RGB_565;
        ant = new Intent(this, MainActivity.class);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        animate();
            
    }
	private void animate(){
    	ImageView imgView = (ImageView)findViewById(R.id.btnOthers);
    	//imgView.setVisibility(ImageView.VISIBLE);
    	imgView.setBackgroundResource(R.drawable.frame_animation);
    	AnimationDrawable frame = (AnimationDrawable) imgView.getBackground();
    	frame.setOneShot(true);
    	frame.start();
    	checker(frame);
	}
	
	private void checker(AnimationDrawable anim){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checker(a);
                } else{
                	startActivity(ant);
                }
            }
        }, timeBetweenChecks);
    }

}