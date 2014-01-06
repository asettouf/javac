package org.asal.jac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.asal.jac.services.Album;
import org.asal.jac.services.Artiste;
import org.asal.jac.services.Chanson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DbUtils {
	
	private static SessionFactory sFact;
	
	private final static String url="jdbc:hsqldb://localhost/db";
	private final static String createArtiste="CREATE TABLE ARTISTE (ID INTEGER NOT NULL, CODEARTISTE INTEGER NOT NULL,NOM CHAR(25) NOT NULL,  PRIMARY KEY(ID))";
	private final static String createAlbum="CREATE TABLE ARTISTE (ID INTEGER NOT NULL, CODEALBUM INTEGER NOT NULL,NOM CHAR(25) NOT NULL, ARTISTEID INTEGER NOT NULL,  PRIMARY KEY(ID))";
	private final static String createChanson="CREATE TABLE ARTISTE (ID INTEGER NOT NULL, CODECHANSON INTEGER NOT NULL,NOM CHAR(25) NOT NULL, DUREE INTEGER NOT NULL, ALBUMID INTEGER NOT NULL,  PRIMARY KEY(ID))";
	
	public DbUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static void loadDriver() throws ClassNotFoundException{
		Class.forName("org.hsqldb.jdbcDriver");
	}
	
	public static Connection newConn() throws SQLException{
		Connection conn=DriverManager.getConnection(url);
		return conn;
	}
	
	//script to populate the database in the first place
	public static void populate(){
		//loading driver
		try {
			loadDriver();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//connecting to database
		Connection conn=null;
		try {
			conn=newConn();
			Statement st=conn.createStatement();
			//batch creating the required tables
			st.execute(createArtiste);
			st.execute(createAlbum);
			st.execute(createChanson);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	//write info on db, please specify the directory to find files
	public static void writeToDb(String directory){
		sFact=new Configuration().configure().buildSessionFactory();
		Session session=sFact.openSession();
		session.beginTransaction();
		HashMap<Artiste,HashMap<Album, Chanson>> toWrite = FileParser.extractAlbumWithArtiste(directory);
		if (toWrite!=null){
			for(Map.Entry<Artiste, HashMap<Album, Chanson>> entry :toWrite.entrySet()){
				session.save(entry.getKey());
				HashMap<Album,Chanson> albumChansonHash=entry.getValue();
				if (albumChansonHash!=null){
					for(Map.Entry<Album,Chanson> entry2:albumChansonHash.entrySet()){
						session.save(entry2.getKey());
						session.save(entry2.getValue());
					}
				}
			}
		}
		session.getTransaction().commit();
		session.close();
		sFact.close();
			
		
		
	}

}
