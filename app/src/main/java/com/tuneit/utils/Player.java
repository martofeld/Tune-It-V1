package com.tuneit.utils;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Environment;

public class Player {
	private MediaPlayer mMediaPlayer;
	private boolean isPlaying = false;
	
	public Player(){
		mMediaPlayer = new MediaPlayer();
	}
	
	public void startPlaying(String filename){
		try {
			mMediaPlayer.setDataSource(Environment.getExternalStorageDirectory()+"/tuneIt/"+filename);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
			isPlaying = true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopPlaying(){
		if(isPlaying){
			mMediaPlayer.stop();
			mMediaPlayer.reset();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
}
