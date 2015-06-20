package com.tuneit.activities;

import java.io.File;
import java.io.FileInputStream;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tuneit.R;
import com.tuneit.classes.Recorder;
import com.tuneit.classes.Song;
import com.tuneit.classes.Uploader;

public class RecordActivity extends ActionBarActivity {
	private ImageButton btn;
	private Recorder mr;
	private String filename, otherid;
	private Song song;
	private boolean finished = false;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO RECORD ACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        ActionBar g = getSupportActionBar();
        g.hide();
        TextView ab = (TextView) findViewById(R.id.actionbar);
        String gender = getIntent().getStringExtra("gender");
        TextView partner = (TextView) findViewById(R.id.partner);
        partner.setText("Juegas con:\n"+getIntent().getStringExtra("otherid"));
        partner.setTextColor((int) 0xFFFFFF);
        LinearLayout back = (LinearLayout) findViewById(R.id.background);
        if(gender.equals("pop")){
        	back.setBackgroundResource(R.drawable.wppop);
        	ab.setBackgroundResource(R.drawable.pop_actionbar);
        	
        	
        }
        if(gender.equals("rock")){
        	back.setBackgroundResource(R.drawable.wprock);
        	ab.setBackgroundResource(R.drawable.rck_actionbar);
        	
        }
	    if(gender.equals("hiphop")){
	    	back.setBackgroundResource(R.drawable.wphiphop);
	    	ab.setBackgroundResource(R.drawable.hip_actionbar);
	    	
	    }
	    if(gender.equals("jazz")){
	    	back.setBackgroundResource(R.drawable.wpjazz);
	    	ab.setBackgroundResource(R.drawable.jzz_actionbar);
	    	
	    }
	    if(gender.equals("stracks")){
	    	back.setBackgroundResource(R.drawable.wpstracks);
	    	ab.setBackgroundResource(R.drawable.stk_actionbar);
	    	
	    }
        
        btn = (ImageButton)findViewById(R.id.btnStart);
        btn.setOnTouchListener(touch);
        
        filename = getIntent().getStringExtra("filename");
        otherid = getIntent().getStringExtra("otherid");
        song = (Song)getIntent().getSerializableExtra("song");
        
        mr = new Recorder();
    }
	
	private View.OnTouchListener touch = new View.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				recordCount.start();
				mr.startRecording(filename);
				//animateRec();
				break;
			case MotionEvent.ACTION_UP:
				if(!finished)
					recordCount.onFinish();
				break;
			}
			return false;
		}
	};
	
	//Counter para que termine la grabacion, lo que pase primero
	final CountDownTimer recordCount = new CountDownTimer(10000, 1000) {
	    public void onTick(long millisUntilFinished) {
	    	Toast.makeText(RecordActivity.this, "Segundos restantes: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
	    }

	    public void onFinish() {
	    	Toast.makeText(RecordActivity.this, "Grabacion finalizada. Escuche el audio", Toast.LENGTH_SHORT).show();
	    	finished = true;
	        if(mr.isRecording())
	        	mr.stopRecording();
			saveFile();
			switching();
	   }
	};
	
	private void saveFile(){
		String filepath = Environment.getExternalStorageDirectory().toString()+"/tuneIt/"+filename;
		File f = new File(filepath);
		if(f.exists()){
		// Set your file path here
			Uploader hfu = new Uploader(filename);
			FileInputStream fstrm = hfu.getInputStream(filepath);
		    
			hfu.Send_Now(fstrm, filename);
		}else{
			Log.d("File doesn't exist", "NO EXISTO");
		}
	}
	
	private void switching(){
    	setContentView(R.layout.postrec);
    	String gender = getIntent().getStringExtra("gender");
    	//TextView partner = (TextView) findViewById(R.id.partner);
    	//partner.setText("Juegas con:\n"+getIntent().getStringExtra("otherid"));
        //partner.setTextColor((int) 0xFFFFFF);
        LinearLayout back = (LinearLayout) findViewById(R.id.background);
    	TextView ab = (TextView) findViewById(R.id.actionbar);
    	if(gender.equals("pop")){
    		
        	back.setBackgroundResource(R.drawable.wppop);
        	ab.setBackgroundResource(R.drawable.pop_actionbar);
        	
        	
        }
        if(gender.equals("rock")){
        	back.setBackgroundResource(R.drawable.wprock);
        	ab.setBackgroundResource(R.drawable.rck_actionbar);
        	
        }
	    if(gender.equals("hiphop")){
	    	back.setBackgroundResource(R.drawable.wphiphop);
	    	ab.setBackgroundResource(R.drawable.hip_actionbar);
	    	
	    }
	    if(gender.equals("jazz")){
	    	back.setBackgroundResource(R.drawable.wpjazz);
	    	ab.setBackgroundResource(R.drawable.jzz_actionbar);
	    	
	    }
	    if(gender.equals("stracks")){
	    	back.setBackgroundResource(R.drawable.wpstracks);
	    	ab.setBackgroundResource(R.drawable.stk_actionbar);
	    	
	    }
    	ImageView imgOk = (ImageView)findViewById(R.id.ok);
    	ImageView imgRetry = (ImageView)findViewById(R.id.retry);
    	imgOk.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(RecordActivity.this, MenuActivity.class));
				finish();
			}
    		
    	});
    	imgRetry.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				setContentView(R.layout.record);
			}
    	});
	}
	
	private void animateRec(){
    	ImageView imgView = (ImageView)findViewById(R.id.btnStart);
    	imgView.setVisibility(ImageView.VISIBLE);
    	imgView.setBackgroundResource(R.drawable.frame_animation_2);
    	AnimationDrawable frame = (AnimationDrawable) imgView.getBackground();
    	frame.setOneShot(true);
    	frame.start();
    	checker(frame);
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
                	setContentView(R.layout.postrec);
                }
            }
        }, timeBetweenChecks);
    }
}