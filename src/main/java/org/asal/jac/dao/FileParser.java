package org.asal.jac.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.asal.jac.services.Album;
import org.asal.jac.services.Artiste;
import org.asal.jac.services.Chanson;

public class FileParser {
	
	//Attention retour null si sub directory
	//Si un fichier !.music alors, retrait de la liste
	public static List<String> filesInDirectory(String path){
		File dir=new File(path);
		File[] fileList=dir.listFiles();
		List<String> retour = new ArrayList<String>();
		
		for (File f:fileList){
			if (!f.isFile()){
				return null;
			}
			if (!f.getName().contains(".music")){
				return null;
			}
			retour.add(f.getName());
		}
		
		for (String name:retour){
			if (!name.contains(".music")){
				retour.remove(name);
			}
		}
		return retour;
	}
	
	public static boolean checkLine(String line){
		int virgules=0;
		boolean retour=false;
		for (int i=0;i<line.length();i++){
			
			if (virgules==6){
				if (Character.toString(line.charAt(i)).matches("[0-9]")){
					retour=true;
				}
				else{
					retour=false;
				}
			}
			
			if (line.charAt(i)==','){
					virgules++;
			}
		
		}	
		return retour;
		}
	
	//Attention retour null si mauvaise données
	public static List<String> readLinesFromFile(String path){
		List<String> retour=new ArrayList<String>();
		boolean check=false;
		try {
			retour=FileUtils.readLines(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String line:retour){
			check=checkLine(line);
			if (check==false){
				return null;
			}
		}
		return retour;
	}
	
	//Watch out, it really suck dicks
	public static HashMap<Artiste, HashMap<Album, Chanson>> fillHash(String[] data){
		HashMap<Album, Chanson> mapAlbumChanson=new HashMap<Album,Chanson>();
		HashMap<Artiste,HashMap<Album, Chanson>> retour=new HashMap<Artiste,HashMap<Album,Chanson>>();
		Artiste buffArt=new Artiste();
		Album buffAlb=new Album();
		Chanson buffChan=new Chanson();
		buffArt.setCodeArtiste(Integer.parseInt(data[0]));
		buffArt.setNom(data[1]);
		buffAlb.setCodeAlbum(Integer.parseInt(data[2]));
		buffArt.setNom(data[3]);
		buffChan.setCodeChanson(Integer.parseInt(data[4]));
		buffChan.setNom(data[5]);
		buffChan.setDuree(Integer.parseInt(data[5]));
		mapAlbumChanson.put(buffAlb, buffChan);
		retour.put(buffArt, mapAlbumChanson);
		
		return retour;
	}
	
	//Boring, returns HashMap of HashMap supposedly containing everything we need
	public static HashMap<Artiste, HashMap<Album, Chanson>> extractAlbumWithArtiste(String path){
		HashMap<Album, Chanson> mapAlbumChanson=new HashMap<Album,Chanson>();
		HashMap<Artiste,HashMap<Album, Chanson>> retour = new HashMap<Artiste,HashMap<Album,Chanson>>();
		List<String> files=filesInDirectory(path);
		for (String f:files){
			List<String> data=null;
			if (f!=null)
				data=readLinesFromFile(f);
			else
				continue;
			for (String d:data){
				Artiste buffArt=new Artiste();
				Album buffAlb=new Album();
				Chanson buffChan=new Chanson();
				String[] extractArray;
				if(d!=null)
					extractArray=d.split(",");
				else
					continue;
				buffArt.setCodeArtiste(Integer.parseInt(extractArray[0]));
				buffArt.setNom(extractArray[1]);
				buffAlb.setCodeAlbum(Integer.parseInt(extractArray[2]));
				buffArt.setNom(extractArray[3]);
				buffChan.setCodeChanson(Integer.parseInt(extractArray[4]));
				buffChan.setNom(extractArray[5]);
				buffChan.setDuree(Integer.parseInt(extractArray[5]));
				mapAlbumChanson.put(buffAlb, buffChan);
				retour.put(buffArt, mapAlbumChanson);				
			}
			
		}
		return retour;
	}
	

}
