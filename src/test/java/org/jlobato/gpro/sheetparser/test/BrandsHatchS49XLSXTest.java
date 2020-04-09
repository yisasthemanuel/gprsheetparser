package org.jlobato.gpro.sheetparser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author JLOBATO
 *
 */
public class BrandsHatchS49XLSXTest extends BaseSheetParserTest {
	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Cargamos la hoja de cálculo
		loadFromClassPath("/xls/Brands_Hatch_S49.xlsx");
	}

	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 
	 */
	@Test
	public void testBrandsHatchS49XLSX() {
		assertNotNull(model);
		assertNotNull(model.getSeasonNumber());
		assertEquals(49, model.getSeasonNumber().intValue());
		assertEquals("Brands Hatch", model.getTrackName());
		assertEquals(76, model.getLapCount());
		assertEquals(15,  model.getLapsList().get(0).getLapPosition());
		assertEquals(7,  model.getLapsList().get(model.getLapCount() - 1).getLapPosition());
		assertEquals(19, model.getOvertakeRisks());
		assertEquals(69, model.getDefendRisks());
		assertEquals(55, model.getClearDryRisks());
		assertEquals(5, model.getElectronicsLevel());
		assertEquals(29, model.getElectronicsStartWear());
		assertEquals(45, model.getElectronicsEndWear());
		assertEquals(114, model.getStartFuel());
		assertEquals(1, model.getStopsCount());
		assertEquals(1, model.getStopsList().get(0).getStopFuelLeft());
		assertEquals(111, model.getStopsList().get(0).getStopRefilledLitres());
		assertEquals(24730, model.getStopsList().get(0).getStopPitTime());
		assertNotNull(model.getEndTyres());
		assertNotNull(model.getEndFuel());
		
		//Race problems
		assertEquals(0, model.getTechProblemsList().size());
		
		assertEquals(8, model.getEndTyres().intValue());
		assertEquals(2, model.getEndFuel().intValue());
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.EXCEL_PARSER;
	}

}
