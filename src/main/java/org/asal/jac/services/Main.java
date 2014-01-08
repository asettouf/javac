package org.asal.jac.services;

import org.apache.log4j.Logger;
import org.asal.jac.dao.DbUtils;

public class Main {
	private static Logger logger=Logger.getLogger(Main.class);

	public Main() {
	
	}
	
	public static void main(String[] args){

		logger.info("Starting data writing program main function");
		DbUtils.populate();		
		DbUtils.writeToDb("C:\\Users\\Adoo\\Desktop\\test");
		logger.info("End of data writing program main function");
		
	}
	
	

}
