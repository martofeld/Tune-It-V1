package com.tuneit.utils;

import android.content.Context;
import android.widget.Toast;

public class Messages{
	Context context;
	
	public Messages(Context context) {
		this.context = context;
	}
	
	public void showMsg(String s){
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(this.context, s, duration);
		toast.show();
	}
}
