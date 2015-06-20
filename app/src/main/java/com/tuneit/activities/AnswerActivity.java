package com.tuneit.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tuneit.R;
import com.tuneit.classes.Downloader;
import com.tuneit.classes.Player;


public class AnswerActivity extends ActionBarActivity {
	
	String data, filename, id, songname, a1, a2, a3;
	Downloader d = new Downloader();
	Player p = new Player();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO ANSWER ACTIVITY");
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.play); 
    	LinearLayout root = (LinearLayout) findViewById(R.id.playctr);
    	getSupportActionBar().hide();
    	
        data = getIntent().getStringExtra("genre");
        filename = getIntent().getStringExtra("filename");
        id = getIntent().getStringExtra("partidaid");
        songname = filename.substring(17, filename.indexOf('.')-1);
        a1 = getIntent().getStringExtra("a1");
        a2 = getIntent().getStringExtra("a2");
        a3 = getIntent().getStringExtra("a3");
        
    	try {
			d.execute("http://tuneit.hostei.com/files/"+filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        if(data.equals("rock")){
        	root.setBackgroundResource(R.drawable.wprock);
        }
        else if(data.equals("pop")){
        	root.setBackgroundResource(R.drawable.wppop);
        }
        else if(data.equals("hiphop")){
        	root.setBackgroundResource(R.drawable.wphiphop);
        }
    }
    
    public void respondido(View v){   	
        setContentView(R.layout.result);
        LinearLayout root2 = (LinearLayout) findViewById(R.id.ctr2);
    	if(data.equals("rock")){
        	root2.setBackgroundResource(R.drawable.wprock);
        }
        else if(data.equals("pop")){
        	root2.setBackgroundResource(R.drawable.wppop);
        }
        else if(data.equals("hiphop")){
        	root2.setBackgroundResource(R.drawable.wphiphop);
        }
    }
    
	final CountDownTimer recordCount = new CountDownTimer(10000, 1000) {
	    public void onTick(long millisUntilFinished) {
	    	Toast.makeText(AnswerActivity.this, "Segundos restantes: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
	    }

	    public void onFinish() {
	    	Toast.makeText(AnswerActivity.this, "Grabacion finalizada. Escuche el audio", Toast.LENGTH_SHORT).show();
	        if(p.isPlaying())
	        	p.stopPlaying();
	        setContentView(R.layout.answer);
	        Button ans1 = (Button)findViewById(R.id.answer1);
	        Button ans2 = (Button)findViewById(R.id.answer2);
	        Button ans3 = (Button)findViewById(R.id.answer3);
	        Button ans4 = (Button)findViewById(R.id.answer4);
	        
	        ans1.setText(a1);
	        ans2.setText(a2);
	        ans3.setText(a3);
	        ans4.setText(songname);
	   }
	    
	};
    
    public void go(View v){
    	p.startPlaying(filename);
    	/*ImageView imgView = (ImageView)findViewById(R.id.res);
    	//imgView.setVisibility(ImageView.VISIBLE);
    	imgView.setBackgroundResource(R.drawable.frame_animation_2);
    	AnimationDrawable frame = (AnimationDrawable) imgView.getBackground();
    	frame.setOneShot(true);
    	frame.start();
    	checker(frame);*/
	}
    
    public void check(View v){
    	String song = ((Button)v).getText().toString();
    	setContentView(R.layout.result);
		ImageView img = (ImageView)findViewById(R.id.res);
    	if(song.equals(songname)){
    		img.setBackgroundResource(R.drawable.bien);
    	}else{
    		img.setBackgroundResource(R.drawable.mal);
    	}
    }
    
    public void finished(View v){
    	Intent i = new Intent(this, MenuActivity.class);
    	startActivity(i);
    	this.finish();
    }
	
	public void checker(AnimationDrawable anim){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checker(a);
                } else{
                	setContentView(R.layout.answer);
                	LinearLayout root2 = (LinearLayout) findViewById(R.id.ctr1);
                	if(data.equals("rock")){
                    	root2.setBackgroundResource(R.drawable.wprock);
                    }
                    else if(data.equals("pop")){
                    	root2.setBackgroundResource(R.drawable.wppop);
                    }
                    else if(data.equals("hiphop")){
                    	root2.setBackgroundResource(R.drawable.wphiphop);
                    }
                }
            }
        }, timeBetweenChecks);
    }
}