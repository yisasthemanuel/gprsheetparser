package org.jlobato.gpro.sheetparser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jlobato.gpro.sheetparser.GPRORaceSheetParserFactory;
import org.jlobato.gpro.sheetparser.RaceLapInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IrungattukottaiS49XLSXTest extends BaseSheetParserTest {

	@Before
	public void setUp() throws Exception {
		loadFromClassPath("/xls/Irungattukottai_S49.xlsx");
	}

	@After
	public void tearDown() throws Exception {
	}	

	@Test
	public void test() {
		assertNotNull(model);
		assertNotNull(model.getSeasonNumber());
		assertEquals(49, model.getSeasonNumber().intValue());
		assertEquals("Irungattukottai", model.getTrackName());
		assertEquals(80, model.getLapCount());
		
		RaceLapInfo firstLap = model.getLapsList().get(0);
		RaceLapInfo lastLap =   model.getLapsList().get(model.getLapCount() - 1);
		RaceLapInfo fortyEightLap = model.getLapsList().get(48);

		//First lap
		assertEquals(8,  firstLap.getLapPosition());
		assertEquals(0,  firstLap.getLapNumber());
		assertEquals(25,  firstLap.getLapTemperature());
		assertEquals(8,  firstLap.getLapHumidity());
		assertEquals("-",  firstLap.getLapEvent());
		assertEquals("-",  firstLap.getLapTime());
		assertEquals("Sunny", firstLap.getLapWeather());
		
		//48 lap		
		assertEquals(12,  fortyEightLap.getLapPosition());
		assertEquals(48,  fortyEightLap.getLapNumber());
		assertEquals(23,  fortyEightLap.getLapTemperature());
		assertEquals(6,  fortyEightLap.getLapHumidity());
		assertEquals("-",  fortyEightLap.getLapEvent());
		assertEquals("1:26.174",  fortyEightLap.getLapTime());
		assertEquals("Sunny", fortyEightLap.getLapWeather());
		
		//Last lap		
		assertEquals(17,  lastLap.getLapPosition());
		assertEquals(79,  lastLap.getLapNumber());
		assertEquals(26,  lastLap.getLapTemperature());
		assertEquals(6,  lastLap.getLapHumidity());
		assertEquals("Car problem",  lastLap.getLapEvent());
		assertEquals("1:29.042",  lastLap.getLapTime());
		assertEquals("Sunny", lastLap.getLapWeather());
		
		//Race risks
		assertEquals(0, model.getOvertakeRisks());
		assertEquals(0, model.getDefendRisks());
		assertEquals(0, model.getClearDryRisks());
		assertEquals(0, model.getClearWetRisks());
		assertEquals(0, model.getMalfunctRisks());
		
		//Race setup
		assertEquals(649, model.getFrontWingSetup());
		assertEquals(833, model.getRearWingSetup());
		assertEquals(555, model.getEngineSetup());
		assertEquals(635, model.getBrakesSetup());
		assertEquals(503, model.getGearboxSetup());
		assertEquals(718, model.getSuspensionSetup());
		
		
		//Race car levels
		assertEquals(3, model.getChassisLevel());
		assertEquals(3, model.getEngineLevel());
		assertEquals(3, model.getFrontWingLevel());
		assertEquals(4, model.getRearWingLevel());
		assertEquals(6, model.getUnderbodyLevel());
		assertEquals(4, model.getSidepodsLevel());
		assertEquals(5, model.getCoolingLevel());
		assertEquals(5, model.getGearboxLevel());
		assertEquals(6, model.getBrakesLevel());
		assertEquals(6, model.getSuspensionLevel());
		assertEquals(6, model.getElectronicsLevel());
		
		//Race car wears
		//Start
		assertEquals(78, model.getChassisStartWear());
		assertEquals(56, model.getEngineStartWear());
		assertEquals(85, model.getFrontWingStartWear());
		assertEquals(52, model.getRearWingStartWear());
		assertEquals(17, model.getUnderbodyStartWear());
		assertEquals(85, model.getSidepodsStartWear());
		assertEquals(71, model.getCoolingStartWear());
		assertEquals(43, model.getGearboxStartWear());
		assertEquals(57, model.getBrakesStartWear());
		assertEquals(41, model.getSuspensionStartWear());
		assertEquals(48, model.getElectronicsStartWear());
		//End
		assertEquals(95, model.getChassisEndWear());
		assertEquals(86, model.getEngineEndWear());
		assertEquals(99, model.getFrontWingEndWear());
		assertEquals(77, model.getRearWingEndWear());
		assertEquals(39, model.getUnderbodyEndWear());
		assertEquals(99, model.getSidepodsEndWear());
		assertEquals(92, model.getCoolingEndWear());
		assertEquals(70, model.getGearboxEndWear());
		assertEquals(88, model.getBrakesEndWear());
		assertEquals(62, model.getSuspensionEndWear());
		assertEquals(60, model.getElectronicsEndWear());
		
		//Race start
		assertEquals(54, model.getStartFuel());
		
		//Race stints
		assertEquals(3, model.getStopsCount());
		//First stop
		assertEquals(20, model.getStopsList().get(0).getStopLapNumber());
		assertEquals("No more fuel was left", model.getStopsList().get(0).getStopReason());
		assertEquals(26, model.getStopsList().get(0).getStopTyresCondition());
		assertEquals(1, model.getStopsList().get(0).getStopFuelLeft());
		assertEquals(54, model.getStopsList().get(0).getStopRefilledLitres());
		assertEquals(21766, model.getStopsList().get(0).getStopPitTime());
		//Second stop
		assertEquals(40, model.getStopsList().get(1).getStopLapNumber());
		assertEquals("No more fuel was left", model.getStopsList().get(1).getStopReason());
		assertEquals(26, model.getStopsList().get(1).getStopTyresCondition());
		assertEquals(1, model.getStopsList().get(1).getStopFuelLeft());
		assertEquals(54, model.getStopsList().get(1).getStopRefilledLitres());
		assertEquals(22590, model.getStopsList().get(1).getStopPitTime());
		//Third stop
		assertEquals(60, model.getStopsList().get(2).getStopLapNumber());
		assertEquals("No more fuel was left", model.getStopsList().get(2).getStopReason());
		assertEquals(30, model.getStopsList().get(2).getStopTyresCondition());
		assertEquals(1, model.getStopsList().get(2).getStopFuelLeft());
		assertEquals(51, model.getStopsList().get(2).getStopRefilledLitres());
		assertEquals(22820, model.getStopsList().get(2).getStopPitTime());
		
		//Race problems
		assertEquals(1, model.getTechProblemsList().size());
		assertEquals(52, model.getTechProblemsList().get(0).getLapInfo());
		assertEquals("The front wing was broken and it damaged the car", model.getTechProblemsList().get(0).getTechProblemInfo());
		
		
		//Race end values
		assertNotNull(model.getEndTyres());
		assertNotNull(model.getEndFuel());
		
		assertEquals(33, model.getEndTyres().intValue());
		assertEquals(1, model.getEndFuel().intValue());
	}

	@Override
	public int getParserType() {
		return GPRORaceSheetParserFactory.EXCEL_PARSER;
	}

}
