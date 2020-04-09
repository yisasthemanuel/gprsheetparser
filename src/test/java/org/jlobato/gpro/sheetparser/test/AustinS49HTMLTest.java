package org.jlobato.gpro.sheetparser.test;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.junit.Before;

public class AustinS49HTMLTest extends AustinS49XLSXTest {

	@Before
	public void setUp() throws Exception {
		loadFromClassPath("/html/Austin_S49.xls");
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.HTML_PARSER;
	}
}
