package org.jlobato.gpro.sheetparser;

import java.io.Serializable;

/**
 * 
 * @author JLOBATO
 *
 */
public class RaceLapInfo implements Serializable {
	
	private int lapNumber;
	private String lapTime;
	private int lapPosition;
	private String lapTyres;
	private String lapWeather;
	private int lapTemperature;
	private int lapHumidity;
	private String lapEvent;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6506440585034075682L;
	
	/**
	 * 
	 * @param lapNumber
	 * @param lapTime
	 * @param lapPosition
	 * @param lapTyres
	 * @param lapWeather
	 * @param lapTemperature
	 * @param lapHumidity
	 * @param lapEvent
	 */
	public RaceLapInfo(int lapNumber, String lapTime, int lapPosition, String lapTyres, String lapWeather, int lapTemperature, int lapHumidity, String lapEvent) {
		super();
		this.lapNumber = lapNumber;
		this.lapTime = lapTime;
		this.lapPosition = lapPosition;
		this.lapTyres = lapTyres;
		this.lapWeather = lapWeather;
		this.lapTemperature = lapTemperature;
		this.lapHumidity = lapHumidity;
		this.lapEvent = lapEvent;
	}

	/**
	 * 
	 * @return
	 */
	public int getLapNumber() {
		return lapNumber;
	}

	/**
	 * 
	 * @param lapNumber
	 */
	public void setLapNumber(int lapNumber) {
		this.lapNumber = lapNumber;
	}

	/**
	 * 
	 * @return
	 */
	public String getLapTime() {
		return lapTime;
	}

	/**
	 * 
	 * @param lapTime
	 */
	public void setLapTime(String lapTime) {
		this.lapTime = lapTime;
	}

	/**
	 * 
	 * @return
	 */
	public int getLapPosition() {
		return lapPosition;
	}

	/**
	 * 
	 * @param lapPosition
	 */
	public void setLapPosition(int lapPosition) {
		this.lapPosition = lapPosition;
	}

	/**
	 * 
	 * @return
	 */
	public String getLapTyres() {
		return lapTyres;
	}

	/**
	 * 
	 * @param lapTyres
	 */
	public void setLapTyres(String lapTyres) {
		this.lapTyres = lapTyres;
	}

	/**
	 * 
	 * @return
	 */
	public String getLapWeather() {
		return lapWeather;
	}

	/**
	 * 
	 * @param lapWeather
	 */
	public void setLapWeather(String lapWeather) {
		this.lapWeather = lapWeather;
	}

	/**
	 * 
	 * @return
	 */
	public int getLapTemperature() {
		return lapTemperature;
	}

	/**
	 * 
	 * @param lapTemperature
	 */
	public void setLapTemperature(int lapTemperature) {
		this.lapTemperature = lapTemperature;
	}

	/**
	 * 
	 * @return
	 */
	public int getLapHumidity() {
		return lapHumidity;
	}

	/**
	 * 
	 * @param lapHumidity
	 */
	public void setLapHumidity(int lapHumidity) {
		this.lapHumidity = lapHumidity;
	}

	/**
	 * 
	 * @return
	 */
	public String getLapEvent() {
		return lapEvent;
	}

	/**
	 * 
	 * @param lapEvent
	 */
	public void setLapEvent(String lapEvent) {
		this.lapEvent = lapEvent;
	}

	@Override
	/**
	 * 
	 */
	public String toString() {
		return "RaceLapInfo [lapNumber=" + lapNumber + ", lapTime=" + lapTime
				+ ", lapPosition=" + lapPosition + ", lapTyres=" + lapTyres
				+ ", lapWeather=" + lapWeather + ", lapTemperature="
				+ lapTemperature + ", lapHumidity=" + lapHumidity
				+ ", lapEvent=" + lapEvent + "]";
	}

	@Override
	/**
	 * 
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lapEvent == null) ? 0 : lapEvent.hashCode());
		result = prime * result + lapHumidity;
		result = prime * result + lapNumber;
		result = prime * result + lapPosition;
		result = prime * result + lapTemperature;
		result = prime * result + ((lapTime == null) ? 0 : lapTime.hashCode());
		result = prime * result
				+ ((lapTyres == null) ? 0 : lapTyres.hashCode());
		result = prime * result
				+ ((lapWeather == null) ? 0 : lapWeather.hashCode());
		return result;
	}

	@Override
	/**
	 * 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RaceLapInfo other = (RaceLapInfo) obj;
		if (lapEvent == null) {
			if (other.lapEvent != null)
				return false;
		} else if (!lapEvent.equals(other.lapEvent))
			return false;
		if (lapHumidity != other.lapHumidity)
			return false;
		if (lapNumber != other.lapNumber)
			return false;
		if (lapPosition != other.lapPosition)
			return false;
		if (lapTemperature != other.lapTemperature)
			return false;
		if (lapTime == null) {
			if (other.lapTime != null)
				return false;
		} else if (!lapTime.equals(other.lapTime))
			return false;
		if (lapTyres == null) {
			if (other.lapTyres != null)
				return false;
		} else if (!lapTyres.equals(other.lapTyres))
			return false;
		if (lapWeather == null) {
			if (other.lapWeather != null)
				return false;
		} else if (!lapWeather.equals(other.lapWeather))
			return false;
		return true;
	}
	
	

}
