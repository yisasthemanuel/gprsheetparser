package org.jlobato.gpro.sheetparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.jlobato.gpro.utils.GPROUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author jlobato
 *
 */
public class RaceHtmlParser implements GPRORaceSheetParser {
	
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
	private boolean driverEnergy = false;

	/**
	 * 
	 * @param is
	 */
	protected RaceHtmlParser(InputStream is) {
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
		try {
			Document doc = Jsoup.parse(is, "UTF-8", "");
			
			// Creamos el model builder
			raceBuilder = new DefaultRaceDataSheetModelBuilder(true);
			Elements tables = doc.getElementsByTag("table");
			
			//Por cada tabla, recorre las filas
			int i = 1;
			for (Element table : tables) {
				Integer currentTable = new Integer(i);
				//RacePartialParser partialParser = PARTIAL_PARSERS.get(currentTable);
				RacePartialParser partialParser = getNextParser(currentTable, table, tables.size());
				if (partialParser != null) {
					partialParser.parse(table, raceBuilder);
				}
				i++;
			}
		} catch(Exception e) {
			throw e;
		} finally {
			try { is.close(); } catch(Exception e) {}
		}
	}	

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String baseDirectory = "C:/Desarrollo/gpro-dev/racedata/";
		
		try (FileInputStream energy = new FileInputStream(new File(baseDirectory, "MIKKO/Season 68/S68 - R1 - Laguna Seca.xls"))) {
			//Directorio base
			System.out.println("Starting... Energy One HTML");
			
			GPRORaceSheetParser parser = new RaceHtmlParser(energy);
			RaceDataSheetModel data = parser.readRaceDataSheet();
			System.out.println("Race model: " + data);
			
			System.out.println("*****OK");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		try (FileInputStream energy = new FileInputStream(new File(baseDirectory, "MIKKO/Season 68/S67 - R1 - Kyalami.xls"))) {
			//Directorio base
			System.out.println("Starting... NO Energy One HTML");
			
			GPRORaceSheetParser parser = new RaceHtmlParser(energy);
			RaceDataSheetModel data = parser.readRaceDataSheet();
			System.out.println("Race model: " + data);
			
			
			System.out.println("*****OK");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (FileInputStream energy = new FileInputStream(new File(baseDirectory, "MIKKO/Season 68/S68 - R3 - Adelaide.xls"))) {
			//Directorio base
			System.out.println("Starting... Energy One HTML");
			
			GPRORaceSheetParser parser = new RaceHtmlParser(energy);
			RaceDataSheetModel data = parser.readRaceDataSheet();
			System.out.println("Race model: " + data);
			
			System.out.println("*****OK");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		/*try (FileInputStream energy = new FileInputStream(new File(baseDirectory, "MIKKO/Season 68/S68 - R1 - Laguna Seca.xlsx"))) {
			//Directorio base
			System.out.println("Starting... Energy One EXCEL");
			
			GPRORaceSheetParser parser = new ExcelSheetParser(energy);
			RaceDataSheetModel data = parser.readRaceDataSheet();
			System.out.println("Race model: " + data);
			
			
			System.out.println("*****OK");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (FileInputStream energy = new FileInputStream(new File(baseDirectory, "MIKKO/Season 68/S67 - R1 - Kyalami.xlsx"))) {
			//Directorio base
			System.out.println("Starting... NO Energy One EXCEL");
			
			GPRORaceSheetParser parser = new ExcelSheetParser(energy);
			RaceDataSheetModel data = parser.readRaceDataSheet();
			System.out.println("Race model: " + data);
			
			
			System.out.println("*****OK");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		System.exit(0);
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static interface RacePartialParser {
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder);
		public boolean isOptional();
	}
	
	/**
	 * 
	 * @author jmplobato
	 *
	 */
	private static abstract class MandatoryPartialParser implements RacePartialParser {
		
		/**
		 * 
		 * @return
		 */
		public boolean isOptional() {
			return false;
		}
	}
	
	/**
	 * 
	 * @author jmplobato
	 *
	 */
	private static class DriverEnergyPartialParser implements RacePartialParser {

		@Override
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			Element row = rows.get(1);
			String energyText = row.getElementsByTag(HTML_TABLE_COLUMN_TAG).first().text();
			raceModelBuilder.setDriverEnergyStart(GPROUtils.getDriverEnergyAtStart(energyText));
			raceModelBuilder.setDriverEnergyEnd(GPROUtils.getDriverEnergyAtEnd(energyText));
		}

		@Override
		public boolean isOptional() {
			return true;
		}
		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class SeasonAndTrackPartialParser extends MandatoryPartialParser {

		@Override
		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//Sólo nos interesa la primera
			Element row = rows.first();
			String seasonAndTrack = row.getElementsByTag(HTML_TABLE_COLUMN_TAG).first().text();
			raceModelBuilder.setTrackSeasonData(seasonAndTrack);
		}
		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class StartFuelPartialParser extends MandatoryPartialParser {

		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//Sólo nos interesa la primera fila
			Element row = rows.first();
			//Y de la primera fila, sólo la segunda columna
			String startFuel = row.getElementsByTag(HTML_TABLE_COLUMN_TAG).get(1).text();
			raceModelBuilder.setStartFuel(Integer.parseInt(startFuel));
		}
		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class LapTimesPartialParser extends MandatoryPartialParser {

		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//La primera fila no nos interesa
			rows.remove(0);
			for (Element row : rows) {
				Elements laps = row.getElementsByTag(HTML_TABLE_COLUMN_TAG);
				int i = 1;
				for (Element lap : laps) {
					switch (i) {
					case 1:
						raceModelBuilder.setLapNumber(Integer.parseInt(lap.text()));
						break;
					case 2:
						raceModelBuilder.setLapTime(lap.text());
						break;
					case 3:
						raceModelBuilder.setLapPosition(Integer.parseInt(lap.text()));
						break;
					case 4:
						raceModelBuilder.setLapTyres(lap.text());
						break;
					case 5:
						raceModelBuilder.setLapWeather(lap.text());
						break;
					case 6:
						//FIX. A veces llega un símbolo de grado tras la temperatura
						int lapTemp = -1;
						try {
							lapTemp = Integer.parseInt(lap.text());
						} catch(Exception e) {
							//Intentamos hacer el parse quitando el último carácter ('º')
							lapTemp = Integer.parseInt(lap.text().substring(0, lap.text().length() - 1));
						}
						raceModelBuilder.setLapTemperature(lapTemp);
						break;
					case 7:
						//FIX. A veces llega un símbolo de % tras la humedad
						int lapHumidity = -1;
						try {
							lapHumidity = Integer.parseInt(lap.text());
						} catch(Exception e) {
							//Intentamos hacer el parse quitando el último carácter ('%')
							lapHumidity = Integer.parseInt(lap.text().substring(0, lap.text().length() - 1));
						}
						raceModelBuilder.setLapHumidity(lapHumidity);
						break;
					case 8:
						raceModelBuilder.setLapEvent(lap.text());
						break;
					default:
						break;
					}
					i++;
					
				}
				raceModelBuilder.addNewLap();
			}
			
		}
		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class RisksUsedPartialParser extends MandatoryPartialParser {

		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//La primera y segunda fila no nos interesan
			Elements risksColumns = rows.get(2).getElementsByTag(HTML_TABLE_COLUMN_TAG);
			int i = 1;
			boolean numberFormatExcepcion4 = false;
			for (Element col : risksColumns) {
				switch (i) {
				case 1:
					raceModelBuilder.setOvertakeRisks(Integer.parseInt(col.text()));
					break;
				case 2:
					raceModelBuilder.setDefendRisks(Integer.parseInt(col.text()));
					break;
				case 3:
					raceModelBuilder.setClearDryRisks(Integer.parseInt(col.text()));
					break;
				case 4:
					//TODO Falta controlar el caso en que no había wet risks y los datos se exportaron antes de que los hubiera
					//En este caso esta columna contiene los malfunction risks y no hay siguiente columna
					try {
						raceModelBuilder.setClearWetRisks(Integer.parseInt(col.text()));
						//Si falla el 5 es que la última columna es de malfunction
						raceModelBuilder.setMalfunctRisks(Integer.parseInt(col.text()));
						numberFormatExcepcion4 = false;
					} catch (NumberFormatException nfe) {
						numberFormatExcepcion4 = true;
						//Si da un error de formato es que la carrera es de antes de que hubiera wet risks
						//PERO los datos se exportaron cuando ya había wet risks
						//En este caso los wet risks son los mismos que los dry risks
						raceModelBuilder.setClearWetRisks(raceModelBuilder.getRaceDataSheetModel().getClearDryRisks());
					}
					break;
				case 5:
					try {
						raceModelBuilder.setMalfunctRisks(Integer.parseInt(col.text()));
					} catch (NumberFormatException nfe) {
						//Si da un error de formato es que la carrera es de antes de que hubiera wet risks
						//PERO los datos se exportaron cuando ya había wet risks
						//En este caso los wet risks son los mismos que los dry risks
						if (numberFormatExcepcion4) {
							raceModelBuilder.setClearWetRisks(raceModelBuilder.getRaceDataSheetModel().getClearDryRisks());
							raceModelBuilder.setMalfunctRisks(raceModelBuilder.getRaceDataSheetModel().getClearDryRisks());
						}
						else {
							//Si no da error en la columna 4 es que sólo hay cuatro columnas y la última es de malfunction
							//Establecemos los wet risks
							raceModelBuilder.setClearWetRisks(raceModelBuilder.getRaceDataSheetModel().getClearDryRisks());
						}
					}
					break;
				default:
					break;
				}
				i++;
			}
		}
		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class SetupUsedPartialParser extends MandatoryPartialParser {

		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//La primera y segunda fila no nos interesan
			Elements risksColumns = rows.get(2).getElementsByTag(HTML_TABLE_COLUMN_TAG);
			int i = 1;
			for (Element col : risksColumns) {
				switch (i) {
				case 1:
					raceModelBuilder.setFrontWingSetup(Integer.parseInt(col.text()));
					break;
				case 2:
					raceModelBuilder.setRearWingSetup(Integer.parseInt(col.text()));
					break;
				case 3:
					raceModelBuilder.setEngineSetup(Integer.parseInt(col.text()));
					break;
				case 4:
					raceModelBuilder.setBrakesSetup(Integer.parseInt(col.text()));
					break;
				case 5:
					raceModelBuilder.setGearSetup(Integer.parseInt(col.text()));
					break;
				case 6:
					raceModelBuilder.setSuspensionSetup(Integer.parseInt(col.text()));
					break;
				default:
					break;
				}
				i++;
			}
		}
		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class CarPartsPartialParser extends MandatoryPartialParser {

		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//La primera fila no nos interesa
			//La segunda tiene los levels
			Elements levelsColumns = rows.get(1).getElementsByTag(HTML_TABLE_COLUMN_TAG);
			int i = 1;
			for (Element col : levelsColumns) {
				switch (i) {
				case 2:
					raceModelBuilder.setChassisLevel(Integer.parseInt(col.text()));
					break;
				case 3:
					raceModelBuilder.setEngineLevel(Integer.parseInt(col.text()));
					break;
				case 4:
					raceModelBuilder.setFrontWingLevel(Integer.parseInt(col.text()));
					break;
				case 5:
					raceModelBuilder.setRearWingLevel(Integer.parseInt(col.text()));
					break;
				case 6:
					raceModelBuilder.setUnderbodyLevel(Integer.parseInt(col.text()));
					break;
				case 7:
					raceModelBuilder.setSidepodsLevel(Integer.parseInt(col.text()));
					break;
				case 8:
					raceModelBuilder.setCoolingLevel(Integer.parseInt(col.text()));
					break;
				case 9:
					raceModelBuilder.setGearboxLevel(Integer.parseInt(col.text()));
					break;
				case 10:
					raceModelBuilder.setBrakesLevel(Integer.parseInt(col.text()));
					break;
				case 11:
					raceModelBuilder.setSuspensionLevel(Integer.parseInt(col.text()));
					break;
				case 12:
					raceModelBuilder.setElectronicsLevel(Integer.parseInt(col.text()));
					break;
				default:
					break;
				}
				i++;
			}
			
			//La tercera tiene los start wears
			Elements wearColumns = rows.get(2).getElementsByTag(HTML_TABLE_COLUMN_TAG);
			i = 1;
			for (Element col : wearColumns) {
				switch (i) {
				case 2:
					raceModelBuilder.setChassisStartWear(Integer.parseInt(col.text()));
					break;
				case 3:
					raceModelBuilder.setEngineStartWear(Integer.parseInt(col.text()));
					break;
				case 4:
					raceModelBuilder.setFrontWingStartWear(Integer.parseInt(col.text()));
					break;
				case 5:
					raceModelBuilder.setRearWingStartWear(Integer.parseInt(col.text()));
					break;
				case 6:
					raceModelBuilder.setUnderbodyStartWear(Integer.parseInt(col.text()));
					break;
				case 7:
					raceModelBuilder.setSidepodsStartWear(Integer.parseInt(col.text()));
					break;
				case 8:
					raceModelBuilder.setCoolingStartWear(Integer.parseInt(col.text()));
					break;
				case 9:
					raceModelBuilder.setGearboxStartWear(Integer.parseInt(col.text()));
					break;
				case 10:
					raceModelBuilder.setBrakesStartWear(Integer.parseInt(col.text()));
					break;
				case 11:
					raceModelBuilder.setSuspensionStartWear(Integer.parseInt(col.text()));
					break;
				case 12:
					raceModelBuilder.setElectronicsStartWear(Integer.parseInt(col.text()));
					break;
				default:
					break;
				}
				i++;
			}
			
			//La cuarta tiene los end wears
			Elements endColumns = rows.get(3).getElementsByTag(HTML_TABLE_COLUMN_TAG);
			i = 1;
			for (Element col : endColumns) {
				switch (i) {
				case 2:
					raceModelBuilder.setChassisEndWear(Integer.parseInt(col.text()));
					break;
				case 3:
					raceModelBuilder.setEngineEndWear(Integer.parseInt(col.text()));
					break;
				case 4:
					raceModelBuilder.setFrontWingEndWear(Integer.parseInt(col.text()));
					break;
				case 5:
					raceModelBuilder.setRearWingEndWear(Integer.parseInt(col.text()));
					break;
				case 6:
					raceModelBuilder.setUnderbodyEndWear(Integer.parseInt(col.text()));
					break;
				case 7:
					raceModelBuilder.setSidepodsEndWear(Integer.parseInt(col.text()));
					break;
				case 8:
					raceModelBuilder.setCoolingEndWear(Integer.parseInt(col.text()));
					break;
				case 9:
					raceModelBuilder.setGearboxEndWear(Integer.parseInt(col.text()));
					break;
				case 10:
					raceModelBuilder.setBrakesEndWear(Integer.parseInt(col.text()));
					break;
				case 11:
					raceModelBuilder.setSuspensionEndWear(Integer.parseInt(col.text()));
					break;
				case 12:
					raceModelBuilder.setElectronicsEndWear(Integer.parseInt(col.text()));
					break;
				default:
					break;
				}
				i++;
			}
			
		}
		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class PitStopsPartialParser extends MandatoryPartialParser {

		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//Pasamos de la primera fila
			rows.remove(0);
			for (Element row : rows) {
				int i = 1;
				Elements pitstops = row.getElementsByTag(HTML_TABLE_COLUMN_TAG);
				for (Element pitstop: pitstops) {
					switch(i) {
					case 1:
						raceModelBuilder.setStopInfo(pitstop.text());
						break;
					case 2:
						raceModelBuilder.setStopReason(pitstop.text());
						break;
					case 3:
						raceModelBuilder.setStopTyresCondition(Integer.parseInt(pitstop.text()));
						break;
					case 4:
						//Si no viene la cantidad de combustible que queda, lo dejamos a nulo
						if (!(pitstop.text() == null || pitstop.text().equals(""))) {
							raceModelBuilder.setStopFuelLeft(Integer.parseInt(pitstop.text()));
						}
						break;
					case 5:
						raceModelBuilder.setStopRefilledInfo(pitstop.text());
						break;
					case 6:
						raceModelBuilder.setStopPitTime(GPROUtils.getPitTimeMillis(pitstop.text()));
						break;
					default:
						break;
					}
					i++;
				}
				//Añadimos el nuevo stint
				raceModelBuilder.addNewStint();
			}	
		}		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class TechProblemsPartialParser extends MandatoryPartialParser {

		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//Pasamos de la primera fila
			rows.remove(0);
			for (Element row : rows) {
				int i = 1;
				Elements techproblems = row.getElementsByTag(HTML_TABLE_COLUMN_TAG);
				for (Element techproblem: techproblems) {
					switch(i) {
					case 1:
						raceModelBuilder.setTechProblemLapInfo(techproblem.text());
						break;
					case 2:
						raceModelBuilder.setTechProblemDescriptionInfo(techproblem.text());
						break;
					default:
						break;
					}
					i++;
				}
				//Añadimos el nuevo stint
				raceModelBuilder.addNewTechProblem();
			}	
		}		
	}
	
	/**
	 * 
	 * @author jlobato
	 *
	 */
	private static class AfterFinishPartialParser extends MandatoryPartialParser {

		/**
		 * 
		 */
		public void parse(Element table, RaceDataSheetModelBuilder raceModelBuilder) {
			Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
			//La primera fila para saber los litros que quedaron
			Element row = rows.first();
			//En la segunda columna tenemos el estado de los neumáticos
			String finishLiters = row.getElementsByTag(HTML_TABLE_COLUMN_TAG).get(1).text();
			raceModelBuilder.setEndTyres(Integer.parseInt(finishLiters));
			
			//Ahora nos vamos a la tercera fila para los litros de combustible que quedaron
			row = rows.get(2);
			//En la segunda columna tenemos lo que buscamos
			String finishTyres = row.getElementsByTag(HTML_TABLE_COLUMN_TAG).get(1).text();
			raceModelBuilder.setEndFuel(Integer.parseInt(finishTyres));
		}		
	}
	
	/**
	 * 
	 * @param i
	 * @param currentTable
	 * @param totalTables
	 * @return
	 */
	public RacePartialParser getNextParser(int nTable, Element currentTable, int totalTables) {
		RacePartialParser result = null;
		String firstColumText = null;
		switch (nTable) {
		case 4:
			//Cuarta tabla: o setup o driver energy
			firstColumText = getFirstColumnText(currentTable);
			if (firstColumText.startsWith(DRIVER_PREFIX)) {
				//Driver
				result = PARTIAL_PARSERS.get(DRIVER_ENERGY);
				this.driverEnergy = true;
			}
			else {
				//Setup
				result = PARTIAL_PARSERS.get(SETUP_USED);
			}
			break;
		case 8:
			// PIT_STOPS
			// Leemos la primera fila y la primera columna debe tener el texto "Pit"
			firstColumText = getFirstColumnText(currentTable);
			if (firstColumText.equals("Pit")) {
				// Se trata de la tabla de pitstops
				result = PARTIAL_PARSERS.get(PIT_STOPS);
			} else {
				// Si no viene la de pitstops, o viene la de final de carrera o viene la de
				// problemas técnicos
				// Vemos si es la de problemas tecnicos
				String headerText = getFirstHeaderText(currentTable);
				if (headerText.equals(TECHNICAL_PROBLEMS)) {
					// Es la de problemas técnicos
					result = PARTIAL_PARSERS.get(TECH_PROBLEMS);
				} else {
					// En cualquier otro caso es la de fin de carrera
					result = PARTIAL_PARSERS.get(FINISH_FUEL_AND_TYRES);
				}
			}
			break;
		// case 8:
		case 9:
			// Si es la octava tabla puede ser o problemas técnicos o final de carrera
			String headerText = getFirstHeaderText(currentTable);
			if (headerText.equals(TECHNICAL_PROBLEMS)) {
				// Es la tabla de problemas tecnicos
				result = PARTIAL_PARSERS.get(TECH_PROBLEMS);
			} else {
				// Es la tabla de fin de carrera
				result = PARTIAL_PARSERS.get(FINISH_FUEL_AND_TYRES);
			}
			break;
		// case 9:
		// Si llegamos a una novena tabla, solo puede ser la de final de carrera, así
		// que entra dentro del caso por defecto
		// break;
		default:
			if (!driverEnergy) {
				//Si la hoja no tiene información de driver energy, el número de tabla me da el parser
				result = PARTIAL_PARSERS.get(nTable);
			}
			else {
				//En caso contrario, el número de tabla es uno más que la correspondencia con el parser
				result = PARTIAL_PARSERS.get(nTable - 1);
			}
			break;
		}

		return result;
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 */
	private String getFirstColumnText(Element table) {
		String result = "";
		Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
		Element firstRow = rows.first();
		Element firstColumn = firstRow.getElementsByTag(HTML_TABLE_COLUMN_TAG).first();
		if (firstColumn != null) {
			result = firstColumn.text();
		}
		return result;
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 */
	private String getFirstHeaderText(Element table) {
		String result = "";
		Elements rows = table.getElementsByTag(HTML_TABLE_ROW_TAG);
		Element firstRow = rows.first();
		Element firstColumn = firstRow.getElementsByTag("th").first();
		if (firstColumn != null) {
			result = firstColumn.text();
		}
		else {
			//No encontramos por th, buscamos por td
			firstColumn = firstRow.getElementsByTag(HTML_TABLE_COLUMN_TAG).first();
			if (firstColumn != null) {
				result = firstColumn.text();
			}
		}
		return result;
	}
	
	/**
	 * 
	 */
	private static HashMap<Integer, RaceHtmlParser.RacePartialParser> PARTIAL_PARSERS = new HashMap<Integer, RaceHtmlParser.RacePartialParser>();
	
	/**
	 * 
	 */
	public static final String HTML_TABLE_ROW_TAG = "tr";
	
	/**
	 * 
	 */
	public static final String HTML_TABLE_COLUMN_TAG = "td";

	
	public static final Integer SEASON_AND_TRACK = new Integer(1);
	public static final Integer LAP_TIMES = new Integer(2);
	public static final Integer RISKS_USED = new Integer(3);
	public static final Integer SETUP_USED = new Integer(4);
	public static final Integer CAR_PARTS = new Integer(5);
	public static final Integer START_FUEL = new Integer(6);
	public static final Integer PIT_STOPS = new Integer(7);
	public static final Integer TECH_PROBLEMS = new Integer(8);
	public static final Integer FINISH_FUEL_AND_TYRES = new Integer(9);
	public static final Integer DRIVER_ENERGY = new Integer(10);
	
	/**
	 * 
	 */
	static  {
		PARTIAL_PARSERS.put(SEASON_AND_TRACK, new SeasonAndTrackPartialParser());
		PARTIAL_PARSERS.put(LAP_TIMES, new LapTimesPartialParser());
		PARTIAL_PARSERS.put(RISKS_USED, new RisksUsedPartialParser());
		PARTIAL_PARSERS.put(DRIVER_ENERGY, new DriverEnergyPartialParser());
		PARTIAL_PARSERS.put(SETUP_USED, new SetupUsedPartialParser());
		PARTIAL_PARSERS.put(CAR_PARTS, new CarPartsPartialParser());
		PARTIAL_PARSERS.put(START_FUEL, new StartFuelPartialParser());
		PARTIAL_PARSERS.put(PIT_STOPS, new PitStopsPartialParser());
		PARTIAL_PARSERS.put(TECH_PROBLEMS, new TechProblemsPartialParser());
		PARTIAL_PARSERS.put(FINISH_FUEL_AND_TYRES, new AfterFinishPartialParser());
	}
	
	
}
