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
	private static File rightArchiveDir=new File("C:\\Users\\Adoo\\Desktop\\test\\right");
	private  static File wrongArchiveDir=new File("C:\\Users\\Adoo\\Desktop\\test\\wrong");

	//Attention retour null si sub directory
	//Si un fichier !.music alors, retrait de la liste
	public static List<String> filesInDirectory(String path){
		File dir=new File(path);
		File[] fileList;
		if(dir.exists())
			fileList=dir.listFiles();
		else
			fileList=null;
		List<String> retour = new ArrayList<String>();
		
		for (File f:fileList){
			System.out.println(f.toString());
			if (!f.isFile()){
				System.out.println("file "+f.getName()+" null");
				continue;
			}
			System.out.println(f.getAbsolutePath());
			retour.add(f.getAbsolutePath());
		}
		return retour;
	}
	
	public static boolean checkLine(String line){
		boolean retour=false;
		String buff=line.replaceAll("\\s+","");
		System.out.println(line);
		String[] extractLine=buff.split(",");
		if (extractLine[0].matches("[0-9]*")&&extractLine[2].matches("[0-9]*")&&extractLine[4].matches("[0-9]*")&&extractLine[6].matches("[0-9]*")){
			System.out.println("Number Artiste ok");
			retour=true;
		}		
		else{
			System.out.println("Wrong format");
			retour=false;
		}
		return retour;
		}
	
	
	//Attention retour null si mauvaise données
	public static List<String> readLinesFromFile(String path){
		List<String> retour=new ArrayList<String>();
		boolean check=false;
		try {
			retour=FileUtils.readLines(new File(path));
			System.out.println("File found: "+path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String line:retour){
			check=checkLine(line);
			if (check==false){
				System.out.println("Check wrong peace out");//archive file in wrong dir -> toDo
				archiveFile(path, wrongArchiveDir);
				return null;
			}
			else
				System.out.println("Check correct");
		}
		return retour;
	}
	
	public static void archiveFile(String file,File destDir){
		File fileToArchive=new File(file);
		try {
			FileUtils.moveFileToDirectory(fileToArchive, destDir, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//Boring, returns HashMap of HashMap supposedly containing everything we need
	public static HashMap<Artiste, HashMap<Album, Chanson>> extractAlbumWithArtiste(String path){
		HashMap<Album, Chanson> mapAlbumChanson=new HashMap<Album,Chanson>();
		HashMap<Artiste,HashMap<Album, Chanson>> retour = new HashMap<Artiste,HashMap<Album,Chanson>>();
		List<String> files=filesInDirectory(path);
		for (String f:files){
			List<String> data=null;
			if (!f.contains(".music")){
				archiveFile(f,wrongArchiveDir);
				continue;
			}
			data=readLinesFromFile(f);
			if (new File(f).exists()){
				archiveFile(f,rightArchiveDir);
			}
				if (data!=null){
					ArrayList<Artiste> memArtiste=new ArrayList<Artiste>();
					int p=0;
					for (String d:data){
						Artiste buffArt=new Artiste();
						Album buffAlb=new Album();
						Chanson buffChan=new Chanson();
						String[] extractArray;
						if(d!=null){
							System.out.println("String correct");
							extractArray=d.replaceAll("\\s+","").split(",");
						}
						else{
							System.out.println("String null");
							continue;
						}
						
						buffArt.setCodeArtiste(Integer.parseInt(extractArray[0]));
						buffArt.setNom(extractArray[1]);
						memArtiste.add(buffArt);
						if(p!=0&&!memArtiste.get(p).getNom().equals(memArtiste.get(p-1).getNom())){
							retour.put(memArtiste.get(p-1), mapAlbumChanson);
							System.out.println("fuck you");
							mapAlbumChanson=new HashMap<Album,Chanson>();
						}
						buffAlb.setCodeAlbum(Integer.parseInt(extractArray[2]));
						buffAlb.setNom(extractArray[3]);
						buffChan.setCodeChanson(Integer.parseInt(extractArray[4]));
						buffChan.setNom(extractArray[5]);
						buffChan.setDuree(Integer.parseInt(extractArray[6]));
						mapAlbumChanson.put(buffAlb, buffChan);
						p++;
					}
					retour.put(memArtiste.get(p-1), mapAlbumChanson);
					mapAlbumChanson=new HashMap<Album,Chanson>();
			}
		}
		return retour;
	}
	
	
	

}
