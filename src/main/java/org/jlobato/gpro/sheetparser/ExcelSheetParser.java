package org.jlobato.gpro.sheetparser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jlobato.gpro.utils.GPROUtils;

/**
 * 
 * @author JLOBATO
 *
 */
class ExcelSheetParser implements GPRORaceSheetParser {

	/**
	 * 
	 */
	private RaceDataSheetModelBuilder raceBuilder;
	
	/**
	 * 
	 */
	private InputStream is;
	
	/**
	 * 
	 */
	private boolean hasClearWetRisks = false;
	
	/**
	 * 
	 */
	protected ExcelSheetParser(InputStream is) {
		super();
		this.is = is;
	}

	/**
	 * 
	 */
	public RaceDataSheetModel readRaceDataSheet() throws Exception {
		//Leemos la información y la convertimos al modelo de carrera
		parse();
		
		//Devolvemos la información
		return raceBuilder.getRaceDataSheetModel();
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private void parse() throws Exception {
		//Creamos el lector de la hoja excel
		ExcelReader reader = new ExcelReader(is);
		try {
			//Extraemos la fuente de infomración
			Iterator<Row> rowIterator = reader.getRowIterator();
			//La tratamos y generamos el modelo de carrera
			parse(rowIterator);
		} catch (Exception e) {
			//Si ocurre alguna excepción, la lanzamos arriba
			throw e;
		} finally {
			//Nos aseguramos de que todo queda cerrado
			try { reader.close(); } catch(Exception e) {}
		}

	}
	
	/**
	 * 
	 * @author JLOBATO
	 *
	 */
	private static class ExcelReader {
		/**
		 * 
		 */
		XSSFWorkbook workbookXlsx = null;
		/**
		 * 
		 */
		HSSFWorkbook workbookXls = null;
		
		/**
		 * 
		 */
		InputStream is;
		
		/**
		 * 
		 */
		InputStream copy;
		
		/**
		 * 
		 */
		byte[] inputBytes;
		
		/**
		 * 
		 * @param is
		 * @throws IOException 
		 */
		public ExcelReader(InputStream is) throws IOException {
			super();
			//Hacemos una copia porque cuando se intenta abrir como XSLX y falla
			//Hay que poner el InputStream desde el principio y el método reset()
			//no siempre está soportado
			ByteArrayOutputStream out;
			IOUtils.copy(is, out = new ByteArrayOutputStream());
			inputBytes = out.toByteArray();
			out.close();
			
			//La instancia que se leerá en formato XLSX 
			this.is = new ByteArrayInputStream(inputBytes);
			//La instancia que se leerá en formato XLS
			this.copy = new ByteArrayInputStream(inputBytes);
		}
		
		/**
		 * 
		 * @return
		 * @throws IOException
		 */
		public Iterator<Row> getRowIterator() throws Exception {
			Iterator<Row> result = null;
			try {
				//Primero intentamos abrir como XLSX
				workbookXlsx = new XSSFWorkbook(this.is);
				/*
				 * Obtenemos la primera pestaña a la que se quiera procesar indicando el
				 * indice. Una vez obtenida la hoja excel con las filas que se quieren
				 * leer obtenemos el iterator que nos permite recorrer cada una de las
				 * filas que contiene.
				 */
				result = workbookXlsx.getSheetAt(0).iterator();
				//Si llegamos aquí es que lo hemos podido abrir cono XLSX, devolvemos el resultado
				return result;
			}catch(Exception e) {
				try {
					//Si falla intentamos abrir como XLS a partir de la copia creada
					workbookXls = new HSSFWorkbook(this.copy);
					result = workbookXls.getSheetAt(0).iterator();
					//Si llegamos aquí es que lo hemos podido abrir cono XLS, devolvemos el resultado
					return result;
				}catch(Exception e2) {
					//Si falla lanzamos la última excepción
					throw e2;
				}
			}
		}
		
		/**
		 * 
		 */
		public void close() {
			//Cierra dependiendo de si lo ha abierto como XLSX o como XLS
			//Para ello no nos complicamos, cerramos las hojas que no sean nulas
			if (workbookXlsx != null) {
				try {workbookXlsx.close();} catch(Exception e) {}
			}
			if (workbookXls != null) {
				try {workbookXls.close();} catch(Exception e) {}
			}
			
			//Tambien cerramos los InputStream por si acaso
			if (this.is != null) {
				try {this.is.close();}catch(Exception e) {}
			}
			if (this.copy != null) {
				try {this.copy.close();}catch(Exception e) {}
			}
		}
		
	}
	
	/**
	 * 
	 * @param rowIterator
	 */
	private void parse(Iterator<Row> rowIterator) {
		raceBuilder = new DefaultRaceDataSheetModelBuilder();
		raceBuilder.createRaceDataSheetModel();
		
			
		int currentMode = RACE_TRACK_AND_SEASON_MODE;
		boolean isNotBlank = false;
		boolean endOfRow = false;
		boolean clearWetRisksChecked = false;
		int lastRowIndex = -1;
		boolean techProblems = false;
		
		Row row;
		
		while (rowIterator.hasNext()) {
			endOfRow = false;
			row = rowIterator.next();

			// Obtenemos el iterator que permite recorrer todas las celdas de una fila
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell celda = null;
			
			//Averiguamos en qué parte de la excel nos encontramos para ver cómo interpretamos la info
			currentMode = getNextMode(currentMode, row.getRowNum(), 0, isNotBlank, lastRowIndex);				

			while (cellIterator.hasNext() && !endOfRow) {
				celda = cellIterator.next();
				isNotBlank = false;
				
				int type = celda.getCellType();
				isNotBlank = type == Cell.CELL_TYPE_NUMERIC || type == Cell.CELL_TYPE_STRING || type == Cell.CELL_TYPE_BOOLEAN; 
				
				if (isNotBlank) {
					switch(currentMode) {
					case RACE_TRACK_AND_SEASON_MODE:
						parseTrackInfo(celda);
						endOfRow = true;
						break;
					case RACE_LAPS_MODE:
						parseLapsInfo(celda);
						break;
					case RACE_RISKS_USED_MODE:
						parseRisksInfo(celda, type);
						break;
					case DRIVER_ENERGY_MODE:
						parseDriverEnergy(celda, type);
						break;
					case RACE_SETUP_USED_MODE:
						if (type == Cell.CELL_TYPE_STRING && celda.getStringCellValue().startsWith(GPRORaceSheetParser.DRIVER_PREFIX)) {
							currentMode = DRIVER_ENERGY_MODE;
							break;
						}
						//Este modo va después de RACE_RISKS_USED_MODE
						//Antes comprobamos que lleva clearWetRisks o no
						if (!clearWetRisksChecked) {
							checkWetRisks();
							clearWetRisksChecked = true;
						}
						parseSetupInfo(celda, type);
						break;
					case RACE_PARTS_LVL_MODE:
						parseCarPartsLevelInfo(celda, type);
						break;
					case RACE_PARTS_START_WEAR_MODE:
						parseStartWearInfo(celda, type);
						break;
					case RACE_PARTS_END_WEAR_MODE:
						parseEndWearInfo(celda, type);
						break;
					case RACE_STINTS_MODE:
						parseStopsInfo(celda, type);
						break;
					case RACE_START_FUEL_MODE:
						parseStartFuel(celda, type);
						break;
					case RACE_TYRES_AFTER_RACE_MODE:
						if (type == Cell.CELL_TYPE_STRING && celda.getStringCellValue().equals(GPRORaceSheetParser.TECHNICAL_PROBLEMS)) {
							currentMode = RACE_TECH_PROBLEMS_MODE;
							break;
						}
						parseEndTyres(celda, type);
						break;
					case RACE_TECH_PROBLEMS_MODE:
						techProblems = true;
						parseTechProblems(celda, type);
						break;
					case RACE_FUEL_AFTER_RACE_MODE:
						parseEndFuel(celda, type);
						break;
					}
				}
				else {
					
				}
			} //End of columns
			
			//Final de una fila
			switch(currentMode) {
				case RACE_LAPS_MODE:
					//Si estamos en la lista de vueltas, podemos añadirla a la lista de la carrera
					raceBuilder.addNewLap();
					break;
				case RACE_STINTS_MODE:
					//Si estamos en la lista de stints, podemos añadirlo a la lista de la carrera
					raceBuilder.addNewStint();
					break;
				case RACE_TECH_PROBLEMS_MODE:
					//Si estamos en la lista de stints, podemos añadirlo a la lista de la carrera
					if (techProblems) raceBuilder.addNewTechProblem();
					break;
			}
			
			lastRowIndex = row.getRowNum();		
		} //End of rows
			
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseTechProblems(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_STRING) {
			switch (celda.getColumnIndex()) {
			case 0:
				raceBuilder.setTechProblemLapInfo(celda.getStringCellValue().trim());
				break;
			case 1:
				raceBuilder.setTechProblemDescriptionInfo(celda.getStringCellValue());
				break;
			}
		}
	}
	
	private void parseDriverEnergy(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_STRING) {
			raceBuilder.setDriverEnergyStart(GPROUtils.getDriverEnergyAtStart(celda.getStringCellValue()));
			raceBuilder.setDriverEnergyEnd(GPROUtils.getDriverEnergyAtEnd(celda.getStringCellValue()));
		}
	}

	/**
	 * 
	 */
	private void checkWetRisks() {
		if (!hasClearWetRisks) {
			//No hay wet risks por tanto...
			//Lo que está en clearwetrisks son los multfunctionrisks...
			raceBuilder.setMalfunctRisks(raceBuilder.getRaceDataSheetModel().getClearWetRisks());
			//y los clearwetrisks son los cleardryrisks
			raceBuilder.setClearWetRisks(raceBuilder.getRaceDataSheetModel().getClearDryRisks());
		}
	}

	/**
	 * 
	 * @param currentMode
	 * @param currentRow
	 * @param currentColumn
	 * @param isNotBlank
	 * @param lastRowIndex 
	 * @return
	 */
	private int getNextMode(int currentMode, int currentRow, int currentColumn, boolean isNotBlank, int lastRowIndex) {
		int result = currentMode;
		
		boolean isThereAGap = ((currentRow - lastRowIndex) > 1);
		
		if ((currentMode == RACE_TRACK_AND_SEASON_MODE) && (currentRow < 4)) result = RACE_TRACK_AND_SEASON_MODE;
		else if ((currentMode == RACE_TRACK_AND_SEASON_MODE) && currentRow == 4) result = RACE_LAPS_MODE;
		else if (currentMode == RACE_LAPS_MODE && isThereAGap) result = RACE_RISKS_USED_MODE;
		else if (currentMode == RACE_RISKS_USED_MODE && isThereAGap) result = RACE_SETUP_USED_MODE;
		else if (currentMode == DRIVER_ENERGY_MODE && isThereAGap) result = RACE_SETUP_USED_MODE;
		else if (currentMode == RACE_SETUP_USED_MODE && isThereAGap) result = RACE_PARTS_PRE_LVL_MODE;
		else if (currentMode == RACE_PARTS_PRE_LVL_MODE) result = RACE_PARTS_LVL_MODE;
		else if (currentMode == RACE_PARTS_LVL_MODE) result = RACE_PARTS_START_WEAR_MODE;
		else if (currentMode == RACE_PARTS_START_WEAR_MODE) result = RACE_PARTS_END_WEAR_MODE;
		else if (currentMode == RACE_PARTS_END_WEAR_MODE && isThereAGap) result = RACE_START_FUEL_MODE;
		else if (currentMode == RACE_START_FUEL_MODE && isThereAGap) result = RACE_STINTS_PRE_MODE;
		else if (currentMode == RACE_STINTS_PRE_MODE) result = RACE_STINTS_MODE;
		else if (currentMode == RACE_STINTS_MODE && isThereAGap) result = RACE_TYRES_AFTER_RACE_MODE;
		else if (currentMode == RACE_TECH_PROBLEMS_MODE && isThereAGap) result = RACE_TYRES_AFTER_RACE_MODE;
		else if (currentMode == RACE_TYRES_AFTER_RACE_MODE && (raceBuilder.getRaceDataSheetModel().getEndTyres() != null)) result = RACE_FUEL_AFTER_RACE_MODE;
		
		return result;
	}
	
	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseEndFuel(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_NUMERIC) {
			switch (celda.getColumnIndex()) {
			case 1:
				raceBuilder.setEndFuel((int) celda.getNumericCellValue());
				break;
			}
		}
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseEndTyres(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_NUMERIC) {
			switch (celda.getColumnIndex()) {
			case 1:
				raceBuilder.setEndTyres((int) celda.getNumericCellValue());
				break;
			}
		}
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseStartFuel(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_NUMERIC) {
			switch (celda.getColumnIndex()) {
			case 1:
				raceBuilder.setStartFuel((int) celda.getNumericCellValue());
				break;
			}
		}
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseEndWearInfo(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_NUMERIC) {
			switch (celda.getColumnIndex()) {
			case 2:
				raceBuilder.setChassisEndWear((int) celda.getNumericCellValue());
				break;
			case 3:
				raceBuilder.setEngineEndWear((int) celda.getNumericCellValue());
				break;
			case 4:
				raceBuilder.setFrontWingEndWear((int) celda.getNumericCellValue());
				break;
			case 5:
				raceBuilder.setRearWingEndWear((int) celda.getNumericCellValue());
				break;
			case 6:
				raceBuilder.setUnderbodyEndWear((int) celda.getNumericCellValue());
				break;
			case 7:
				raceBuilder.setSidepodsEndWear((int) celda.getNumericCellValue());
				break;
			case 8:
				raceBuilder.setCoolingEndWear((int) celda.getNumericCellValue());
				break;
			case 9:
				raceBuilder.setGearboxEndWear((int) celda.getNumericCellValue());
				break;
			case 10:
				raceBuilder.setBrakesEndWear((int) celda.getNumericCellValue());
				break;
			case 11:
				raceBuilder.setSuspensionEndWear((int) celda.getNumericCellValue());
				break;
			case 12:
				raceBuilder.setElectronicsEndWear((int) celda.getNumericCellValue());
				break;
			}
		}
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseStartWearInfo(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_NUMERIC) {
			switch(celda.getColumnIndex()) {
			case 2:
				raceBuilder.setChassisStartWear((int)celda.getNumericCellValue());
				break;
			case 3:
				raceBuilder.setEngineStartWear((int)celda.getNumericCellValue());
				break;
			case 4:
				raceBuilder.setFrontWingStartWear((int)celda.getNumericCellValue());
				break;
			case 5:
				raceBuilder.setRearWingStartWear((int)celda.getNumericCellValue());
				break;
			case 6:
				raceBuilder.setUnderbodyStartWear((int)celda.getNumericCellValue());
				break;
			case 7:
				raceBuilder.setSidepodsStartWear((int)celda.getNumericCellValue());
				break;
			case 8:
				raceBuilder.setCoolingStartWear((int)celda.getNumericCellValue());
				break;
			case 9:
				raceBuilder.setGearboxStartWear((int)celda.getNumericCellValue());
				break;
			case 10:
				raceBuilder.setBrakesStartWear((int)celda.getNumericCellValue());
				break;
			case 11:
				raceBuilder.setSuspensionStartWear((int)celda.getNumericCellValue());
				break;
			case 12:
				raceBuilder.setElectronicsStartWear((int)celda.getNumericCellValue());
				break;
			}
		}
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseCarPartsLevelInfo(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_NUMERIC) {
			switch(celda.getColumnIndex()) {
			case 2:
				raceBuilder.setChassisLevel((int)celda.getNumericCellValue());
				break;
			case 3:
				raceBuilder.setEngineLevel((int)celda.getNumericCellValue());
				break;
			case 4:
				raceBuilder.setFrontWingLevel((int)celda.getNumericCellValue());
				break;
			case 5:
				raceBuilder.setRearWingLevel((int)celda.getNumericCellValue());
				break;
			case 6:
				raceBuilder.setUnderbodyLevel((int)celda.getNumericCellValue());
				break;
			case 7:
				raceBuilder.setSidepodsLevel((int)celda.getNumericCellValue());
				break;
			case 8:
				raceBuilder.setCoolingLevel((int)celda.getNumericCellValue());
				break;
			case 9:
				raceBuilder.setGearboxLevel((int)celda.getNumericCellValue());
				break;
			case 10:
				raceBuilder.setBrakesLevel((int)celda.getNumericCellValue());
				break;
			case 11:
				raceBuilder.setSuspensionLevel((int)celda.getNumericCellValue());
				break;
			case 12:
				raceBuilder.setElectronicsLevel((int)celda.getNumericCellValue());
				break;
			}								
			
		}
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseSetupInfo(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_NUMERIC) {
			switch(celda.getColumnIndex()) {
			case 0:
				raceBuilder.setFrontWingSetup((int)celda.getNumericCellValue());
				break;
			case 1:
				raceBuilder.setRearWingSetup((int)celda.getNumericCellValue());
				break;
			case 2:
				raceBuilder.setEngineSetup((int)celda.getNumericCellValue());
				break;
			case 3:
				raceBuilder.setBrakesSetup((int)celda.getNumericCellValue());
				break;
			case 4:
				raceBuilder.setGearSetup((int)celda.getNumericCellValue());
				break;
			case 5:
				raceBuilder.setSuspensionSetup((int)celda.getNumericCellValue());
				break;
			}								
		}
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseRisksInfo(Cell celda, int type) {
		if (type == Cell.CELL_TYPE_NUMERIC) {
			switch(celda.getColumnIndex()) {
			case 0:
				raceBuilder.setOvertakeRisks((int)celda.getNumericCellValue());
				break;
			case 1:
				raceBuilder.setDefendRisks((int)celda.getNumericCellValue());
				break;
			case 2:
				raceBuilder.setClearDryRisks((int)celda.getNumericCellValue());
				break;
			case 3:
				raceBuilder.setClearWetRisks((int)celda.getNumericCellValue());
				break;
			case 4:
				//Si llegamos a esta columna es que esta hoja tiene el campo clearWetRisks
				hasClearWetRisks = true;
				raceBuilder.setMalfunctRisks((int)celda.getNumericCellValue());
				break;
			}
		}
		else if (type == Cell.CELL_TYPE_STRING && celda.getColumnIndex() == 3) {
			if ("-".equals(celda.getStringCellValue())) {
				//Significa que aunque tiene el campo clearWetRisks el valor no ha sido especificado
				//Por tanto lo igualamos con los crearDryRisks que a estas alturas ya ha sido establecido
				raceBuilder.setClearWetRisks(raceBuilder.getRaceDataSheetModel().getClearDryRisks());
			}
		}
	}

	/**
	 * 
	 * @param trackSeasonInfoSet
	 * @param celda
	 * @return
	 */
	private void parseTrackInfo(Cell celda) {
		if (raceBuilder.getRaceDataSheetModel().getTrackName() == null) {
			raceBuilder.setTrackSeasonData(celda.getStringCellValue());
		}
	}

	/**
	 * 
	 * @param celda
	 */
	private void parseLapsInfo(Cell celda) {
		switch(celda.getColumnIndex()) {
		case 0:
			raceBuilder.setLapNumber((int)celda.getNumericCellValue());
			break;
		case 1:
			String lapTime = "";
			try {
				lapTime = celda.getStringCellValue();
			} catch (IllegalStateException ise) {
				//Cuando ocurre esto es porque el tiempo es por debajo del minuto
				//Esto devuelve el tiempo en milisegundos por lo que hay que dividirlo por 1000
				double lapTimeDobule = celda.getNumericCellValue();
				//Para que cumpla el patrón de tiempo de vuelta MM:SS:mmm
				lapTime = "0:" + Double.toString(lapTimeDobule / 1000.0);
			}
			raceBuilder.setLapTime(lapTime);
			break;
		case 2:
			raceBuilder.setLapPosition((int)celda.getNumericCellValue());
			break;
		case 3:
			raceBuilder.setLapTyres(celda.getStringCellValue());
			break;
		case 4:
			raceBuilder.setLapWeather(celda.getStringCellValue());
			break;
		case 5:
			raceBuilder.setLapTemperature((int)celda.getNumericCellValue());
			break;
		case 6:
			raceBuilder.setLapHumidity((int)celda.getNumericCellValue());
			break;
		case 7:
			raceBuilder.setLapEvent(celda.getStringCellValue());
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @param celda
	 * @param type
	 */
	private void parseStopsInfo(Cell celda, int type) {
		switch(celda.getColumnIndex()) {
		case 0:
			raceBuilder.setStopInfo(celda.getStringCellValue());
			break;
		case 1:
			raceBuilder.setStopReason(celda.getStringCellValue());
			break;
		case 2:
			raceBuilder.setStopTyresCondition((int)celda.getNumericCellValue());
			break;
		case 3:
			raceBuilder.setStopFuelLeft((int)celda.getNumericCellValue());
			break;
		case 4:
			raceBuilder.setStopRefilledInfo(celda.getStringCellValue());
			break;
		case 5:
			raceBuilder.setStopPitTime((int)celda.getNumericCellValue());
			break;
		default:
			break;
		}		
	}

	
}
