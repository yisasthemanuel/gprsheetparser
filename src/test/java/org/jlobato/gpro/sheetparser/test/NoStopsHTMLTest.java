package org.jlobato.gpro.sheetparser.test;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.junit.Before;

public class NoStopsHTMLTest extends NoStopsTest {

	@Before
	public void setUp() throws Exception {
		loadFromClassPath("/html/Portimao_S40.xls");
	}
	
	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.HTML_PARSER;
	}

}
