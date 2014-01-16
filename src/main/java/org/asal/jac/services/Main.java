package org.asal.jac.services;

import org.apache.log4j.Logger;
import org.asal.jac.dao.DbUtils;

public class Main {
	private static Logger logger=Logger.getLogger(Main.class);
	private static String defaultPath;

	public Main() {
	
	}
	
	public static void main(String[] args){
		
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
