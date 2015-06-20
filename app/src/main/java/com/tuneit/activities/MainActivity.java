package com.tuneit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tuneit.R;
import com.tuneit.classes.Get;
import com.tuneit.classes.Global;
import com.tuneit.classes.Messages;
import com.tuneit.classes.Post;


public class MainActivity extends ActionBarActivity {
	Messages m = new Messages(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.e("TRANSITION", "TRANSITIONED TO MAIN ACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
       
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
            return true;
        }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    public void gotoInsert(View v){
			Intent inta = new Intent(MainActivity.this, RegisterActivity.class);
		    startActivity(inta);
    }
    	
    public void gotoMenu(View v){
    	String user = ((EditText) findViewById(R.id.txtUser)).getText().toString();
		String pass = ((EditText) findViewById(R.id.txtPass)).getText().toString();
		Log.e("debug", user + " " + pass);
		
		if(user.equals("") || pass.equals("")){
			m.showMsg("Usuario y/o contraseña inválido/a");
		}else{
			if (Post.login(user, pass)){
				Global.user = Get.userByUsername(user);
				Log.i("usuario", Global.user.toString());
				Intent inta = new Intent(MainActivity.this, MenuActivity.class);
				startActivity(inta);
			}
			else{
				m.showMsg("Usuario o contraseña inválido(s)");
			}
		}
		
   	}
}
