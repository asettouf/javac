package org.asal.jac.services;

import java.util.Set;

public class Artiste {
	
	private int id;
	private int codeArtiste;
	private String nom;
	private Set<Album> albums;
	
	public Artiste(int codeArtiste,String nom, Set<Album> albums) {
		super();
		this.codeArtiste=codeArtiste;
		this.nom = nom;
		this.albums = albums;
	}

	
	public Artiste() {
		// TODO Auto-generated constructor stub
	}

	
	public int getCodeArtiste() {
		return codeArtiste;
	}


	public void setCodeArtiste(int codeArtiste) {
		this.codeArtiste = codeArtiste;
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

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

}
