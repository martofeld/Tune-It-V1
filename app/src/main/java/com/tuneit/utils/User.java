package com.tuneit.utils;


public class User{
	String username, id;
	int puntos;
	
	public User(){
		
	}
	
	public User(String username, String id, int puntos) {
		this.username = username;
		this.puntos = puntos;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}