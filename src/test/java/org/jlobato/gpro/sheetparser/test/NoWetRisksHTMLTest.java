package org.jlobato.gpro.sheetparser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.jlobato.gpro.sheetparser.RaceLapInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NoWetRisksHTMLTest extends BaseSheetParserTest {

	@Before
	public void setUp() throws Exception {
		loadFromClassPath("/html/Monza_S24.xls");		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.HTML_PARSER;
	}
	
	@Test
	public void test() {
		assertNotNull(model);
		assertNotNull(model.getSeasonNumber());
		assertEquals(24, model.getSeasonNumber().intValue());
		assertEquals("Monza", model.getTrackName());
		assertEquals(54, model.getLapCount());
		
		RaceLapInfo firstLap = model.getLapsList().get(0);
		RaceLapInfo lastLap =   model.getLapsList().get(model.getLapCount() - 1);
		RaceLapInfo fortyEightLap = model.getLapsList().get(48);

		//First lap
		assertEquals(21,  firstLap.getLapPosition());
		assertEquals(0,  firstLap.getLapNumber());
		assertEquals(12,  firstLap.getLapTemperature());
		assertEquals(13,  firstLap.getLapHumidity());
		assertEquals("-",  firstLap.getLapEvent());
		assertEquals("-",  firstLap.getLapTime());
		assertEquals("Partially Cloudy", firstLap.getLapWeather());
		
		//48 lap		
		assertEquals(20,  fortyEightLap.getLapPosition());
		assertEquals(48,  fortyEightLap.getLapNumber());
		assertEquals(14,  fortyEightLap.getLapTemperature());
		assertEquals(9,  fortyEightLap.getLapHumidity());
		assertEquals("-",  fortyEightLap.getLapEvent());
		assertEquals("1:24.506",  fortyEightLap.getLapTime());
		assertEquals("Partially Cloudy", fortyEightLap.getLapWeather());
		
		//Last lap		
		assertEquals(20,  lastLap.getLapPosition());
		assertEquals(53,  lastLap.getLapNumber());
		assertEquals(12,  lastLap.getLapTemperature());
		assertEquals(9,  lastLap.getLapHumidity());
		assertEquals("-",  lastLap.getLapEvent());
		assertEquals("1:24.803",  lastLap.getLapTime());
		assertEquals("Partially Cloudy", lastLap.getLapWeather());
		
		//Race risks
		assertEquals(40, model.getOvertakeRisks());
		assertEquals(19, model.getDefendRisks());
		assertEquals(79, model.getClearDryRisks());
		
		//Aquí está la clave del test. Los clearwetrisks deben ser iguales a los cleardryrisks cuando no se especifican
		assertEquals(79, model.getClearWetRisks());
		
		assertEquals(0, model.getMalfunctRisks());
		
		//Race setup
		assertEquals(154, model.getFrontWingSetup());
		assertEquals(294, model.getRearWingSetup());
		assertEquals(999, model.getEngineSetup());
		assertEquals(396, model.getBrakesSetup());
		assertEquals(758, model.getGearboxSetup());
		assertEquals(853, model.getSuspensionSetup());
		
		
		//Race car levels
		assertEquals(5, model.getChassisLevel());
		assertEquals(6, model.getEngineLevel());
		assertEquals(6, model.getFrontWingLevel());
		assertEquals(6, model.getRearWingLevel());
		assertEquals(6, model.getUnderbodyLevel());
		assertEquals(5, model.getSidepodsLevel());
		assertEquals(5, model.getCoolingLevel());
		assertEquals(6, model.getGearboxLevel());
		assertEquals(5, model.getBrakesLevel());
		assertEquals(5, model.getSuspensionLevel());
		assertEquals(6, model.getElectronicsLevel());
		
		//Race car wears
		//Start
		assertEquals(0, model.getChassisStartWear());
		assertEquals(0, model.getEngineStartWear());
		assertEquals(17, model.getFrontWingStartWear());
		assertEquals(20, model.getRearWingStartWear());
		assertEquals(20, model.getUnderbodyStartWear());
		assertEquals(71, model.getSidepodsStartWear());
		assertEquals(67, model.getCoolingStartWear());
		assertEquals(25, model.getGearboxStartWear());
		assertEquals(30, model.getBrakesStartWear());
		assertEquals(0, model.getSuspensionStartWear());
		assertEquals(86, model.getElectronicsStartWear());
		//End
		assertEquals(19, model.getChassisEndWear());
		assertEquals(38, model.getEngineEndWear());
		assertEquals(28, model.getFrontWingEndWear());
		assertEquals(28, model.getRearWingEndWear());
		assertEquals(37, model.getUnderbodyEndWear());
		assertEquals(86, model.getSidepodsEndWear());
		assertEquals(78, model.getCoolingEndWear());
		assertEquals(53, model.getGearboxEndWear());
		assertEquals(67, model.getBrakesEndWear());
		assertEquals(19, model.getSuspensionEndWear());
		assertEquals(97, model.getElectronicsEndWear());
		
		//Race start
		assertEquals(80, model.getStartFuel());
		
		//Race stops
		assertEquals(2, model.getStopsCount());
		//First stop
		assertEquals(19, model.getStopsList().get(0).getStopLapNumber());
		assertEquals("No more fuel was left", model.getStopsList().get(0).getStopReason());
		assertEquals(19, model.getStopsList().get(0).getStopTyresCondition());
		assertEquals(1, model.getStopsList().get(0).getStopFuelLeft());
		assertEquals(80, model.getStopsList().get(0).getStopRefilledLitres());
		assertEquals(22239, model.getStopsList().get(0).getStopPitTime());
		//Second stop
		assertEquals(38, model.getStopsList().get(1).getStopLapNumber());
		assertEquals("No more fuel was left", model.getStopsList().get(1).getStopReason());
		assertEquals(17, model.getStopsList().get(1).getStopTyresCondition());
		assertEquals(1, model.getStopsList().get(1).getStopFuelLeft());
		assertEquals(80, model.getStopsList().get(1).getStopRefilledLitres());
		assertEquals(23528, model.getStopsList().get(1).getStopPitTime());
		
		//Race problems
		assertEquals(0, model.getTechProblemsList().size());

		//Race end values
		assertNotNull(model.getEndTyres());
		assertNotNull(model.getEndFuel());
		
		assertEquals(35, model.getEndTyres().intValue());
		assertEquals(17, model.getEndFuel().intValue());
		
		//Test específico para los wet risks
		try {
			getSheetFromClassPath("/A1-Ring_S24.xls");
		} catch (Exception e) {
			e.printStackTrace();
			//No debería dar error
			fail(e.getMessage());
		}
		
		assertEquals(100, model.getClearWetRisks());
		assertEquals(100, model.getClearDryRisks());
		assertEquals(0, model.getMalfunctRisks());
		
		//Race problems
		assertEquals(0, model.getTechProblemsList().size());
		//assertEquals(62, model.getTechProblemsList().get(0).getLapInfo());
		//assertEquals("The suspension broke and made the car undrivable", model.getTechProblemsList().get(0).getTechProblemInfo());

		try {
			getSheetFromClassPath("/Barcelona_S24.xls");
		} catch (Exception e) {
			e.printStackTrace();
			//No debería dar error
			fail(e.getMessage());
		}
		
		assertEquals(0, model.getClearWetRisks());
		assertEquals(0, model.getClearDryRisks());
		assertEquals(0, model.getMalfunctRisks());
		
		//Race problems
		assertEquals(0, model.getTechProblemsList().size());
		
		try {
			getSheetFromClassPath("/Fiorano_S23.xls");
		} catch (Exception e) {
			e.printStackTrace();
			//No debería dar error
			fail(e.getMessage());
		}
		
		assertEquals(50, model.getClearWetRisks());
		assertEquals(50, model.getClearDryRisks());
		assertEquals(0, model.getMalfunctRisks());
		
		//Race problems
		assertEquals(0, model.getTechProblemsList().size());
		//assertEquals(6, model.getTechProblemsList().get(0).getLapInfo());
		//assertEquals("The rear wing was broken and had to be replaced", model.getTechProblemsList().get(0).getTechProblemInfo());
		
		try {
			getSheetFromClassPath("/Poznan_S23.xls");
		} catch (Exception e) {
			e.printStackTrace();
			//No debería dar error
			fail(e.getMessage());
		}
		
		assertEquals(5, model.getClearWetRisks());
		assertEquals(5, model.getClearDryRisks());
		assertEquals(0, model.getMalfunctRisks());
		
		//Race problems
		assertEquals(1, model.getTechProblemsList().size());
		assertEquals(48, model.getTechProblemsList().get(0).getLapInfo());
		assertEquals("The engine was losing power", model.getTechProblemsList().get(0).getTechProblemInfo());
		
	}
	
	void getSheetFromClassPath(String path) throws Exception {
		loadFromClassPath("/html" + path);
	}
	
	
}
