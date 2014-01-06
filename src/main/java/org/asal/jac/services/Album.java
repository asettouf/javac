package org.asal.jac.services;

import java.util.HashSet;
import java.util.Set;

public class Album {

	private int id;
	private int codeAlbum;
	private String nom;
	private Set<Chanson> chansons;
	private int ARTISTEID;
	
	public Album(int codeAlbum,String nom, Set<Chanson> chansons) {
		super();
		this.codeAlbum=codeAlbum;
		this.nom = nom;
		this.chansons = chansons;
	}
	
	public Album(){
		super();
		this.chansons=new HashSet<Chanson>();
		
	}
	
	public void addChanson(Chanson toAdd){
		this.chansons.add(toAdd);
	}
	public int getCodeAlbum() {
		return codeAlbum;
	}


	public void setCodeAlbum(int codeAlbum) {
		this.codeAlbum = codeAlbum;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Chanson> getChansons() {
		return chansons;
	}

	public void setChansons(Set<Chanson> chansons) {
		this.chansons = chansons;
	}

	public int getARTISTEID() {
		return ARTISTEID;
	}

	public void setARTISTEID(int aRTISTEID) {
		ARTISTEID = aRTISTEID;
	}

}
