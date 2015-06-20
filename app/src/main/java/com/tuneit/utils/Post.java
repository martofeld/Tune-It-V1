package com.tuneit.utils;

public class Post {
	private static Connect c = new Connect();
	private static String parameters;
	
	public static boolean login(String user, String password){
		parameters = "type=login&user="+user+"&password="+password;
		try {
			if(c.makePost(parameters).indexOf("ok") >= 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean register(String name, String last, String mail, String user, String password){
		parameters = "type=register";
		parameters += "&name="+name;
		parameters += "&last="+last;
		parameters += "&mail="+mail;
		parameters += "&user="+user;
		parameters += "&pass="+password;
		String res = null;
		try {
			res = c.makePost(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(res.indexOf("ok") > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean newMatch(String myId, String otherid, String songName, String filename){
		parameters = "type=newMatch&id="+myId+"&otherid="+otherid+"&song="+songName+"&filename="+filename;
		try {
			if(c.makePost(parameters).indexOf("ok") >= 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
}
