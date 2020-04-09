package org.jlobato.gpro.sheetparser;

import java.io.Serializable;

/**
 * 
 * @author JLOBATO
 *
 */
public class TechProblemInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7554737161851032865L;
	
	/**
	 * 
	 */
	private int lapInfo;
	
	/**
	 * 
	 */
	private String techProblemInfo;

	/**
	 * 
	 * @param lapInfo
	 * @param techProblemInfo
	 */
	public TechProblemInfo(String lapInfo, String techProblemInfo) {
		this(getTechProblemLapNumber(lapInfo), techProblemInfo);
	}
	
	/**
	 * 
	 * @param lapInfo
	 * @param techProblemInfo
	 */
	public TechProblemInfo(int lapInfo, String techProblemInfo) {
		this.lapInfo = lapInfo;
		this.techProblemInfo = techProblemInfo;
		
	}

	/**
	 * 
	 * @return
	 */
	public int getLapInfo() {
		return lapInfo;
	}

	/**
	 * 
	 * @param lapInfo
	 */
	public void setLapInfo(int lapInfo) {
		this.lapInfo = lapInfo;
	}

	/**
	 * 
	 * @return
	 */
	public String getTechProblemInfo() {
		return techProblemInfo;
	}

	/**
	 * 
	 * @param techProblemInfo
	 */
	public void setTechProblemInfo(String techProblemInfo) {
		this.techProblemInfo = techProblemInfo;
	}
	
	/**
	 * 
	 * @param lapInfo
	 * @return
	 */
	public static int getTechProblemLapNumber(String lapInfo) {
		int result = 0;
		int lapIndex = lapInfo.indexOf("Lap");
		if (lapIndex > -1) {
			int beginIndex = lapIndex + "Lap".length() + 1;
			String sub = lapInfo.substring(beginIndex, lapInfo.length());
			result = new Integer(sub).intValue();
		}
		return result;
		
	}
	

	@Override
	/**
	 * 
	 */
	public String toString() {
		return "TechProblemInfo [lapInfo=" + lapInfo + ", techProblemInfo=" + techProblemInfo + "]";
	}

	@Override
	/**
	 * 
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lapInfo;
		result = prime * result + ((techProblemInfo == null) ? 0 : techProblemInfo.hashCode());
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
		TechProblemInfo other = (TechProblemInfo) obj;
		if (lapInfo != other.lapInfo)
			return false;
		if (techProblemInfo == null) {
			if (other.techProblemInfo != null)
				return false;
		} else if (!techProblemInfo.equals(other.techProblemInfo))
			return false;
		return true;
	}	

}
