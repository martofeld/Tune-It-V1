package com.tuneit.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.StrictMode;
import android.util.Log;

public class Connect{

    private HttpURLConnection mHttpURLConnection = null;
    private String baseUrl = null;
    private URL mURL = null;
    private DataOutputStream mDataOutputStream = null;
    
    public Connect(){
        this.baseUrl = "http://tuneit.hostei.com/php/";
        this.setPolicy();
    }

    public String makeGet(String parameters) throws Exception{
        String url = "getData.php";
        
        Log.i("URL", baseUrl+url+parameters);
        mURL = new URL(baseUrl + url);
        mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
        mHttpURLConnection.setRequestMethod("GET");

        
        print(parameters);
        // Send post request
        mHttpURLConnection.setDoOutput(true);
        mDataOutputStream = new DataOutputStream(mHttpURLConnection.getOutputStream());
        mDataOutputStream.writeBytes(parameters);
        mDataOutputStream.flush();
        mDataOutputStream.close();

        String response = streamToString(mHttpURLConnection.getInputStream());
        
        return response;
    }

    public String makePost(String parameters) throws Exception{
        String url = "postData.php";
        print(baseUrl+url);
        mURL = new URL(baseUrl + url);
        mHttpURLConnection = (HttpURLConnection) mURL.openConnection();

        //add request header
        mHttpURLConnection.setRequestMethod("POST");
        
        print(parameters);
        // Send post request
        mHttpURLConnection.setDoOutput(true);
        mDataOutputStream = new DataOutputStream(mHttpURLConnection.getOutputStream());
        mDataOutputStream.writeBytes(parameters);
        mDataOutputStream.flush();
        mDataOutputStream.close();
        
        String response = streamToString(mHttpURLConnection.getInputStream());
        
        System.out.println(response);
        
        return response;
    }
    
    //================================================================================================
    private String streamToString(InputStream is){

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
        } catch (IOException e) {
            print(e.getMessage());
        }
        String[] a = sb.toString().split("<");
        return a[0];
    }

    private void print(String s){
        Log.e("DEBUG",s);
    }

    private void setPolicy(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
    }
}