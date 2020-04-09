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
 * @author JLOBATO
 *
 */
public class AhvenistoS49XLSXTest extends BaseSheetParserTest {
	
	@Before
	public void setUp() throws Exception {
		loadFromClassPath("/xls/Ahvenisto_S49.xlsx");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * 
	 */
	public void test() {
		assertNotNull(model);
		assertNotNull(model.getSeasonNumber());
		assertEquals(49, model.getSeasonNumber().intValue());
		assertEquals("Ahvenisto", model.getTrackName());
		assertEquals(81, model.getLapCount());
		
		RaceLapInfo firstLap = model.getLapsList().get(0);
		RaceLapInfo lastLap =   model.getLapsList().get(model.getLapCount() - 1);
		RaceLapInfo fortyEightLap = model.getLapsList().get(48);

		//First lap
		assertEquals(10,  firstLap.getLapPosition());
		assertEquals(0,  firstLap.getLapNumber());
		assertEquals(5,  firstLap.getLapTemperature());
		assertEquals(9,  firstLap.getLapHumidity());
		assertEquals("-",  firstLap.getLapEvent());
		assertEquals("-",  firstLap.getLapTime());
		assertEquals("Partially Cloudy", firstLap.getLapWeather());
		
		//48 lap		
		assertEquals(11,  fortyEightLap.getLapPosition());
		assertEquals(48,  fortyEightLap.getLapNumber());
		assertEquals(4,  fortyEightLap.getLapTemperature());
		assertEquals(8,  fortyEightLap.getLapHumidity());
		assertEquals("Car problem",  fortyEightLap.getLapEvent());
		assertEquals("1:25.917",  fortyEightLap.getLapTime());
		assertEquals("Partially Cloudy", fortyEightLap.getLapWeather());
		
		//Last lap		
		assertEquals(31,  lastLap.getLapPosition());
		assertEquals(80,  lastLap.getLapNumber());
		assertEquals(8,  lastLap.getLapTemperature());
		assertEquals(6,  lastLap.getLapHumidity());
		assertEquals("Dropped out",  lastLap.getLapEvent());
		assertEquals("-",  lastLap.getLapTime());
		assertEquals("Cloudy", lastLap.getLapWeather());
		
		//Race risks
		assertEquals(24, model.getOvertakeRisks());
		assertEquals(64, model.getDefendRisks());
		assertEquals(60, model.getClearDryRisks());
		assertEquals(60, model.getClearWetRisks());
		assertEquals(60, model.getMalfunctRisks());
		
		//Race setup
		assertEquals(999, model.getFrontWingSetup());
		assertEquals(905, model.getRearWingSetup());
		assertEquals(609, model.getEngineSetup());
		assertEquals(636, model.getBrakesSetup());
		assertEquals(715, model.getGearboxSetup());
		assertEquals(466, model.getSuspensionSetup());
		
		
		//Race car levels
		assertEquals(4, model.getChassisLevel());
		assertEquals(3, model.getEngineLevel());
		assertEquals(4, model.getFrontWingLevel());
		assertEquals(4, model.getRearWingLevel());
		assertEquals(5, model.getUnderbodyLevel());
		assertEquals(5, model.getSidepodsLevel());
		assertEquals(4, model.getCoolingLevel());
		assertEquals(4, model.getGearboxLevel());
		assertEquals(5, model.getBrakesLevel());
		assertEquals(5, model.getSuspensionLevel());
		assertEquals(5, model.getElectronicsLevel());
		
		//Race car wears
		//Start
		assertEquals(3, model.getChassisStartWear());
		assertEquals(79, model.getEngineStartWear());
		assertEquals(3, model.getFrontWingStartWear());
		assertEquals(76, model.getRearWingStartWear());
		assertEquals(30, model.getUnderbodyStartWear());
		assertEquals(85, model.getSidepodsStartWear());
		assertEquals(80, model.getCoolingStartWear());
		assertEquals(5, model.getGearboxStartWear());
		assertEquals(44, model.getBrakesStartWear());
		assertEquals(70, model.getSuspensionStartWear());
		assertEquals(21, model.getElectronicsStartWear());
		//End
		assertEquals(15, model.getChassisEndWear());
		assertEquals(96, model.getEngineEndWear());
		assertEquals(19, model.getFrontWingEndWear());
		assertEquals(92, model.getRearWingEndWear());
		assertEquals(43, model.getUnderbodyEndWear());
		assertEquals(99, model.getSidepodsEndWear());
		assertEquals(90, model.getCoolingEndWear());
		assertEquals(20, model.getGearboxEndWear());
		assertEquals(61, model.getBrakesEndWear());
		assertEquals(83, model.getSuspensionEndWear());
		assertEquals(28, model.getElectronicsEndWear());
		
		
		//Race start
		assertEquals(61, model.getStartFuel());
		
		//Race stints
		assertEquals(2, model.getStopsCount());
		//First stint
		assertEquals(28, model.getStopsList().get(0).getStopLapNumber());
		assertEquals("No more fuel was left", model.getStopsList().get(0).getStopReason());
		assertEquals(16, model.getStopsList().get(0).getStopTyresCondition());
		assertEquals(0, model.getStopsList().get(0).getStopFuelLeft());
		assertEquals(61, model.getStopsList().get(0).getStopRefilledLitres());
		assertEquals(20636, model.getStopsList().get(0).getStopPitTime());
		//Second stint
		assertEquals(56, model.getStopsList().get(1).getStopLapNumber());
		assertEquals("No more fuel was left", model.getStopsList().get(1).getStopReason());
		assertEquals(20, model.getStopsList().get(1).getStopTyresCondition());
		assertEquals(0, model.getStopsList().get(1).getStopFuelLeft());
		assertEquals(58, model.getStopsList().get(1).getStopRefilledLitres());
		assertEquals(23053, model.getStopsList().get(1).getStopPitTime());
		
		//Race problems
		assertEquals(1, model.getTechProblemsList().size());
		assertEquals(38, model.getTechProblemsList().get(0).getLapInfo());
		assertEquals("The sidepods were damaged after a touch with the wall", model.getTechProblemsList().get(0).getTechProblemInfo());
		
		
		//Race end values
		assertEquals(model.getEndTyres(), null);
		assertEquals(model.getEndFuel(), null);
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.EXCEL_PARSER;
	}

}
