package org.jlobato.gpro.sheetparser;

import java.io.IOException;

/**
 * 
 * @author jlobato
 *
 */
public interface GPRORaceSheetParser {

	/**
	 * 
	 */
	public static final int RACE_TRACK_AND_SEASON_MODE = 0;
	/**
	 * 
	 */
	public static final int RACE_POST_TRACK_AND_SEASON_MODE = 10;
	/**
	 * 
	 */
	public static final int RACE_LAPS_MODE = 1;
	/**
	 * 
	 */
	public static final int RACE_RISKS_USED_MODE = 2;
	/**
	 * 
	 */
	public static final int RACE_SETUP_USED_MODE = 3;
	/**
	 * 
	 */
	public static final int RACE_PARTS_LVL_MODE = 4;
	/**
	 * 
	 */
	public static final int RACE_PARTS_START_WEAR_MODE = 5;
	/**
	 * 
	 */
	public static final int RACE_PARTS_END_WEAR_MODE = 6;
	/**
	 * 
	 */
	public static final int RACE_START_FUEL_MODE = 7;
	
	/**
	 * 
	 */
	public static final int RACE_PARTS_PRE_LVL_MODE = 8;
	
	/**
	 * 
	 */
	public static final int RACE_STINTS_MODE = 9;
	
	/**
	 * 
	 */
	public static final int RACE_TYRES_AFTER_RACE_MODE = 10;
	
	/**
	 * 
	 */
	public static final int RACE_FUEL_AFTER_RACE_MODE = 11;
	
	/*
	 * 
	 */
	public  static final int RACE_STINTS_PRE_MODE = 12;	

	/*
	 * 
	 */
	public  static final int RACE_TECH_PROBLEMS_MODE = 14;
	
	/**
	 * 
	 */
	public  static final int DRIVER_ENERGY_MODE = 15;
	
	/**
	 * 
	 */
	String DRIVER_PREFIX = "Driver";
	
	/**
	 * 
	 */
	String TECHNICAL_PROBLEMS = "Technical problems";
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 * @throws Exception 
	 */
	public RaceDataSheetModel readRaceDataSheet() throws Exception;

}