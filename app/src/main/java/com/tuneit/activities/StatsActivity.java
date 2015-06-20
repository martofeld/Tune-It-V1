package com.tuneit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tuneit.R;
import com.tuneit.utils.Get;
import com.tuneit.utils.Stats;


public class StatsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO STATS ACTIVITY");
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.stats);
    	ActionBar x = getSupportActionBar();
    	x.hide();
    	Stats stat = Get.stats("U76604577");
    	//Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/MAGNETOB.TTF");
    	TextView lost = (TextView) findViewById(R.id.TextView05);
    	TextView won = (TextView) findViewById(R.id.textView1);
    	TextView total = (TextView) findViewById(R.id.TextView07);
    	TextView rock = (TextView) findViewById(R.id.textView3);
    	TextView hiphop = (TextView) findViewById(R.id.textView4);
    	TextView pop = (TextView) findViewById(R.id.TextView11);
    	TextView jazz = (TextView) findViewById(R.id.TextView10);
    	TextView soundtrack = (TextView) findViewById(R.id.TextView13);
    	TextView other = (TextView) findViewById(R.id.TextView12);
    	/*rock.setTypeface(custom_font);
    	pop.setTypeface(custom_font);
    	hiphop.setTypeface(custom_font);
    	jazz.setTypeface(custom_font);
    	soundtrack.setTypeface(custom_font);
    	other.setTypeface(custom_font);*/
    	if(stat.getPartidasGeneroTotales()[1]!=0){
    	rock.setText("Rock:\n"+String.valueOf((int)(stat.getPartidasGeneroGanadas()[5])/(stat.getPartidasGeneroTotales()[5])*100)+"%\nde" + String.valueOf((stat.getPartidasGeneroTotales()[5])));
    	}
    	if(stat.getPartidasGeneroTotales()[3]!=0){
    	hiphop.setText("HipHop:\n"+String.valueOf((int)(stat.getPartidasGeneroGanadas()[3])/(stat.getPartidasGeneroTotales()[3])*100)+"%\nde" + String.valueOf((stat.getPartidasGeneroTotales()[3])));
    	}
    	if(stat.getPartidasGeneroTotales()[0]!=0){
    	pop.setText("Pop:\n"+String.valueOf((int)(stat.getPartidasGeneroGanadas()[4])/(stat.getPartidasGeneroTotales()[4])*100)+"%\nde" + String.valueOf((stat.getPartidasGeneroTotales()[4])));
    	}
    	if(stat.getPartidasGeneroTotales()[2]!=0){
    	jazz.setText("Jazz:\n"+String.valueOf((int)(stat.getPartidasGeneroGanadas()[0])/(stat.getPartidasGeneroTotales()[0])*100)+"%\nde" + String.valueOf((stat.getPartidasGeneroTotales()[0])));
    	}
    	if(stat.getPartidasGeneroTotales()[3]!=0){
    	soundtrack.setText("Soundtracks:\n"+String.valueOf((int)(stat.getPartidasGeneroGanadas()[1])/(stat.getPartidasGeneroTotales()[1])*100)+"%\nde" + String.valueOf((stat.getPartidasGeneroTotales()[1])));
    	}
    	if(stat.getPartidasGeneroTotales()[4]!=0){
    	other.setText("Otros:\n"+String.valueOf((int)(stat.getPartidasGeneroGanadas()[2])/(stat.getPartidasGeneroTotales()[2])*100)+"%\nde" + String.valueOf((stat.getPartidasGeneroTotales()[2])));
    	}
    }
    
    public void gotoMenu(View v){
    	Intent inta = new Intent(StatsActivity.this, MenuActivity.class);
        startActivity(inta);
    }
    
}
