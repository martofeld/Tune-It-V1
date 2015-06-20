package com.tuneit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.tuneit.R;

public class GenderSelectionActivity extends ActionBarActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO GENDER SELECTION");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selgenero);
        ActionBar a = getSupportActionBar();
        a.hide();
        ((TextView)findViewById(R.id.btnPop)).setOnClickListener(l);
        ((TextView)findViewById(R.id.btnRock)).setOnClickListener(l);
        ((TextView)findViewById(R.id.btnHipHop)).setOnClickListener(l);
        ((TextView)findViewById(R.id.btnOthers)).setOnClickListener(l);
        ((TextView)findViewById(R.id.btnJazz)).setOnClickListener(l);
        ((TextView)findViewById(R.id.btnStrack)).setOnClickListener(l);
        
    }
	
	private void click(View v){
		switch (v.getId()){
		case R.id.btnPop: 
			gotoSongs("pop"); 
			break;
		case R.id.btnRock: 
			gotoSongs("rock"); 
			break;
		case R.id.btnStrack: 
			gotoSongs("soundtrack"); 
			break;
		case R.id.btnOthers: 
			gotoSongs("others"); 
			break;
		case R.id.btnHiphop: 
			gotoSongs("hiphop"); 
			break;
		case R.id.btnJazz: 
			gotoSongs("jazz"); 
			break;	
		}
	}
	
	public void gotoSongs(String g){
		Intent inta = new Intent(GenderSelectionActivity.this, SongSelectionActivity.class);
		inta.putExtra("gender", g);
		startActivity(inta);
	}
	
	OnClickListener l = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			click(arg0);
		}
    };
}