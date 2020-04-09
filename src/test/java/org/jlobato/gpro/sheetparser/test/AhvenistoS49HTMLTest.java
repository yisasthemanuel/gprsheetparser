package org.jlobato.gpro.sheetparser.test;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.junit.Before;

public class AhvenistoS49HTMLTest extends AhvenistoS49XLSXTest {
	
	@Before
	public void setUp() throws Exception {
		loadFromClassPath("/html/Ahvenisto_S49.xls");
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.HTML_PARSER;
	}
	
	

}
