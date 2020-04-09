package org.jlobato.gpro.sheetparser.test;

import java.io.InputStream;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParser;
import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.jlobato.gpro.sheetparser.RaceDataSheetModel;

/**
 * 
 * @author JLOBATO
 *
 */
public abstract class BaseSheetParserTest {
	/**
	 * 
	 */
	InputStream sheetInputStream;
	
	/**
	 * 
	 */
	GPRORaceSheetParser parser;
	
	/**
	 * 
	 */
	RaceDataSheetModel model;
	
	/**
	 * 
	 * @param sheetPath
	 * @throws Exception
	 */
	void loadFromClassPath(String sheetPath) throws Exception {
		//Cargamos la hoja de cálculo
		loadFromClassPath(sheetPath, getParserType());
	}
	
	/**
	 * 
	 * @param sheetPath
	 * @throws Exception
	 */
	void loadFromClassPath(String sheetPath, int parserType) throws Exception {
		//Cargamos la hoja de cálculo
		sheetInputStream = getClass().getResourceAsStream(sheetPath);
		parser = GPRORaceSheetParserFactory.create(sheetInputStream, parserType);
		model = parser.readRaceDataSheet();
	}
	
	public abstract int getParserType();

}
