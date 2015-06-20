package com.tuneit.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class Downloader extends AsyncTask<String, String, String> {
		   
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... aurl) {
		int count;
		String eurl = "http://tuneit.hostei.com/files/"+aurl[0];

		try {
			URL url = new URL(eurl);
			URLConnection conexion = url.openConnection();
			conexion.connect();
		
			int lenghtOfFile = conexion.getContentLength();
			Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);
		
			String outputS = Environment.getExternalStorageDirectory()+"/tuneIt/";
			
			File f = new File(outputS);
			if(f.exists()){
				f.mkdirs();
			}
			
			outputS += aurl[0];
			
			InputStream input = new BufferedInputStream(url.openStream());
			OutputStream output = new FileOutputStream(outputS);
			
			Log.d("DEBUG", outputS);
			
		
			byte data[] = new byte[1024];
		
			long total = 0;
	
			while ((count = input.read(data)) != -1) {
				total += count;
				publishProgress(""+(int)((total*100)/lenghtOfFile));
				output.write(data, 0, count);
			}
	
			output.flush();
			output.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	protected void onProgressUpdate(String... progress) {
		 Log.d("ANDRO_ASYNC",progress[0]);
	}

	@Override
	protected void onPostExecute(String unused) {
	}
}
/*Implementation
 * Downloader d = new Downloader();
    	try {
			d.execute("http://tuneit.hostei.com/files/audio8.3gp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 */
