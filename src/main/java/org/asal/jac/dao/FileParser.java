package org.asal.jac.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.asal.jac.services.Album;
import org.asal.jac.services.Artiste;
import org.asal.jac.services.Chanson;
import org.asal.jac.services.Main;

public class FileParser {
	private static File rightArchiveDir=new File(Main.getDefaultPath()+"\\right");
	private  static File wrongArchiveDir=new File(Main.getDefaultPath()+"\\wrong");
	
	private static Logger logger=Logger.getLogger(FileParser.class);


	//Attention retour null si sub directory
	//Si un fichier !.music alors, retrait de la liste
	public static List<String> filesInDirectory(String path){
		logger.info("Starting to list files");
		File dir=new File(path);
		File[] fileList;
		if(dir.exists())
			fileList=dir.listFiles();
		else

			logger.warn("Directory not found"+path);
			fileList=null;
		List<String> retour = new ArrayList<String>();
		
		for (File f:fileList){
			System.out.println(f.toString());
			if (!f.isFile()){

				logger.warn("File invalid : "+f);
				continue;
			}
			System.out.println(f.getAbsolutePath());
			retour.add(f.getAbsolutePath());
		}
		logger.info("Listing done");
		return retour;
	}
	
	public static boolean checkLine(String line){
		boolean retour=false;
		String buff=line.replaceAll("\\s+","");
		logger.info("Starting to check line : "+line);
		String[] extractLine=buff.split(",");
		if (extractLine[0].matches("[0-9]*")&&extractLine[2].matches("[0-9]*")&&extractLine[4].matches("[0-9]*")&&extractLine[6].matches("[0-9]*")){
			logger.info("Line correct : "+line);
			retour=true;
		}		
		else{
			logger.info("Line wrong : "+line);
			retour=false;
		}
		return retour;
		}
	
	
	//Attention retour null si mauvaise données
	public static List<String> readLinesFromFile(String path){
		List<String> retour=new ArrayList<String>();
		logger.info("Reading lines from file : "+path);
		boolean check=false;
		try {
			retour=FileUtils.readLines(new File(path));
			logger.info("File found: "+path);
		} catch (IOException e) {
			logger.warn("File not found"+path);
			e.printStackTrace();
		}
		
		for (String line:retour){
			check=checkLine(line);
			if (check==false){
				logger.info("File invalid : "+path+"\nIt will be stocked in"+wrongArchiveDir);//archive file in wrong dir -> toDo
				archiveFile(path, wrongArchiveDir);
				return null;
			}
			else
				logger.info("File : "+path+" correct\nStored in "+rightArchiveDir);
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
		logger.info("Starting to extract datas");
		HashMap<Album, Chanson> mapAlbumChanson=new HashMap<Album,Chanson>();
		HashMap<Artiste,HashMap<Album, Chanson>> retour = new HashMap<Artiste,HashMap<Album,Chanson>>();
		List<String> files=filesInDirectory(path);
		logger.info("Datas extracted");
		for (String f:files){
			List<String> data=null;
			if (!f.contains(".music")){
				logger.info("Wrong file, archived in directory : "+wrongArchiveDir);
				archiveFile(f,wrongArchiveDir);
				continue;
			}
			data=readLinesFromFile(f);
			if (new File(f).exists()){
				logger.info("File correct,archived in directory "+rightArchiveDir);
				archiveFile(f,rightArchiveDir);
			}
			logger.info("Starting to write datas for file "+f+" in a Hash");
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

		logger.info("Writing completed");
		return retour;
	}
	
	
	

}
