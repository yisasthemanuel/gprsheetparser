package org.jlobato.gpro.sheetparser;

/**
 * 
 * @author JLOBATO
 *
 */
class DefaultRaceDataSheetModelBuilder implements RaceDataSheetModelBuilder {
	
	/**
	 * 
	 */
	RaceDataSheetModel product;
	
	
	private int lapNumber;
	private String lapTime;
	private int lapPosition;
	private String lapTyres;
	private String lapWeather;
	private int lapTemperature;
	private int lapHumidity;
	private String lapEvent;	

	private String stopInfo;
	private String stopReason;
	private int stopTyresCondition;
	private int stopFuelLeft;
	private String stopRefilledInfo;
	private int stopPitTime;


	private String techProblemLapInfo;
	private String techProblemDescriptionInfo;
	
	/**
	 * 
	 */
	public DefaultRaceDataSheetModelBuilder() {
		this(false);
	}
	
	/**
	 * 
	 * @param create
	 */
	public DefaultRaceDataSheetModelBuilder(boolean create) {
		super();
		if (create) {
			createRaceDataSheetModel();
		}
	}
	
	/**
	 * 
	 */
	public void createRaceDataSheetModel() {
		product = new RaceDataSheetModel();
	}

	/**
	 * 
	 */
	public void setTrackSeasonData(String trackAndSeasonData) {
		String trackAndSeason = trackAndSeasonData;
		String trackName = trackAndSeason.substring(0, trackAndSeason.indexOf("(")).trim();
		String seasonName = trackAndSeason.substring(trackAndSeason.indexOf("Season"), trackAndSeason.length());
		String seasonNumber = seasonName.substring("Season".length() + 1, seasonName.length());
		
		product.setTrackName(trackName);
		product.setSeason(seasonName);
		product.setSeasonNumber(new Integer(seasonNumber));		
	}

	/**
	 * 
	 */
	public RaceDataSheetModel getRaceDataSheetModel() {
		return product;
	}

	/**
	 * 
	 */
	public void setLapNumber(int lapNumber) {
		this.lapNumber = lapNumber;
	}

	/**
	 * 
	 */
	public void setLapTime(String lapTime) {
		this.lapTime = lapTime;
	}

	/**
	 * 
	 */
	public void setLapPosition(int lapPosition) {
		this.lapPosition = lapPosition;
	}

	/**
	 * 
	 */
	public void setLapTyres(String lapTyres) {
		this.lapTyres = lapTyres;
	}

	/**
	 * 
	 */
	public void setLapWeather(String lapWeather) {
		this.lapWeather = lapWeather;
	}

	/**
	 * 
	 */
	public void setLapTemperature(int lapTemperature) {
		this.lapTemperature = lapTemperature;
	}

	/**
	 * 
	 */
	public void setLapHumidity(int lapHumidity) {
		this.lapHumidity = lapHumidity;
	}

	/**
	 * 
	 */
	public void setLapEvent(String lapEvent) {
		this.lapEvent = lapEvent;
	}

	/**
	 * 
	 */
	public void addNewLap() {
		RaceLapInfo lap = new RaceLapInfo(lapNumber, lapTime, lapPosition, lapTyres, lapWeather, lapTemperature, lapHumidity, lapEvent);
		product.addLap(lap);
	}

	/**
	 * 
	 */
	public void setOvertakeRisks(int overtakeRisks) {
		product.setOvertakeRisks(overtakeRisks);
	}

	/**
	 * 
	 */
	public void setDefendRisks(int defendRisks) {
		product.setDefendRisks(defendRisks);
	}

	/**
	 * 
	 */
	public void setClearDryRisks(int clearDryRisks) {
		product.setClearDryRisks(clearDryRisks);
	}

	/**
	 * 
	 */
	public void setClearWetRisks(int clearWetRisks) {
		product.setClearWetRisks(clearWetRisks);
	}

	/**
	 * 
	 */
	public void setMalfunctRisks(int malfunctRisks) {
		product.setMalfunctRisks(malfunctRisks);
	}

	/**
	 * 
	 */
	public void setFrontWingSetup(int frontWingSetup) {
		product.setFrontWingSetup(frontWingSetup);
	}

	/**
	 * 
	 */
	public void setRearWingSetup(int rearWingSetup) {
		product.setRearWingSetup(rearWingSetup);
	}

	/**
	 * 
	 */
	public void setEngineSetup(int numericCellValue) {
		product.setEngineSetup(numericCellValue);
	}

	/**
	 * 
	 */
	public void setBrakesSetup(int numericCellValue) {
		product.setBrakesSetup(numericCellValue);
	}

	/**
	 * 
	 */
	public void setGearSetup(int numericCellValue) {
		product.setGearboxSetup(numericCellValue);
	}

	/**
	 * 
	 */
	public void setSuspensionSetup(int numericCellValue) {
		product.setSuspensionSetup(numericCellValue);
	}

	/**
	 * 
	 */
	public void setChassisLevel(int numericCellValue) {
		product.setChassisLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setEngineLevel(int numericCellValue) {
		product.setEngineLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setFrontWingLevel(int numericCellValue) {
		product.setFrontWingLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setRearWingLevel(int numericCellValue) {
		product.setRearWingLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setUnderbodyLevel(int numericCellValue) {
		product.setUnderbodyLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setSidepodsLevel(int numericCellValue) {
		product.setSidepodsLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setCoolingLevel(int numericCellValue) {
		product.setCoolingLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setGearboxLevel(int numericCellValue) {
		product.setGearboxLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setBrakesLevel(int numericCellValue) {
		product.setBrakesLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setSuspensionLevel(int numericCellValue) {
		product.setSuspensionLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setElectronicsLevel(int numericCellValue) {
		product.setElectronicsLevel(numericCellValue);	
	}

	/**
	 * 
	 */
	public void setChassisEndWear(int numericCellValue) {
		product.setChassisEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setEngineEndWear(int numericCellValue) {
		product.setEngineEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setFrontWingEndWear(int numericCellValue) {
		product.setFrontWingEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setRearWingEndWear(int numericCellValue) {
		product.setRearWingEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setUnderbodyEndWear(int numericCellValue) {
		product.setUnderbodyEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setSidepodsEndWear(int numericCellValue) {
		product.setSidepodsEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setCoolingEndWear(int numericCellValue) {
		product.setCoolingEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setGearboxEndWear(int numericCellValue) {
		product.setGearboxEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setBrakesEndWear(int numericCellValue) {
		product.setBrakesEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setSuspensionEndWear(int numericCellValue) {
		product.setSuspensionEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setElectronicsEndWear(int numericCellValue) {
		product.setElectronicsEndWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setChassisStartWear(int numericCellValue) {
		product.setChassisStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setEngineStartWear(int numericCellValue) {
		product.setEngineStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setFrontWingStartWear(int numericCellValue) {
		product.setFrontWingStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setRearWingStartWear(int numericCellValue) {
		product.setRearWingStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setUnderbodyStartWear(int numericCellValue) {
		product.setUnderbodyStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setSidepodsStartWear(int numericCellValue) {
		product.setSidepodsStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setCoolingStartWear(int numericCellValue) {
		product.setCoolingStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setGearboxStartWear(int numericCellValue) {
		product.setGearboxStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setBrakesStartWear(int numericCellValue) {
		product.setBrakesStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setSuspensionStartWear(int numericCellValue) {
		product.setSuspensionStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setElectronicsStartWear(int numericCellValue) {
		product.setElectronicsStartWear(numericCellValue);
	}

	/**
	 * 
	 */
	public void setStartFuel(int startFuelLitres) {
		product.setStartFuel(startFuelLitres);
	}

	/**
	 * 
	 */
	public void setEndTyres(int endTyresPercent) {
		product.setEndTyres(new Integer(endTyresPercent));
	}

	/**
	 * 
	 */
	public void setEndFuel(int endFuelLitres) {
		product.setEndFuel(new Integer(endFuelLitres));
	}

	/**
	 * 
	 */
	public void setStopInfo(String stopInfo) {
		this.stopInfo = stopInfo;
	}

	/**
	 * 
	 */
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	/**
	 * 
	 */
	public void setStopTyresCondition(int tyresCond) {
		this.stopTyresCondition = tyresCond;
	}

	/**
	 * 
	 */
	public void setStopFuelLeft(int fuelLeft) {
		this.stopFuelLeft = fuelLeft;
	}

	/**
	 * 
	 */
	public void setStopRefilledInfo(String stopRefilledInfo) {
		this.stopRefilledInfo = stopRefilledInfo;
	}

	/**
	 * 
	 */
	public void setStopPitTime(int stopPitTime) {
		this.stopPitTime = stopPitTime;
	}

	/**
	 * 
	 */
	public void addNewStint() {
		this.product.addStop(new RaceStopInfo(stopInfo, stopReason, stopTyresCondition, stopFuelLeft, stopRefilledInfo, stopPitTime));
	}

	/**
	 * 
	 */
	public void setTechProblemLapInfo(String techProblemLapInfo) {
		this.techProblemLapInfo = techProblemLapInfo;
	}

	/**
	 * 
	 */
	public void setTechProblemDescriptionInfo(String techProblemDescriptionInfo) {
		this.techProblemDescriptionInfo = techProblemDescriptionInfo;
	}

	@Override
	/**
	 * 
	 */
	public void addNewTechProblem() {
		this.product.addTechProblem(new TechProblemInfo(techProblemLapInfo, techProblemDescriptionInfo));
	}

	@Override
	/**
	 * 
	 */
	public RaceDataSheetModelBuilder setDriverEnergyStart(int energyStart) {
		this.product.setEnergyStart(energyStart);
		return this;
	}

	@Override
	/**
	 * 
	 */
	public RaceDataSheetModelBuilder setDriverEnergyEnd(int energyEnd) {
		this.product.setEnergyEnd(energyEnd);
		return this;
	}
}
