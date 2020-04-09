package org.jlobato.gpro.sheetparser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author JLOBATO
 *
 */
public class RaceDataSheetModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1902322523037643663L;
	
	/**
	 * 
	 */
	private String trackName;
	/**
	 * 
	 */
	private String season;
	/**
	 * 
	 */
	private Integer seasonNumber;
	
	/**
	 * 
	 */
	private List<RaceLapInfo> lapsList;
	
	/**
	 * 
	 */
	private List<RaceStopInfo> stopsList;
	/**
	 * 
	 */
	private List<TechProblemInfo> techProblemsList;
	
	
	/**
	 * 
	 */
	private int overtakeRisks;
	/**
	 * 
	 */
	private int defendRisks;
	/**
	 * 
	 */
	private int clearDryRisks;
	/**
	 * 
	 */
	private int clearWetRisks;
	/**
	 * 
	 */
	private int malfunctRisks;
	
	/**
	 * 
	 */
	private int frontWingSetup;
	/**
	 * 
	 */
	private int rearWingSetup;
	/**
	 * 
	 */
	private int engineSetup;
	/**
	 * 
	 */
	private int brakesSetup;
	/**
	 * 
	 */
	private int gearboxSetup;
	/**
	 * 
	 */
	private int suspensionSetup;
	
	/**
	 * 
	 */
	private int chassisLevel;
	/**
	 * 
	 */
	private int engineLevel;
	/**
	 * 
	 */
	private int frontWingLevel;
	/**
	 * 
	 */
	private int rearWingLevel;
	/**
	 * 
	 */
	private int underbodyLevel;
	/**
	 * 
	 */
	private int sidepodsLevel;
	/**
	 * 
	 */
	private int coolingLevel;
	/**
	 * 
	 */
	private int gearboxLevel;
	/**
	 * 
	 */
	private int brakesLevel;
	/**
	 * 
	 */
	private int suspensionLevel;
	/**
	 * 
	 */
	private int electronicsLevel;
	
	/**
	 * 
	 */
	private int chassisStartWear;
	/**
	 * 
	 */
	private int engineStartWear;
	/**
	 * 
	 */
	private int frontWingStartWear;
	/**
	 * 
	 */
	private int rearWingStartWear;
	/**
	 * 
	 */
	private int underbodyStartWear;
	/**
	 * 
	 */
	private int sidepodsStartWear;
	/**
	 * 
	 */
	private int coolingStartWear;
	/**
	 * 
	 */
	private int gearboxStartWear;
	/**
	 * 
	 */
	private int brakesStartWear;
	/**
	 * 
	 */
	private int suspensionStartWear;
	/**
	 * 
	 */
	private int electronicsStartWear;
	
	/**
	 * 
	 */
	private int chassisEndWear;
	/**
	 * 
	 */
	private int engineEndWear;
	/**
	 * 
	 */
	private int frontWingEndWear;
	/**
	 * 
	 */
	private int rearWingEndWear;
	/**
	 * 
	 */
	private int underbodyEndWear;
	/**
	 * 
	 */
	private int sidepodsEndWear;
	/**
	 * 
	 */
	private int coolingEndWear;
	/**
	 * 
	 */
	private int gearboxEndWear;
	/**
	 * 
	 */
	private int brakesEndWear;
	/**
	 * 
	 */
	private int suspensionEndWear;
	/**
	 * 
	 */
	private int electronicsEndWear;
	
	/**
	 * 
	 */
	private int startFuel;
	/**
	 * 
	 */
	private Integer endTyres;
	/**
	 * 
	 */
	private Integer endFuel;
	
	/**
	 * 
	 */
	private Integer energyStart;
	
	/**
	 * 
	 */
	private Integer energyEnd;

	/**
	 * Constructor
	 */
	public RaceDataSheetModel() {
		super();
		this.lapsList = new ArrayList<RaceLapInfo>();
		this.stopsList = new ArrayList<RaceStopInfo>();
		this.techProblemsList = new ArrayList<TechProblemInfo>();
	}

	/**
	 * 
	 * @return
	 */
	public String getTrackName() {
		return trackName;
	}

	/**
	 * 
	 * @param trackName
	 */
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	/**
	 * 
	 * @return
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * 
	 * @param season
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getSeasonNumber() {
		return seasonNumber;
	}

	/**
	 * 
	 * @param seasonNumber
	 */
	public void setSeasonNumber(Integer seasonNumber) {
		this.seasonNumber = seasonNumber;
	}
	
	/**
	 * 
	 * @param lap
	 */
	public void addLap(RaceLapInfo lap) {
		getLapsList().add(lap);
	}

	/**
	 * 
	 * @return
	 */
	public List<RaceLapInfo> getLapsList() {
		return lapsList;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLapCount() {
		return getLapsList().size();
	}

	/**
	 * 
	 * @return
	 */
	public int getOvertakeRisks() {
		return overtakeRisks;
	}

	public void setOvertakeRisks(int overtakeRisks) {
		this.overtakeRisks = overtakeRisks;
	}

	public int getDefendRisks() {
		return defendRisks;
	}

	public void setDefendRisks(int defendRisks) {
		this.defendRisks = defendRisks;
	}

	public int getClearDryRisks() {
		return clearDryRisks;
	}

	public void setClearDryRisks(int clearDryRisks) {
		this.clearDryRisks = clearDryRisks;
	}

	public int getClearWetRisks() {
		return clearWetRisks;
	}

	public void setClearWetRisks(int clearWetRisks) {
		this.clearWetRisks = clearWetRisks;
	}

	public int getMalfunctRisks() {
		return malfunctRisks;
	}

	public void setMalfunctRisks(int malfunctRisks) {
		this.malfunctRisks = malfunctRisks;
	}

	public void setLapsList(List<RaceLapInfo> lapsList) {
		this.lapsList = lapsList;
	}


	public int getFrontWingSetup() {
		return frontWingSetup;
	}

	public int getRearWingSetup() {
		return rearWingSetup;
	}

	public int getEngineSetup() {
		return engineSetup;
	}

	public int getBrakesSetup() {
		return brakesSetup;
	}

	public int getGearboxSetup() {
		return gearboxSetup;
	}

	public int getSuspensionSetup() {
		return suspensionSetup;
	}

	public void setFrontWingSetup(int frontWingSetup) {
		this.frontWingSetup = frontWingSetup;
	}

	public void setRearWingSetup(int rearWingSetup) {
		this.rearWingSetup = rearWingSetup;
	}

	public void setEngineSetup(int engineSetup) {
		this.engineSetup = engineSetup;
	}

	public void setBrakesSetup(int brakesSetup) {
		this.brakesSetup = brakesSetup;
	}

	public void setGearboxSetup(int gearboxSetup) {
		this.gearboxSetup = gearboxSetup;
	}

	public void setSuspensionSetup(int suspensionSetup) {
		this.suspensionSetup = suspensionSetup;
	}

	public int getChassisLevel() {
		return chassisLevel;
	}

	public void setChassisLevel(int chassisLevel) {
		this.chassisLevel = chassisLevel;
	}

	public int getEngineLevel() {
		return engineLevel;
	}

	public void setEngineLevel(int engineLevel) {
		this.engineLevel = engineLevel;
	}

	public int getFrontWingLevel() {
		return frontWingLevel;
	}

	public void setFrontWingLevel(int frontWingLevel) {
		this.frontWingLevel = frontWingLevel;
	}

	public int getRearWingLevel() {
		return rearWingLevel;
	}

	public void setRearWingLevel(int rearWingLevel) {
		this.rearWingLevel = rearWingLevel;
	}

	public int getUnderbodyLevel() {
		return underbodyLevel;
	}

	public void setUnderbodyLevel(int underbodyLevel) {
		this.underbodyLevel = underbodyLevel;
	}

	public int getSidepodsLevel() {
		return sidepodsLevel;
	}

	public void setSidepodsLevel(int sidepodsLevel) {
		this.sidepodsLevel = sidepodsLevel;
	}

	public int getCoolingLevel() {
		return coolingLevel;
	}

	public void setCoolingLevel(int coolingLevel) {
		this.coolingLevel = coolingLevel;
	}

	public int getGearboxLevel() {
		return gearboxLevel;
	}

	public void setGearboxLevel(int gearboxLevel) {
		this.gearboxLevel = gearboxLevel;
	}

	public int getBrakesLevel() {
		return brakesLevel;
	}

	public void setBrakesLevel(int brakesLevel) {
		this.brakesLevel = brakesLevel;
	}

	public int getSuspensionLevel() {
		return suspensionLevel;
	}

	public void setSuspensionLevel(int suspensionLevel) {
		this.suspensionLevel = suspensionLevel;
	}

	public int getElectronicsLevel() {
		return electronicsLevel;
	}

	public void setElectronicsLevel(int electronicsLevel) {
		this.electronicsLevel = electronicsLevel;
	}

	public int getChassisStartWear() {
		return chassisStartWear;
	}

	public void setChassisStartWear(int chassisStartWear) {
		this.chassisStartWear = chassisStartWear;
	}

	public int getEngineStartWear() {
		return engineStartWear;
	}

	public void setEngineStartWear(int engineStartWear) {
		this.engineStartWear = engineStartWear;
	}

	public int getFrontWingStartWear() {
		return frontWingStartWear;
	}

	public void setFrontWingStartWear(int frontWingStartWear) {
		this.frontWingStartWear = frontWingStartWear;
	}

	public int getRearWingStartWear() {
		return rearWingStartWear;
	}

	public void setRearWingStartWear(int rearWingStartWear) {
		this.rearWingStartWear = rearWingStartWear;
	}

	public int getUnderbodyStartWear() {
		return underbodyStartWear;
	}

	public void setUnderbodyStartWear(int underbodyStartWear) {
		this.underbodyStartWear = underbodyStartWear;
	}

	public int getSidepodsStartWear() {
		return sidepodsStartWear;
	}

	public void setSidepodsStartWear(int sidepodsStartWear) {
		this.sidepodsStartWear = sidepodsStartWear;
	}

	public int getCoolingStartWear() {
		return coolingStartWear;
	}

	public void setCoolingStartWear(int coolingStartWear) {
		this.coolingStartWear = coolingStartWear;
	}

	public int getGearboxStartWear() {
		return gearboxStartWear;
	}

	public void setGearboxStartWear(int gearboxStartWear) {
		this.gearboxStartWear = gearboxStartWear;
	}

	public int getBrakesStartWear() {
		return brakesStartWear;
	}

	public void setBrakesStartWear(int brakesStartWear) {
		this.brakesStartWear = brakesStartWear;
	}

	public int getSuspensionStartWear() {
		return suspensionStartWear;
	}

	public void setSuspensionStartWear(int suspensionStartWear) {
		this.suspensionStartWear = suspensionStartWear;
	}

	public int getElectronicsStartWear() {
		return electronicsStartWear;
	}

	public void setElectronicsStartWear(int electronicsStartWear) {
		this.electronicsStartWear = electronicsStartWear;
	}

	public int getChassisEndWear() {
		return chassisEndWear;
	}

	public void setChassisEndWear(int chassisEndWear) {
		this.chassisEndWear = chassisEndWear;
	}

	public int getEngineEndWear() {
		return engineEndWear;
	}

	public void setEngineEndWear(int engineEndWear) {
		this.engineEndWear = engineEndWear;
	}

	public int getFrontWingEndWear() {
		return frontWingEndWear;
	}

	public void setFrontWingEndWear(int frontWingEndWear) {
		this.frontWingEndWear = frontWingEndWear;
	}

	public int getRearWingEndWear() {
		return rearWingEndWear;
	}

	public void setRearWingEndWear(int rearWingEndWear) {
		this.rearWingEndWear = rearWingEndWear;
	}

	public int getUnderbodyEndWear() {
		return underbodyEndWear;
	}

	public void setUnderbodyEndWear(int underbodyEndWear) {
		this.underbodyEndWear = underbodyEndWear;
	}

	public int getSidepodsEndWear() {
		return sidepodsEndWear;
	}

	public void setSidepodsEndWear(int sidepodsEndWear) {
		this.sidepodsEndWear = sidepodsEndWear;
	}

	public int getCoolingEndWear() {
		return coolingEndWear;
	}

	public void setCoolingEndWear(int coolingEndWear) {
		this.coolingEndWear = coolingEndWear;
	}

	public int getGearboxEndWear() {
		return gearboxEndWear;
	}

	public void setGearboxEndWear(int gearboxEndWear) {
		this.gearboxEndWear = gearboxEndWear;
	}

	public int getBrakesEndWear() {
		return brakesEndWear;
	}

	public void setBrakesEndWear(int brakesEndWear) {
		this.brakesEndWear = brakesEndWear;
	}

	public int getSuspensionEndWear() {
		return suspensionEndWear;
	}

	public void setSuspensionEndWear(int suspensionEndWear) {
		this.suspensionEndWear = suspensionEndWear;
	}

	public int getElectronicsEndWear() {
		return electronicsEndWear;
	}

	public void setElectronicsEndWear(int electronicsEndWear) {
		this.electronicsEndWear = electronicsEndWear;
	}


	/**
	 * 
	 * @return
	 */
	public int getStartFuel() {
		return startFuel;
	}

	/**
	 * 
	 * @param startFuel
	 */
	public void setStartFuel(int startFuel) {
		this.startFuel = startFuel;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getEndTyres() {
		return endTyres;
	}

	/**
	 * 
	 * @param endTyres
	 */
	public void setEndTyres(Integer endTyres) {
		this.endTyres = endTyres;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getEndFuel() {
		return endFuel;
	}

	/**
	 * 
	 * @param endFuel
	 */
	public void setEndFuel(Integer endFuel) {
		this.endFuel = endFuel;
	}

	/**
	 * 
	 * @return
	 */
	public List<RaceStopInfo> getStopsList() {
		return stopsList;
	}

	/**
	 * 
	 * @param stopList
	 */
	public void setStopsList(List<RaceStopInfo> stopList) {
		this.stopsList = stopList;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getStopsCount() {
		return stopsList.size();
	}
	
	/**
	 * 
	 * @param stopInfo
	 */
	public void addStop(RaceStopInfo stopInfo) {
		getStopsList().add(stopInfo);
	}

	/**
	 * 
	 * @param techProblemInfo
	 */
	public void addTechProblem(TechProblemInfo techProblemInfo) {
		getTechProblemsList().add(techProblemInfo);
	}

	/***
	 * 
	 * @return
	 */
	public List<TechProblemInfo> getTechProblemsList() {
		return techProblemsList;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getEnergyStart() {
		return energyStart;
	}

	/**
	 * 
	 * @param energyStart
	 */
	public void setEnergyStart(Integer energyStart) {
		this.energyStart = energyStart;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getEnergyEnd() {
		return energyEnd;
	}

	/**
	 * 
	 * @param energyEnd
	 */
	public void setEnergyEnd(Integer energyEnd) {
		this.energyEnd = energyEnd;
	}

	@Override
	public String toString() {
		return "RaceDataSheetModel [trackName=" + trackName + ", season=" + season + ", seasonNumber=" + seasonNumber
				+ ", lapsList=" + lapsList + ", stopsList=" + stopsList + ", techProblemsList=" + techProblemsList
				+ ", overtakeRisks=" + overtakeRisks + ", defendRisks=" + defendRisks + ", clearDryRisks="
				+ clearDryRisks + ", clearWetRisks=" + clearWetRisks + ", malfunctRisks=" + malfunctRisks
				+ ", frontWingSetup=" + frontWingSetup + ", rearWingSetup=" + rearWingSetup + ", engineSetup="
				+ engineSetup + ", brakesSetup=" + brakesSetup + ", gearboxSetup=" + gearboxSetup + ", suspensionSetup="
				+ suspensionSetup + ", chassisLevel=" + chassisLevel + ", engineLevel=" + engineLevel
				+ ", frontWingLevel=" + frontWingLevel + ", rearWingLevel=" + rearWingLevel + ", underbodyLevel="
				+ underbodyLevel + ", sidepodsLevel=" + sidepodsLevel + ", coolingLevel=" + coolingLevel
				+ ", gearboxLevel=" + gearboxLevel + ", brakesLevel=" + brakesLevel + ", suspensionLevel="
				+ suspensionLevel + ", electronicsLevel=" + electronicsLevel + ", chassisStartWear=" + chassisStartWear
				+ ", engineStartWear=" + engineStartWear + ", frontWingStartWear=" + frontWingStartWear
				+ ", rearWingStartWear=" + rearWingStartWear + ", underbodyStartWear=" + underbodyStartWear
				+ ", sidepodsStartWear=" + sidepodsStartWear + ", coolingStartWear=" + coolingStartWear
				+ ", gearboxStartWear=" + gearboxStartWear + ", brakesStartWear=" + brakesStartWear
				+ ", suspensionStartWear=" + suspensionStartWear + ", electronicsStartWear=" + electronicsStartWear
				+ ", chassisEndWear=" + chassisEndWear + ", engineEndWear=" + engineEndWear + ", frontWingEndWear="
				+ frontWingEndWear + ", rearWingEndWear=" + rearWingEndWear + ", underbodyEndWear=" + underbodyEndWear
				+ ", sidepodsEndWear=" + sidepodsEndWear + ", coolingEndWear=" + coolingEndWear + ", gearboxEndWear="
				+ gearboxEndWear + ", brakesEndWear=" + brakesEndWear + ", suspensionEndWear=" + suspensionEndWear
				+ ", electronicsEndWear=" + electronicsEndWear + ", startFuel=" + startFuel + ", endTyres=" + endTyres
				+ ", endFuel=" + endFuel + ", energyStart=" + energyStart + ", energyEnd=" + energyEnd + "]";
	}
	
	
}
