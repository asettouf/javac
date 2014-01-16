/******************IMPORTANT**************************************
 * PLEASE BE SURE TO READ THE README BEFORE LAUNCHING THE PROGRAM!
 *******************************************************************/


package org.asal.jac.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.asal.jac.dao.DbUtils;

public class Main {
	private static Logger logger=Logger.getLogger(Main.class);
	private static String defaultPath;
	public static String defaultdb;

	public Main() {
	
	}
	
	public static String dateToString(){
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
    	String now=sdf.format(cal.getTime());
    	return now;
	}
	
	public static void main(String[] args){
		
		defaultdb="jdbc:hsqldb:hsql://localhost/";
		logger.info("Starting data writing program main function");
		defaultPath=System.getProperty("user.home");
		logger.info("Default path is currently : "+defaultPath);
		for (int i=0;i<args.length;i++){
			String s = args[i];
			if (s.contains("initdb")){
				logger.info("Initialising database");
				DbUtils.populate();		
			}
			if(s.contains("directory")){
				if (i+1<=args.length){
					logger.info("Initialising default directory : "+args[i+1]);
					defaultPath+="\\"+args[i+1];
				}
				
				else{
					defaultPath+="\\MusicToRead";
					logger.warn("Warning no directory indicated, using instead : "+defaultPath);
				}
			}
			if(s.contains("dbname")){
				if (i+1<=args.length){
					logger.info("Initialising default db : "+args[i+1]);
					defaultdb+=args[i+1];
				}
				
				else{
					defaultdb+="db";
					logger.warn("Warning no db indicated, using instead : "+defaultdb);
				}
			}
			
		}
		if(defaultdb.equals("jdbc:hsqldb:hsql://localhost/")){
			defaultdb+="db";
			logger.warn("Initialising db to its default value : "+defaultdb);
		}
		if(defaultPath.equals(System.getProperty("user.home"))){
				defaultPath+="\\MusicToRead";
				logger.info("No directory indicated, using instead : "+defaultPath);
			}
		
		DbUtils.writeToDb(defaultPath);
		logger.info("End of data writing program main function");
		
	}

	public static String getDefaultPath() {
		return defaultPath;
	}

	public static void setDefaultPath(String defaultPath) {
		Main.defaultPath = defaultPath;
	}
	
	

}
