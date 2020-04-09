package org.jlobato.gpro.sheetparser;

import java.io.File;
import java.io.FileInputStream;

/**
 * 
 * @author JLOBATO
 *
 */
public class Main {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		FileInputStream file = new FileInputStream(new File("./src/test/resources/" + args[0]));
		
		GPRORaceSheetParser parser = GPRORaceSheetParserFactory.create(file);	
		RaceDataSheetModel model = parser.readRaceDataSheet();
		
		System.out.println("Modelo: " + model);

	}
}
