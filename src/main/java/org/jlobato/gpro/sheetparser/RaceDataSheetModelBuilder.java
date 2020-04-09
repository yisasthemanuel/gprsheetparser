package org.jlobato.gpro.sheetparser;

/**
 * 
 * @author JLOBATO
 *
 */
public interface RaceDataSheetModelBuilder {
	/**
	 * 
	 */
	public void createRaceDataSheetModel();
	
	/**
	 * 
	 * @param trackAndSeasonData
	 */
	public void setTrackSeasonData(String trackAndSeasonData);
	
	/**
	 * 
	 * @return
	 */
	public RaceDataSheetModel getRaceDataSheetModel();

	public void setLapNumber(int numericCellValue);

	public void setLapTime(String stringCellValue);

	public void setLapPosition(int numericCellValue);

	public void setLapTyres(String stringCellValue);

	public void setLapWeather(String stringCellValue);

	public void setLapTemperature(int numericCellValue);

	public void setLapHumidity(int numericCellValue);

	public void setLapEvent(String stringCellValue);

	public void addNewLap();

	public void setOvertakeRisks(int numericCellValue);

	public void setDefendRisks(int numericCellValue);

	public void setClearDryRisks(int numericCellValue);

	public void setClearWetRisks(int numericCellValue);

	public void setMalfunctRisks(int numericCellValue);

	public void setFrontWingSetup(int numericCellValue);

	public void setRearWingSetup(int numericCellValue);

	public void setEngineSetup(int numericCellValue);

	public void setBrakesSetup(int numericCellValue);

	public void setGearSetup(int numericCellValue);

	public void setSuspensionSetup(int numericCellValue);

	public void setChassisLevel(int numericCellValue);

	public void setEngineLevel(int numericCellValue);

	public void setFrontWingLevel(int numericCellValue);

	public void setRearWingLevel(int numericCellValue);

	public void setUnderbodyLevel(int numericCellValue);

	public void setSidepodsLevel(int numericCellValue);

	public void setCoolingLevel(int numericCellValue);

	public void setGearboxLevel(int numericCellValue);

	public void setBrakesLevel(int numericCellValue);

	public void setSuspensionLevel(int numericCellValue);

	public void setElectronicsLevel(int numericCellValue);

	public void setChassisEndWear(int numericCellValue);

	public void setEngineEndWear(int numericCellValue);

	public void setFrontWingEndWear(int numericCellValue);

	public void setRearWingEndWear(int numericCellValue);

	public void setUnderbodyEndWear(int numericCellValue);

	public void setSidepodsEndWear(int numericCellValue);

	public void setCoolingEndWear(int numericCellValue);

	public void setGearboxEndWear(int numericCellValue);

	public void setBrakesEndWear(int numericCellValue);

	public void setSuspensionEndWear(int numericCellValue);

	public void setElectronicsEndWear(int numericCellValue);

	public void setChassisStartWear(int numericCellValue);

	public void setEngineStartWear(int numericCellValue);

	public void setFrontWingStartWear(int numericCellValue);

	public void setRearWingStartWear(int numericCellValue);

	public void setUnderbodyStartWear(int numericCellValue);

	public void setSidepodsStartWear(int numericCellValue);

	public void setCoolingStartWear(int numericCellValue);

	public void setGearboxStartWear(int numericCellValue);

	public void setBrakesStartWear(int numericCellValue);

	public void setSuspensionStartWear(int numericCellValue);

	public void setElectronicsStartWear(int numericCellValue);
	
	/**
	 * 
	 * @param startFuelLitres
	 */
	public void setStartFuel(int startFuelLitres);
	
	/**
	 * 
	 * @param endTyresPercent
	 */
	public void setEndTyres(int endTyresPercent);
	
	/**
	 * 
	 * @param endFuelLitres
	 */
	public void setEndFuel(int endFuelLitres);
	
	/**
	 * 
	 * @param stopInfo
	 */
	public void setStopInfo(String stopInfo);
	
	/**
	 * 
	 * @param stopReason
	 */
	public void setStopReason(String stopReason);
	
	/**
	 * 
	 * @param tyresCond
	 */
	public void setStopTyresCondition(int tyresCond);
	
	/**
	 * 
	 * @param fuelLeft
	 */
	public void setStopFuelLeft(int fuelLeft);
	
	/**
	 * 
	 * @param stopRefilledInfo
	 */
	public void setStopRefilledInfo(String stopRefilledInfo);
	
	/**
	 * 
	 * @param stopPitTime
	 */
	public void setStopPitTime(int stopPitTime);
	
	/**
	 * 
	 */
	public void addNewStint();

	/**
	 * 
	 * @param stringCellValue
	 */
	public void setTechProblemLapInfo(String stringCellValue);

	/**
	 * 
	 * @param stringCellValue
	 */
	public void setTechProblemDescriptionInfo(String stringCellValue);
	
	/**
	 * 
	 */
	public void addNewTechProblem();
	
	/**
	 * 
	 * @param energyStart
	 * @return
	 */
	public RaceDataSheetModelBuilder setDriverEnergyStart(int energyStart);
	
	/**
	 * 
	 * @param energyEnd
	 * @return
	 */
	public RaceDataSheetModelBuilder setDriverEnergyEnd(int energyEnd);

}
