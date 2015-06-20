package com.tuneit.utils;

import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class Recorder {
	private MediaRecorder mMediaRecorder = new MediaRecorder();;
	private boolean isRecording = false;

	public Recorder(){
	}
	
	public void startRecording(String filename){
		String dir = Environment.getExternalStorageDirectory().toString()+"/tuneit/"+filename;
		mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mMediaRecorder.setOutputFile(dir);

		try{
			mMediaRecorder.prepare();
			mMediaRecorder.start();
			isRecording = true;
		}
		catch(IOException e){
			Log.e("Exception", "Ole gato" + e.getMessage());
		}
	}
	

	public void stopRecording(){
		if(this.isRecording()){
			mMediaRecorder.stop();
			mMediaRecorder.reset();
			mMediaRecorder.release();
			mMediaRecorder = null;
		}
	}
	
	public boolean isRecording(){
		return this.isRecording;
	}
}