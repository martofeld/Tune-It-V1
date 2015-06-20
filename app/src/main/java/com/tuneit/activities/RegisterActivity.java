package com.tuneit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.tuneit.R;
import com.tuneit.utils.Messages;
import com.tuneit.utils.Post;

public class RegisterActivity extends ActionBarActivity {
	private Messages m = new Messages(RegisterActivity.this);
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO REGISTER ACTIVITY");
		super.onCreate(savedInstanceState);
        setContentView(R.layout.datainsert);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        
            
    }
	
	public void register(View v){
		String name = ((EditText) findViewById(R.id.txtName)).getText().toString();
		String last = ((EditText) findViewById(R.id.txtLast)).getText().toString();
		String mail = ((EditText) findViewById(R.id.txtMail)).getText().toString();
		String user = ((EditText) findViewById(R.id.txtUsername)).getText().toString();
		String pass = ((EditText) findViewById(R.id.txtPassword)).getText().toString();
		if(name != "" && last != "" && mail != "" && user !="" && pass != ""){
			try {
				if(Post.register(name, last, mail, user, pass)){
					backToMain();
				} else {
					m.showMsg("Error");
				}
			} catch (Exception e) {
				Log.e("DEBUG", e.getMessage());
			}
		}else{
			m.showMsg("Something is missing");
		}
	}
	
	public void backToMain(){
    	Intent inta = new Intent(RegisterActivity.this, MainActivity.class);
    	startActivity(inta);
	}
}