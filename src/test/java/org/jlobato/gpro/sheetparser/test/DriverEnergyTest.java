package org.jlobato.gpro.sheetparser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParser;
import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.jlobato.gpro.sheetparser.RaceDataSheetModel;
import org.jlobato.gpro.sheetparser.RaceLapInfo;
import org.jlobato.gpro.sheetparser.RaceStopInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DriverEnergyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEnergyLagunaSecaS68HTML() {
		assertsForLagunaSecaMikkoS68(doDriverEnergyTest("/html/S68 - R1 - Laguna Seca.xls", GPRORaceSheetParserFactory.HTML_PARSER));
	}
	
	@Test
	public void testEnergyLagunaSecaS68Excel() {
		assertsForLagunaSecaMikkoS68(doDriverEnergyTest("/xls/S68 - R1 - Laguna Seca.xlsx", GPRORaceSheetParserFactory.EXCEL_PARSER));
	}
	
	@Test
	public void testEnergyKyalamiS67HTML() {
		assertsForKyalamiMikkoS67(doDriverEnergyTest("/html/S67 - R1 - Kyalami.xls", GPRORaceSheetParserFactory.HTML_PARSER));			
	}

	@Test
	public void testEnergyKyalamiS67Excel() {
		assertsForKyalamiMikkoS67(doDriverEnergyTest("/xls/S67 - R1 - Kyalami.xlsx", GPRORaceSheetParserFactory.EXCEL_PARSER));			
	}
	
	@Test
	public void testEnergyAdelaideS68HTML() {
		assertsForAdelaideMikkoS68(doDriverEnergyTest("/html/S68 - R3 - Adelaide.xls", GPRORaceSheetParserFactory.HTML_PARSER));			
	}

	@Test
	public void testEnergyAdelaideS68Excel() {
		assertsForAdelaideMikkoS68(doDriverEnergyTest("/xls/S68 - R3 - Adelaide.xlsx", GPRORaceSheetParserFactory.EXCEL_PARSER));			
	}

	/**
	 * 
	 * @param classpathSheet
	 * @param type
	 */
	private RaceDataSheetModel doDriverEnergyTest(String classpathSheet, int type) {
		RaceDataSheetModel result = null;
		try (InputStream energy = getClass().getResourceAsStream(classpathSheet)) {
			
			GPRORaceSheetParser parser = GPRORaceSheetParserFactory.create(energy, type);
			result = parser.readRaceDataSheet();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param doDriverEnergyTest
	 */
	private void assertsForAdelaideMikkoS68(RaceDataSheetModel data) {
		//Datos principales
		assertNotNull(data);
		assertNotNull(data.getSeasonNumber());
		assertEquals(68, data.getSeasonNumber().intValue());
		assertEquals("Adelaide", data.getTrackName());
		assertEquals(80, data.getLapCount());
		
		//Información de las vueltas
		RaceLapInfo firstLap = data.getLapsList().get(0);
		RaceLapInfo lastLap =   data.getLapsList().get(data.getLapCount() - 1);
		RaceLapInfo fortyEightLap = data.getLapsList().get(48);
		RaceLapInfo fiftyFourLap = data.getLapsList().get(54);
		
		//First lap
		assertEquals(37,  firstLap.getLapPosition());
		assertEquals(0,  firstLap.getLapNumber());
		assertEquals(34,  firstLap.getLapTemperature());
		assertEquals(92,  firstLap.getLapHumidity());
		assertEquals("-",  firstLap.getLapEvent());
		assertEquals("-",  firstLap.getLapTime());
		assertEquals("Rain", firstLap.getLapWeather());
		assertEquals("Rain", firstLap.getLapTyres());
		
		//48 lap		
		assertEquals(39,  fortyEightLap.getLapPosition());
		assertEquals(48,  fortyEightLap.getLapNumber());
		assertEquals(38,  fortyEightLap.getLapTemperature());
		assertEquals(92,  fortyEightLap.getLapHumidity());
		assertEquals("-",  fortyEightLap.getLapEvent());
		assertEquals("1:27.678",  fortyEightLap.getLapTime());
		assertEquals("Rain", fortyEightLap.getLapWeather());
		
		//54 lap		
		assertEquals(39,  fiftyFourLap.getLapPosition());
		assertEquals(54,  fiftyFourLap.getLapNumber());
		assertEquals(33,  fiftyFourLap.getLapTemperature());
		assertEquals(94,  fiftyFourLap.getLapHumidity());
		assertEquals("-",  fiftyFourLap.getLapEvent());
		assertEquals("1:27.358",  fiftyFourLap.getLapTime());
		assertEquals("Rain", fiftyFourLap.getLapWeather());
		
		//Last lap		
		assertEquals(39,  lastLap.getLapPosition());
		assertEquals(79,  lastLap.getLapNumber());
		assertEquals(33,  lastLap.getLapTemperature());
		assertEquals(95,  lastLap.getLapHumidity());
		assertEquals("Car problem",  lastLap.getLapEvent());
		assertEquals("1:29.334",  lastLap.getLapTime());
		assertEquals("Rain", lastLap.getLapWeather());
		
		//Stops
		assertEquals(1, data.getStopsCount());
		RaceStopInfo firstStop = data.getStopsList().get(0);
		//First stop	
		assertEquals(40, firstStop.getStopLapNumber());
		assertEquals("No more fuel was left", firstStop.getStopReason());
		assertEquals(77, firstStop.getStopRefilledLitres());
		assertEquals(19970, firstStop.getStopPitTime());
		assertEquals(0, firstStop.getStopFuelLeft());
		assertEquals(63, firstStop.getStopTyresCondition());
		
		//Start and end fuel
		assertEquals(75, data.getStartFuel());
		assertEquals(4, data.getEndFuel().intValue());
		
		//Tyres at the end
		assertEquals(63, data.getEndTyres().intValue());
		
	}

	
	
	/**
	 * 
	 * @param data
	 */
	private void assertsForLagunaSecaMikkoS68(RaceDataSheetModel data) {
		//Datos principales
		assertNotNull(data);
		assertNotNull(data.getSeasonNumber());
		assertEquals(68, data.getSeasonNumber().intValue());
		assertEquals("Laguna Seca", data.getTrackName());
		assertEquals(80, data.getLapCount());
		
		//Información de las vueltas
		RaceLapInfo firstLap = data.getLapsList().get(0);
		RaceLapInfo lastLap =   data.getLapsList().get(data.getLapCount() - 1);
		RaceLapInfo fortyEightLap = data.getLapsList().get(48);
		RaceLapInfo fiftyFourLap = data.getLapsList().get(54);
		
		//First lap
		assertEquals(34,  firstLap.getLapPosition());
		assertEquals(0,  firstLap.getLapNumber());
		assertEquals(18,  firstLap.getLapTemperature());
		assertEquals(20,  firstLap.getLapHumidity());
		assertEquals("-",  firstLap.getLapEvent());
		assertEquals("-",  firstLap.getLapTime());
		assertEquals("Cloudy", firstLap.getLapWeather());
		
		//48 lap		
		assertEquals(35,  fortyEightLap.getLapPosition());
		assertEquals(48,  fortyEightLap.getLapNumber());
		assertEquals(19,  fortyEightLap.getLapTemperature());
		assertEquals(20,  fortyEightLap.getLapHumidity());
		assertEquals("-",  fortyEightLap.getLapEvent());
		assertEquals("1:16.301",  fortyEightLap.getLapTime());
		assertEquals("Cloudy", fortyEightLap.getLapWeather());
		
		//54 lap		
		assertEquals(36,  fiftyFourLap.getLapPosition());
		assertEquals(54,  fiftyFourLap.getLapNumber());
		assertEquals(21,  fiftyFourLap.getLapTemperature());
		assertEquals(19,  fiftyFourLap.getLapHumidity());
		assertEquals("Pit",  fiftyFourLap.getLapEvent());
		assertEquals("1:23.768",  fiftyFourLap.getLapTime());
		assertEquals("Cloudy", fiftyFourLap.getLapWeather());
		
		//Last lap		
		assertEquals(36,  lastLap.getLapPosition());
		assertEquals(79,  lastLap.getLapNumber());
		assertEquals(17,  lastLap.getLapTemperature());
		assertEquals(16,  lastLap.getLapHumidity());
		assertEquals("-",  lastLap.getLapEvent());
		assertEquals("1:16.006",  lastLap.getLapTime());
		assertEquals("Cloudy", lastLap.getLapWeather());
		
		//Race risks
		assertEquals(0, data.getOvertakeRisks());
		assertEquals(0, data.getDefendRisks());
		assertEquals(0, data.getClearDryRisks());
		//Viene con un guión y eso es lo mismo que si no vinieran estos riesgos, luego deben ser igual que los cleardryrisks
		assertEquals(0, data.getClearWetRisks());
		assertEquals(0, data.getMalfunctRisks());
		
		//Driver Energy
		assertEquals(98, data.getEnergyStart().intValue());
		assertEquals(90, data.getEnergyEnd().intValue());
		
		//Race setup
		assertEquals(290, data.getFrontWingSetup());
		assertEquals(684, data.getRearWingSetup());
		assertEquals(642, data.getEngineSetup());
		assertEquals(542, data.getBrakesSetup());
		assertEquals(546, data.getGearboxSetup());
		assertEquals(287, data.getSuspensionSetup());
		
		
		//Race car levels
		assertEquals(5, data.getChassisLevel());
		assertEquals(6, data.getEngineLevel());
		assertEquals(5, data.getFrontWingLevel());
		assertEquals(5, data.getRearWingLevel());
		assertEquals(6, data.getUnderbodyLevel());
		assertEquals(6, data.getSidepodsLevel());
		assertEquals(5, data.getCoolingLevel());
		assertEquals(4, data.getGearboxLevel());
		assertEquals(4, data.getBrakesLevel());
		assertEquals(5, data.getSuspensionLevel());
		assertEquals(6, data.getElectronicsLevel());
		
		//Race car wears
		//Start
		assertEquals(11, data.getChassisStartWear());
		assertEquals(8, data.getEngineStartWear());
		assertEquals(13, data.getFrontWingStartWear());
		assertEquals(12, data.getRearWingStartWear());
		assertEquals(12, data.getUnderbodyStartWear());
		assertEquals(9, data.getSidepodsStartWear());
		assertEquals(11, data.getCoolingStartWear());
		assertEquals(12, data.getGearboxStartWear());
		assertEquals(18, data.getBrakesStartWear());
		assertEquals(10, data.getSuspensionStartWear());
		assertEquals(9, data.getElectronicsStartWear());
		//End
		assertEquals(26, data.getChassisEndWear());
		assertEquals(28, data.getEngineEndWear());
		assertEquals(26, data.getFrontWingEndWear());
		assertEquals(20, data.getRearWingEndWear());
		assertEquals(19, data.getUnderbodyEndWear());
		assertEquals(16, data.getSidepodsEndWear());
		assertEquals(23, data.getCoolingEndWear());
		assertEquals(33, data.getGearboxEndWear());
		assertEquals(41, data.getBrakesEndWear());
		assertEquals(28, data.getSuspensionEndWear());
		assertEquals(12, data.getElectronicsEndWear());
		
		//Stops
		assertEquals(2, data.getStopsCount());
		RaceStopInfo firstStop = data.getStopsList().get(0);
		RaceStopInfo secondStop = data.getStopsList().get(1);
		//First stop	
		assertEquals(27, firstStop.getStopLapNumber());
		assertEquals("No more fuel was left", firstStop.getStopReason());
		assertEquals(68, firstStop.getStopRefilledLitres());
		assertEquals(18717, firstStop.getStopPitTime());
		assertEquals(0, firstStop.getStopFuelLeft());
		assertEquals(23, firstStop.getStopTyresCondition());
		//Second stop	
		assertEquals(54, secondStop.getStopLapNumber());
		assertEquals("No more fuel was left", secondStop.getStopReason());
		assertEquals(68, secondStop.getStopRefilledLitres());
		assertEquals(18319, secondStop.getStopPitTime());
		assertEquals(0, secondStop.getStopFuelLeft());
		assertEquals(22, secondStop.getStopTyresCondition());
		
		//Start and end fuel
		assertEquals(68, data.getStartFuel());
		assertEquals(5, data.getEndFuel().intValue());
		
		//Tyres at the end
		assertEquals(27, data.getEndTyres().intValue());
	}
	
	/**
	 * 
	 * @param data
	 */
	private void assertsForKyalamiMikkoS67(RaceDataSheetModel data) {
		//Datos principales
		assertNotNull(data);
		assertNotNull(data.getSeasonNumber());
		assertEquals(67, data.getSeasonNumber().intValue());
		assertEquals("Kyalami", data.getTrackName());
		assertEquals(73, data.getLapCount());
		
		//Información de las vueltas
		RaceLapInfo firstLap = data.getLapsList().get(0);
		RaceLapInfo lastLap =   data.getLapsList().get(data.getLapCount() - 1);
		RaceLapInfo fortyEightLap = data.getLapsList().get(48);
		RaceLapInfo sixtyFourLap = data.getLapsList().get(68);
		
		//First lap
		assertEquals(13,  firstLap.getLapPosition());
		assertEquals(0,  firstLap.getLapNumber());
		assertEquals(18,  firstLap.getLapTemperature());
		assertEquals(10,  firstLap.getLapHumidity());
		assertEquals("-",  firstLap.getLapEvent());
		assertEquals("-",  firstLap.getLapTime());
		assertEquals("Partially Cloudy", firstLap.getLapWeather());
		
		//48 lap		
		assertEquals(4,  fortyEightLap.getLapPosition());
		assertEquals(48,  fortyEightLap.getLapNumber());
		assertEquals(18,  fortyEightLap.getLapTemperature());
		assertEquals(10,  fortyEightLap.getLapHumidity());
		assertEquals("-",  fortyEightLap.getLapEvent());
		assertEquals("1:07.347",  fortyEightLap.getLapTime());
		assertEquals("Partially Cloudy", fortyEightLap.getLapWeather());
		
		//68 lap		
		assertEquals(9,  sixtyFourLap.getLapPosition());
		assertEquals(68,  sixtyFourLap.getLapNumber());
		assertEquals(17,  sixtyFourLap.getLapTemperature());
		assertEquals(11,  sixtyFourLap.getLapHumidity());
		assertEquals("Driver mistake",  sixtyFourLap.getLapEvent());
		assertEquals("1:14.224",  sixtyFourLap.getLapTime());
		assertEquals("Partially Cloudy", sixtyFourLap.getLapWeather());
		
		//Last lap		
		assertEquals(10,  lastLap.getLapPosition());
		assertEquals(72,  lastLap.getLapNumber());
		assertEquals(17,  lastLap.getLapTemperature());
		assertEquals(11,  lastLap.getLapHumidity());
		assertEquals("-",  lastLap.getLapEvent());
		assertEquals("1:10.253",  lastLap.getLapTime());
		assertEquals("Partially Cloudy", lastLap.getLapWeather());
		
		//Race risks
		assertEquals(19, data.getOvertakeRisks());
		assertEquals(29, data.getDefendRisks());
		assertEquals(100, data.getClearDryRisks());
		//Viene con un guión y eso es lo mismo que si no vinieran estos riesgos, luego deben ser igual que los cleardryrisks
		assertEquals(100, data.getClearWetRisks());
		assertEquals(100, data.getMalfunctRisks());
		
		//Driver Energy - No debe haber información
		assertNull(data.getEnergyStart());
		assertNull(data.getEnergyEnd());
		
		//Race setup
		assertEquals(807, data.getFrontWingSetup());
		assertEquals(691, data.getRearWingSetup());
		assertEquals(863, data.getEngineSetup());
		assertEquals(486, data.getBrakesSetup());
		assertEquals(359, data.getGearboxSetup());
		assertEquals(999, data.getSuspensionSetup());
		
		
		//Race car levels
		assertEquals(6, data.getChassisLevel());
		assertEquals(6, data.getEngineLevel());
		assertEquals(7, data.getFrontWingLevel());
		assertEquals(7, data.getRearWingLevel());
		assertEquals(7, data.getUnderbodyLevel());
		assertEquals(6, data.getSidepodsLevel());
		assertEquals(7, data.getCoolingLevel());
		assertEquals(7, data.getGearboxLevel());
		assertEquals(6, data.getBrakesLevel());
		assertEquals(6, data.getSuspensionLevel());
		assertEquals(7, data.getElectronicsLevel());
		
		//Race car wears
		//Start
		assertEquals(9, data.getChassisStartWear());
		assertEquals(10, data.getEngineStartWear());
		assertEquals(6, data.getFrontWingStartWear());
		assertEquals(6, data.getRearWingStartWear());
		assertEquals(6, data.getUnderbodyStartWear());
		assertEquals(6, data.getSidepodsStartWear());
		assertEquals(9, data.getCoolingStartWear());
		assertEquals(8, data.getGearboxStartWear());
		assertEquals(15, data.getBrakesStartWear());
		assertEquals(6, data.getSuspensionStartWear());
		assertEquals(6, data.getElectronicsStartWear());
		//End
		assertEquals(23, data.getChassisEndWear());
		assertEquals(33, data.getEngineEndWear());
		assertEquals(31, data.getFrontWingEndWear());
		assertEquals(35, data.getRearWingEndWear());
		assertEquals(19, data.getUnderbodyEndWear());
		assertEquals(16, data.getSidepodsEndWear());
		assertEquals(32, data.getCoolingEndWear());
		assertEquals(42, data.getGearboxEndWear());
		assertEquals(33, data.getBrakesEndWear());
		assertEquals(21, data.getSuspensionEndWear());
		assertEquals(20, data.getElectronicsEndWear());
		
		//Stops
		assertEquals(2, data.getStopsCount());
		RaceStopInfo firstStop = data.getStopsList().get(0);
		RaceStopInfo secondStop = data.getStopsList().get(1);
		//First stop	
		assertEquals(26, firstStop.getStopLapNumber());
		assertEquals("No more fuel was left", firstStop.getStopReason());
		assertEquals(66, firstStop.getStopRefilledLitres());
		assertEquals(15318, firstStop.getStopPitTime());
		assertEquals(0, firstStop.getStopFuelLeft());
		assertEquals(10, firstStop.getStopTyresCondition());
		//Second stop	
		assertEquals(52, secondStop.getStopLapNumber());
		assertEquals("No more fuel was left", secondStop.getStopReason());
		assertEquals(59, secondStop.getStopRefilledLitres());
		assertEquals(15720, secondStop.getStopPitTime());
		assertEquals(0, secondStop.getStopFuelLeft());
		assertEquals(11, secondStop.getStopTyresCondition());
		
		//Start and end fuel
		assertEquals(65, data.getStartFuel());
		assertEquals(9, data.getEndFuel().intValue());
		
		//Tyres at the end
		assertEquals(41, data.getEndTyres().intValue());
	}
	
}
