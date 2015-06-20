package com.tuneit.utils;

public class Partida{

	private String id;
	private String me;
	private String partner; //the other player
	private String song; //which song is being played
	private String a1;
	private String a2;
	private String a3;
	private String genre; //gender of song
	private String filename;

	public Partida(String id, String me, String partner, String song, String a1, String a2, String a3, String genre, String filename) {
        this.id = id;
		this.me = me;
        this.partner= partner;
        this.song = song;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.genre = genre;
        this.filename = filename;
    }
	
	public String getId(){
		return id;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getFilename(){
		return filename;
	}
	
	public String getA1(){
		return a1;
		
	}
	public String getA2(){
		return a2;
		
	}
	public String getA3(){
		return a3;
		
	}
	
}
