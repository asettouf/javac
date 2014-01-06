package org.asal.jac.services;

public class Chanson {
	
	private int id;
	private int codeChanson;
	private String nom;
	private int duree;

	public Chanson(int codeChanson,String nom, int duree) {
		super();
		this.codeChanson=codeChanson;
		this.nom = nom;
		this.duree = duree;
	}
	
	public Chanson() {
		// TODO Auto-generated constructor stub
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

}
