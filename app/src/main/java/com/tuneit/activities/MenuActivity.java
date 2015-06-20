package com.tuneit.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tuneit.R;
import com.tuneit.classes.Get;
import com.tuneit.classes.Global;
import com.tuneit.classes.Partida;
import com.tuneit.classes.PartidasAdap;
import com.tuneit.classes.SecondPartidasAdap;


public class MenuActivity extends ActionBarActivity {
	private ListView rec;
	private ListView env;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO MENU ACTIVITY");
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.menu);
    	rec = (ListView) findViewById(R.id.list);
    	env = (ListView) findViewById(R.id.list2);
    	//gotoReceived(findViewById(R.id.list));
    	getSupportActionBar().hide();
    	
    	ArrayList<Partida> recibidas = Get.partidas(Global.user.getId(), true);
    	ArrayList<Partida> enviadas = Get.partidas(Global.user.getId(), false);
    	rec.setAdapter( new PartidasAdap(this, R.layout.rows, recibidas) );
    	env.setAdapter( new SecondPartidasAdap(this, R.layout.rows, enviadas) );
    }
    
    public void nuevaPartida(View v){
    	Intent inta = new Intent(MenuActivity.this, GenderSelectionActivity.class);
        startActivity(inta);
    }
    
    public void gotoStats(View v){
        Intent inta = new Intent(MenuActivity.this, StatsActivity.class);
        startActivity(inta);         	
    }
}