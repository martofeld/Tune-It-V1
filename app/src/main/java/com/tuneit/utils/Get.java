package com.tuneit.utils;

import java.util.ArrayList;

import android.util.Log;

public class Get {
	private static Connect c = new Connect();
	private static String parameters;
	
	//GET INFORMATION ABOUT A PARTICULAR SONG
	public static Song song(String songId){
		parameters = "function=song&song="+songId;
		String response;
		try {
			response = c.makeGet(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Song();
	}
	
	//GET 4 SONGS FOR SELECTION
	public static Song[] songs(String gender, int qty){
		Song[] songs = new Song[qty];
		parameters = "function=songs&gender="+gender+"&qty="+qty;
		String response = null;
		try {
			response = c.makeGet(parameters);
			Log.e("Get Songs", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] res = spliter(response, "-");
		
		for(int i = 0; i < res.length-1; i++){
			String[] a = spliter(res[i], "@");
			Log.e("asdasd", String.valueOf(i));
			Log.e("asd", a[0]);
			Log.e("SDSDf", res[i]);
			songs[i] = new Song(a[0], a[1], a[2], gender, a[3]);
			a=null;
		}
		
		return songs;
	}
	
	//GET THE USERS PLAYS
	public static ArrayList<Partida> partidas(String myId, boolean mine){
		
		ArrayList<Partida> partidas = new ArrayList<Partida>();
		if(mine)
			parameters = "function=partidasPropias";
		else
			parameters = "function=partidasNoPropias";
		
		parameters += "&idUser="+myId;
		
		try {
			String res = c.makeGet(parameters);
			String[] response = spliter(res, "/");
			for(int i = 0; i < response.length-1; i++){
				String[] a = spliter(response[i], "@");
				partidas.add(new Partida(a[0], a[1], a[2], a[4], a[5], a[6], a[7], a[3], a[8]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return partidas;
	}
	
	//GET THE STATS OF THE USER
	public static Stats stats(String myId){
		parameters = "function=stats&idUser="+myId;
		String response = null;
		try {
			response = c.makeGet(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.e("RESPONSE", response);
		String[] res = spliter(response, "@");
		int[] stats = converter(res);
		int[] played = new int[6];
		int[] won = new int[6];
		
		for(int i = 0; i < played.length-1; i++){
			played[i] = stats[i+2];
			won[i] = stats[i+8];
		}
		
		Stats stat = new Stats(stats[0], stats[1], played, won);
		return stat;
	}
	
	public static User userById(String id){
		parameters = "function=userById&idUser="+id;
		String response = null;
		try {
			response=c.makeGet(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] res = spliter(response, "@");
		return new User(res[0], id, Integer.parseInt(res[1]));
	}
	
	public static User userByUsername(String username){
		parameters = "function=userByUsername&username="+username;
		String response = null;
		try {
			response=c.makeGet(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] res = spliter(response, "@");
		return new User(username, res[0], 0);
	}
	
	public static String randomUser(String id){
		parameters = "function=randomUser&ownId="+id;
		String response = "";
		try{
			response = c.makeGet(parameters);
		}catch(Exception e){
			e.printStackTrace();
		}
		Log.d("RESPONSE", response);
		return response;
	}
	/*-------------------------------------------------------------------------------------------*/
	private static String[] spliter(String toSplit, String spliter){
		return toSplit.split(spliter);
	}
	
	private static int[] converter(String[] toConvert){
		int[] i = new int[toConvert.length];
		for(int x = 1; x < toConvert.length-1; x++){
			i[x] = Integer.parseInt(toConvert[x]);
		}
		return i;
	}
	
}
