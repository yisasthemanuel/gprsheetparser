package org.jlobato.gpro.sheetparser.test;

import static org.junit.Assert.*;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.jlobato.gpro.sheetparser.RaceLapInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NoStopsTest extends BaseSheetParserTest {

	@Before
	public void setUp() throws Exception {
		loadFromClassPath("/xls/Portimao_S40_sinstints.xlsx");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(model);
		assertNotNull(model.getSeasonNumber());
		assertEquals(40, model.getSeasonNumber().intValue());
		assertEquals("Portimao", model.getTrackName());
		assertEquals(67, model.getLapCount());
		
		RaceLapInfo firstLap = model.getLapsList().get(0);
		RaceLapInfo lastLap =   model.getLapsList().get(model.getLapCount() - 1);
		RaceLapInfo fortyEightLap = model.getLapsList().get(48);

		//First lap
		assertEquals(38,  firstLap.getLapPosition());
		assertEquals(0,  firstLap.getLapNumber());
		assertEquals(31,  firstLap.getLapTemperature());
		assertEquals(1,  firstLap.getLapHumidity());
		assertEquals("-",  firstLap.getLapEvent());
		assertEquals("-",  firstLap.getLapTime());
		assertEquals("Partially Cloudy", firstLap.getLapWeather());
		
		//48 lap		
		assertEquals(39,  fortyEightLap.getLapPosition());
		assertEquals(48,  fortyEightLap.getLapNumber());
		assertEquals(34,  fortyEightLap.getLapTemperature());
		assertEquals(1,  fortyEightLap.getLapHumidity());
		assertEquals("Dropped out",  fortyEightLap.getLapEvent());
		assertEquals("-",  fortyEightLap.getLapTime());
		assertEquals("Partially Cloudy", fortyEightLap.getLapWeather());
		
		//Last lap		
		assertEquals(39,  lastLap.getLapPosition());
		assertEquals(66,  lastLap.getLapNumber());
		assertEquals(35,  lastLap.getLapTemperature());
		assertEquals(2,  lastLap.getLapHumidity());
		assertEquals("Dropped out",  lastLap.getLapEvent());
		assertEquals("-",  lastLap.getLapTime());
		assertEquals("Cloudy", lastLap.getLapWeather());
		
		//Race risks
		assertEquals(0, model.getOvertakeRisks());
		assertEquals(0, model.getDefendRisks());
		assertEquals(90, model.getClearDryRisks());
		//Viene con un guión y eso es lo mismo que si no vinieran estos riesgos, luego deben ser igual que los cleardryrisks
		assertEquals(90, model.getClearWetRisks());
		assertEquals(100, model.getMalfunctRisks());
		
		//Race setup
		assertEquals(767, model.getFrontWingSetup());
		assertEquals(727, model.getRearWingSetup());
		assertEquals(473, model.getEngineSetup());
		assertEquals(452, model.getBrakesSetup());
		assertEquals(305, model.getGearboxSetup());
		assertEquals(388, model.getSuspensionSetup());
		
		
		//Race car levels
		assertEquals(6, model.getChassisLevel());
		assertEquals(5, model.getEngineLevel());
		assertEquals(6, model.getFrontWingLevel());
		assertEquals(6, model.getRearWingLevel());
		assertEquals(8, model.getUnderbodyLevel());
		assertEquals(7, model.getSidepodsLevel());
		assertEquals(7, model.getCoolingLevel());
		assertEquals(6, model.getGearboxLevel());
		assertEquals(7, model.getBrakesLevel());
		assertEquals(6, model.getSuspensionLevel());
		assertEquals(7, model.getElectronicsLevel());
		
		//Race car wears
		//Start
		assertEquals(63, model.getChassisStartWear());
		assertEquals(77, model.getEngineStartWear());
		assertEquals(82, model.getFrontWingStartWear());
		assertEquals(81, model.getRearWingStartWear());
		assertEquals(9, model.getUnderbodyStartWear());
		assertEquals(76, model.getSidepodsStartWear());
		assertEquals(61, model.getCoolingStartWear());
		assertEquals(12, model.getGearboxStartWear());
		assertEquals(9, model.getBrakesStartWear());
		assertEquals(9, model.getSuspensionStartWear());
		assertEquals(54, model.getElectronicsStartWear());
		//End
		assertEquals(63, model.getChassisEndWear());
		assertEquals(77, model.getEngineEndWear());
		assertEquals(82, model.getFrontWingEndWear());
		assertEquals(81, model.getRearWingEndWear());
		assertEquals(9, model.getUnderbodyEndWear());
		assertEquals(76, model.getSidepodsEndWear());
		assertEquals(61, model.getCoolingEndWear());
		assertEquals(12, model.getGearboxEndWear());
		assertEquals(9, model.getBrakesEndWear());
		assertEquals(9, model.getSuspensionEndWear());
		assertEquals(54, model.getElectronicsEndWear());
		
		//Race start
		assertEquals(102, model.getStartFuel());
		
		//Race stops
		assertEquals(0, model.getStopsCount());
		
		//Race problems
		assertEquals(0, model.getTechProblemsList().size());

		
		assertEquals(model.getEndTyres(), null);
		assertEquals(model.getEndFuel(), null);
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.EXCEL_PARSER;
	}

}
