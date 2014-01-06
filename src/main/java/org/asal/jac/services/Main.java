package org.asal.jac.services;

import org.asal.jac.dao.DbUtils;

public class Main {

	public Main() {
	
		
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		DbUtils.populate();		
		DbUtils.writeToDb("C:\\Users\\Adoo\\Desktop\\test");
		
	}
	
	

}
