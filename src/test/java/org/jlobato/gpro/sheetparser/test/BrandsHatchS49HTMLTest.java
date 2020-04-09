package org.jlobato.gpro.sheetparser.test;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.junit.Before;

public class BrandsHatchS49HTMLTest extends BrandsHatchS49XLSTest {

	@Before
	public void setUp() throws Exception {
		//Cargamos la hoja de cálculo
		loadFromClassPath("/html/Brands_Hatch_S49.xls");
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.HTML_PARSER;
	}

}
