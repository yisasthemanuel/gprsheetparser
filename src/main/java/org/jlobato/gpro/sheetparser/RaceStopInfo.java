package org.jlobato.gpro.sheetparser;

import java.io.Serializable;

/**
 * 
 * @author JLOBATO
 *
 */
public class RaceStopInfo implements Serializable {
	
	/**
	 * 
	 */
	private String stopInfo;
	/**
	 * 
	 */
	private int stopLapNumber;
	/**
	 * 
	 */
	private String stopReason;
	/**
	 * 
	 */
	private int stopTyresCondition;
	/**
	 * 
	 */
	private int stopFuelLeft;
	/**
	 * 
	 */
	private String stopRefilledInfo;
	/**
	 * 
	 */
	private int stopRefilledLitres;
	/**
	 * 
	 */
	private int stopPitTime;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9153004983423871184L;

	/**
	 * 
	 * @param stopInfo
	 * @param stopLapNumber
	 * @param stopReason
	 * @param stopTyresCondition
	 * @param stopFuelLeft
	 * @param stopRefilledInfo
	 * @param stopRefilledLitres
	 * @param stopPitTime
	 */
	public RaceStopInfo(String stopInfo, int stopLapNumber, String stopReason,	int stopTyresCondition, int stopFuelLeft, String stopRefilledInfo, int stopRefilledLitres, int stopPitTime) {
		super();
		this.stopInfo = stopInfo;
		this.stopLapNumber = stopLapNumber;
		this.stopReason = stopReason;
		this.stopTyresCondition = stopTyresCondition;
		this.stopFuelLeft = stopFuelLeft;
		this.stopRefilledInfo = stopRefilledInfo;
		this.stopRefilledLitres = stopRefilledLitres;
		this.stopPitTime = stopPitTime;
	}

	/**
	 * 
	 * @param stopInfo
	 * @param stopReason
	 * @param stopTyresCondition
	 * @param stopFuelLeft
	 * @param stopRefilledInfo
	 * @param stopPitTime
	 */
	public RaceStopInfo(String stopInfo, String stopReason, int stopTyresCondition, int stopFuelLeft, String stopRefilledInfo, int stopPitTime) {
		this(stopInfo, getStopLapNumber(stopInfo), stopReason, stopTyresCondition, stopFuelLeft, stopRefilledInfo, getRefilledLitres(stopRefilledInfo), stopPitTime);
	}
	
	/**
	 * 
	 * @param stopLapNumberInfo
	 * @return
	 */
	public static int getStopLapNumber(String stopLapNumberInfo) {
		int result = 0;
		int lapIndex = stopLapNumberInfo.indexOf("Lap");
		int parentesisIndex = stopLapNumberInfo.indexOf(")");
		if (lapIndex > -1 && parentesisIndex > -1) {
			int beginIndex = lapIndex + "Lap".length() + 1;
			int endIndex = parentesisIndex;
			String sub = stopLapNumberInfo.substring(beginIndex, endIndex).trim();
			result = new Integer(sub).intValue();
		}
		return result;
	}
	
	/**
	 * 
	 * @param stopRefilledInfo
	 * @return
	 */
	public static int getRefilledLitres(String stopRefilledInfo) {
		int result = 0;
		int indexLiters = stopRefilledInfo.indexOf("liters");
		if (indexLiters > -1) {
			String sub = stopRefilledInfo.substring(0, indexLiters).trim();
			result = new Integer(sub).intValue(); 
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "RaceStopInfo [stopInfo=" + stopInfo + ", stopLapNumber="
				+ stopLapNumber + ", stopReason=" + stopReason
				+ ", stopTyresCondition=" + stopTyresCondition
				+ ", stopFuelLeft=" + stopFuelLeft + ", stopRefilledInfo="
				+ stopRefilledInfo + ", stopRefilledLitres="
				+ stopRefilledLitres + ", stopPitTime=" + stopPitTime + "]";
	}

	public String getStopInfo() {
		return stopInfo;
	}

	public void setStopInfo(String stopInfo) {
		this.stopInfo = stopInfo;
	}

	public int getStopLapNumber() {
		return stopLapNumber;
	}

	public void setStopLapNumber(int stopLapNumber) {
		this.stopLapNumber = stopLapNumber;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	public int getStopTyresCondition() {
		return stopTyresCondition;
	}

	public void setStopTyresCondition(int stopTyresCondition) {
		this.stopTyresCondition = stopTyresCondition;
	}

	public int getStopFuelLeft() {
		return stopFuelLeft;
	}

	public void setStopFuelLeft(int stopFuelLeft) {
		this.stopFuelLeft = stopFuelLeft;
	}

	public String getStopRefilledInfo() {
		return stopRefilledInfo;
	}

	public void setStopRefilledInfo(String stopRefilledInfo) {
		this.stopRefilledInfo = stopRefilledInfo;
	}

	public int getStopRefilledLitres() {
		return stopRefilledLitres;
	}

	public void setStopRefilledLitres(int stopRefilledLitres) {
		this.stopRefilledLitres = stopRefilledLitres;
	}

	public int getStopPitTime() {
		return stopPitTime;
	}

	public void setStopPitTime(int stopPitTime) {
		this.stopPitTime = stopPitTime;
	}
	

}
