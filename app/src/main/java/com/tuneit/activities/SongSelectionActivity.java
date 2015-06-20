package com.tuneit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.appcompat.R.drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tuneit.R;
import com.tuneit.classes.Get;
import com.tuneit.classes.Global;
import com.tuneit.classes.Post;
import com.tuneit.classes.Song;

public class SongSelectionActivity extends ActionBarActivity {
	
	private Song[] songs;
	private Button song1, song2, song3;
	private Song currentsong;
	private String myId;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO SONG SELECTION");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selsong);
        ActionBar g = getSupportActionBar();
        g.hide();
        TextView ab = (TextView) findViewById(R.id.actionbar);
        String gender = getIntent().getStringExtra("gender");
        
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
        
        songs = Get.songs(gender, 3);
        

    	song1 = (Button)findViewById(R.id.answer0);
        song2 = (Button)findViewById(R.id.answer1);
        song3 = (Button)findViewById(R.id.answer2);
        
        if(songs != null){
            song1.setText(songs[0].getName() + " - " + songs[0].getArtist());
            song2.setText(songs[1].getName() + " - " + songs[1].getArtist());
            song3.setText(songs[2].getName() + " - " + songs[2].getArtist());	
        }else{
        	Toast.makeText(this, "Error en la db", Toast.LENGTH_SHORT).show();
        }

        Log.e("user", Global.user.getId());
        myId = Global.user.getId();
        Log.e("user", myId);
	}
	
	public void click(View v){
		String song = ((Button)findViewById(v.getId())).getText().toString().split(" -")[0];
		String otherid = Get.randomUser(Global.user.getId());
		String filename = myId+otherid+song+".3gp";
		
		if(Post.newMatch(Global.user.getId(), otherid, song, filename)){
			Toast.makeText(this, "Partida creada con exito", Toast.LENGTH_SHORT).show();
			gotoRecord(filename, otherid);
		}else{
			Log.e("TUNEIT", "Error en la partida");
		}
		int i = 0;
		while(songs[i].getName() != song && i < songs.length-1){
			i++;
		}
		currentsong = songs[i];
	}
	
	private void gotoRecord(String filename, String userid){
		Intent inta = new Intent(SongSelectionActivity.this, RecordActivity.class);
		inta.putExtra("filename", filename);
		inta.putExtra("otherid", userid);
		inta.putExtra("song", currentsong);
		inta.putExtra("gender", getIntent().getStringExtra("gender"));
		startActivity(inta);
	}
}
