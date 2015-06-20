package com.tuneit.utils;

import java.io.Serializable;

public class Song implements Serializable{
	
	private String id;
	private String name;
	private String artist;
	private String gender;
	private String link;
	
	public Song(){
		
	}

	public Song(String id, String name, String artist, String gender, String link) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.gender = gender;
		this.link = link;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
