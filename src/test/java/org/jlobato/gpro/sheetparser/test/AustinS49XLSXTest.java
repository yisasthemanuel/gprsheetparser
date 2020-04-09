package org.jlobato.gpro.sheetparser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.jlobato.gpro.sheetparser.RaceLapInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jlobato
 *
 */
public class AustinS49XLSXTest extends BaseSheetParserTest {
	@Before
	public void setUp() throws Exception {
		//Cargamos la hoja de cálculo
		loadFromClassPath("/xls/Austin_S49.xlsx");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(model);
		assertNotNull(model.getSeasonNumber());
		assertEquals(49, model.getSeasonNumber().intValue());
		assertEquals("Austin", model.getTrackName());
		assertEquals(57, model.getLapCount());
		
		RaceLapInfo fistLap = model.getLapsList().get(0);
		RaceLapInfo lastLap =   model.getLapsList().get(model.getLapCount() - 1);

		assertEquals(7,  fistLap.getLapPosition());
		assertEquals(0,  fistLap.getLapNumber());
		assertEquals(19,  fistLap.getLapTemperature());
		assertEquals(21,  fistLap.getLapHumidity());
		assertEquals("-",  fistLap.getLapEvent());
		
		assertEquals(2,  lastLap.getLapPosition());
		assertEquals(56,  lastLap.getLapNumber());
		assertEquals(24,  lastLap.getLapTemperature());
		assertEquals(17,  lastLap.getLapHumidity());
		assertEquals("-",  lastLap.getLapEvent());
		assertEquals("1:49.345",  lastLap.getLapTime());
		
		
		assertEquals(34, model.getOvertakeRisks());
		assertEquals(69, model.getDefendRisks());
		assertEquals(100, model.getClearDryRisks());
		assertEquals(5, model.getElectronicsLevel());
		assertEquals(61, model.getElectronicsStartWear());
		assertEquals(75, model.getElectronicsEndWear());
		assertEquals(58, model.getStartFuel());
		assertEquals(3, model.getStopsCount());
		assertEquals(1, model.getStopsList().get(0).getStopFuelLeft());
		assertEquals(58, model.getStopsList().get(0).getStopRefilledLitres());
		assertEquals(22992, model.getStopsList().get(0).getStopPitTime());
		assertNotNull(model.getEndTyres());
		assertNotNull(model.getEndFuel());
		
		//Race problems
		assertEquals(0, model.getTechProblemsList().size());
		
		assertEquals(14, model.getEndTyres().intValue());
		assertEquals(1, model.getEndFuel().intValue());
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.EXCEL_PARSER;
	}

}
