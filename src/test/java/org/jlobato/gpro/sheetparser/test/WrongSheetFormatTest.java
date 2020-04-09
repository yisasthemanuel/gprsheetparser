package org.jlobato.gpro.sheetparser.test;

import static org.junit.Assert.*;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WrongSheetFormatTest extends BaseSheetParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		boolean error = false;
		
		try {
			loadFromClassPath("/xls/Portimao_S40_sinstints_niformato.xls");
		} catch (Exception e) {
			error = true;
		}
		
		assertEquals(error, true);
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.EXCEL_PARSER;
	}

}
