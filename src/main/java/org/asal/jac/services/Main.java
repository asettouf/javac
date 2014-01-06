package org.asal.jac.services;

import org.asal.jac.dao.*;

public class Main {

	public Main() {
	
		
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		DbUtils.populate();
		DbUtils.writeToDb(args[0]);
	}
	
	

}
