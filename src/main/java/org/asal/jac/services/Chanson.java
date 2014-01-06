package org.asal.jac.services;

public class Chanson {
	
	private int id;
	private int codeChanson;
	private String nom;
	private int duree;
	private int ALBUMID;

	public Chanson(int codeChanson,String nom, int duree) {
		super();
		this.codeChanson=codeChanson;
		this.nom = nom;
		this.duree = duree;
	}
	
	public Chanson() {
		super();
	}
	
	
	public int getCodeChanson() {
		return codeChanson;
	}

	public void setCodeChanson(int codeChanson) {
		this.codeChanson = codeChanson;
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

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getALBUMID() {
		return ALBUMID;
	}

	public void setALBUMID(int aLBUMID) {
		ALBUMID = aLBUMID;
	}

}
