package org.jlobato.gpro.sheetparser.test;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.junit.Before;

public class IrungattukottaiS49HTMLTest extends IrungattukottaiS49XLSXTest {
	@Before
	public void setUp() throws Exception {
		//Cargamos la hoja de cálculo
		loadFromClassPath("/html/Irungattukottai_S49.xls");
	}
	
	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.HTML_PARSER;
	}



}
