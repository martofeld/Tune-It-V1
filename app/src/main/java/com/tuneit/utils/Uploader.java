package com.tuneit.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.StrictMode;
import android.util.Log;
	
public class Uploader implements Runnable{
	//constants
    private final String LINEEND = "\r\n";
    private final String HYPHENS = "--";
    private final String BOUNDARY = "*****";
    private final String TAG="UPLOADER";
    
    //objects
	private final String server = "http://tuneit.hostei.com/php/uploadFile.php";
    URL connectURL;
    String responseString;
    String Title;
    byte[ ] dataToServer;
    FileInputStream fileInputStream = null;


    public Uploader(String vTitle){
        try{
        	connectURL = new URL(server);
            Title= vTitle;
            setPolicy();
        }catch(Exception ex){
            Log.i(TAG,"URL Malformatted");
        }
    }

    public void Send_Now(FileInputStream fStream, final String filename){
        fileInputStream = fStream;
		Sending(filename);
    }

    void Sending(String filename){
        String iFileName = filename;
        try
        {
        	Log.e(TAG,"Starting Http File Sending to URL");

            // Open a HTTP connection to the URL
            HttpURLConnection conn = (HttpURLConnection)connectURL.openConnection();

            // Allow Inputs
            conn.setDoInput(true);

            // Allow Outputs
            conn.setDoOutput(true);

            // Don't use a cached copy.
            conn.setUseCaches(false);

            // Use a post method.
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");

            conn.setRequestProperty("Content-Type", "multipart/form-data;BOUNDARY="+BOUNDARY);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(HYPHENS + BOUNDARY + LINEEND);
            dos.writeBytes("Content-Disposition: form-data; name=\"title\""+ LINEEND);
            dos.writeBytes(LINEEND);
            dos.writeBytes(Title);
            dos.writeBytes(LINEEND);
            dos.writeBytes(HYPHENS + BOUNDARY + LINEEND);
                
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + iFileName +"\"" + LINEEND);
            dos.writeBytes(LINEEND);

            Log.e(TAG,"Headers are written");

            // create a buffer of maximum size
            int bytesAvailable = fileInputStream.available();
                
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[ ] buffer = new byte[bufferSize];

            // read file and write it into form...
            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0)
            {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0,bufferSize);
            }
            dos.writeBytes(LINEEND);
            dos.writeBytes(HYPHENS + BOUNDARY + HYPHENS + LINEEND);

            // close streams
            fileInputStream.close();
                
            dos.flush();
                
            Log.e(TAG,"File Sent, Response: "+String.valueOf(conn.getResponseCode()));
                 
            InputStream is = conn.getInputStream();
                
            // retrieve the response from server
            int ch;

            StringBuffer b =new StringBuffer();
            while( ( ch = is.read() ) != -1 ){ b.append( (char)ch ); }
            String s=b.toString();
            Log.i("Response",s);
            dos.close();
        }
        catch (MalformedURLException ex)
        {
            Log.e(TAG, "URL error: " + ex.getMessage(), ex);
        }

        catch (IOException ioe)
        {
            Log.e(TAG, "IO error: " + ioe.getMessage(), ioe);
        }
    }

    @Override
    public void run() {
            // TODO Auto-generated method stub
    }
    
    private void setPolicy(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
    }
    
	public FileInputStream getInputStream(String filepath){
		File f = new File(filepath);
		FileInputStream fstrm = null;
		if(f.exists()){
			try {
				fstrm = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return fstrm;
		}else{
			Log.d("File doesn't exist", "NO EXISTO");
			return null;
		}
	}
}
/* Implement code
 * try {
    		String filepath = Environment.getExternalStorageDirectory().toString()+"/tuneIt/audio8.3gp";
    		File f = new File(filepath);
    		if(f.exists()){
    	    // Set your file path here
    			FileInputStream fstrm = new FileInputStream(f);
    			Uploader hfu = new Uploader("audio2");
    			
    			String filename = filepath.split("/")[filepath.split("/").length-1];
        	    
    			hfu.Send_Now(fstrm, filename);
    		}else{
    			Log.d("File doesn't exist", "NO EXISTO");
    		}
    	    

    	  } catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	}
 * 
 * */