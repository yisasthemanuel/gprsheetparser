package org.jlobato.gpro.sheetparser;

import java.io.InputStream;

/**
 * 
 * @author JLOBATO
 *
 */
public class GPRORaceSheetParserFactory {
	
	/**
	 * 
	 */
	public static final int EXCEL_PARSER = 0;
	
	/**
	 * 
	 */
	public static final int HTML_PARSER = 1;
	
	/**
	 * 
	 * @param is
	 * @return
	 */
	public static GPRORaceSheetParser create(InputStream is) {
		return create(is, HTML_PARSER);
	}
	
	/**
	 * 
	 * @param is
	 * @param parserType
	 * @return
	 */
	public static GPRORaceSheetParser create(InputStream is, int parserType) {
		//TODO: Utilizar patrón prototype con Spring para crear parsers
		GPRORaceSheetParser parser = null;

		switch (parserType) {
		case EXCEL_PARSER:
			parser = new ExcelSheetParser(is);
			break;
		case HTML_PARSER:
			parser = new RaceHtmlParser(is);
			break;
		default:
			parser = new RaceHtmlParser(is);
			break;

		}
		return parser;
	}
	
}
