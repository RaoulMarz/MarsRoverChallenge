package roverframework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoverExploration {
	private static Coordinate mapBounds = new Coordinate(9999, 9999);

	public static List<String> parseInputStream(String input) {
		List<String> result = null;
		if (input.length() > 0) {
			result = Arrays.asList(input.split("\n"));
		}
		return result;
	}

	public static List<Character> getCharacterTokens(String input) {
		List<Character> result = null;
		if (input.length() > 0) {
			result = new ArrayList<Character>();
			for (int ix = 0; ix < input.length(); ix++) {
				Character chartoken = input.charAt(ix);
				if (Character.isWhitespace(chartoken))
					continue;
				result.add(chartoken);
			}
		}
		return result;
	}

	public static String readFileAsString(String file) throws IOException {
		InputStream inputStream = new FileInputStream(file);

		StringBuilder textBuilder = new StringBuilder();
		try (Reader reader = new BufferedReader(
				new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			int c = 0;
			while ((c = reader.read()) != -1) {
				textBuilder.append((char) c);
			}
		}
		return textBuilder.toString();
	}
	
	public static void reportInputData(List<String> inputReport) {
		System.out.println("Rover input ::");
		for (String reportLine : inputReport) {
			System.out.println(reportLine);
		}
	}
	
	public static void reportOutputData(List<Rover> listRovers) {
		System.out.println("Rover output ::");
		for (Rover myRover : listRovers) {
			System.out.println(myRover);
		}
	}

	public static void main(String[] args) {
		if (args.length >= 1) {
			ReportCategory reportCat = ReportCategory.REPORT_OUTPUT;			
			String reportArgument = "";
			String input = "";
			for (int argIndex = 0; argIndex < args.length; argIndex++) {
				String anchorArgument = args[argIndex];
				if ((args.length >= 2) && (anchorArgument.startsWith("--report"))) {
					reportArgument = args[argIndex + 1];
					argIndex += 1;
					if (reportArgument.trim().compareTo("input") == 0)
						reportCat = ReportCategory.REPORT_INPUT;
					if (reportArgument.trim().compareTo("output") == 0)
						reportCat = ReportCategory.REPORT_OUTPUT;
					if (reportArgument.trim().compareTo("all") == 0)
						reportCat = ReportCategory.REPORT_ALL;
				}
				if ((args.length >= 2) && (anchorArgument.startsWith("--inputfile"))) {
					String argumentFile = args[argIndex + 1];
					argIndex += 1;
					try {
						input = readFileAsString(argumentFile);
					} catch (IOException ioError) {
						System.out.println(
								"Cannot read input file = " + argumentFile + ":Error = " + ioError.getMessage());
					}
				}
			}
			List<String> lineData = parseInputStream(input);
			ArrayList<Rover> landedRovers = new ArrayList<Rover>();
			ArrayList<String> inputReport = new ArrayList<String>();
			Rover currentRover = null;
			int roverNumber = 0;
			if ((lineData != null) && (lineData.size() > 1)) {
				int linepos = 0;
				for (String line : lineData) {
					if (linepos == 0) {
						mapBounds.readInput(line);
						inputReport.add("Map bounds : " + mapBounds.toString());
					} else {
						int altLine = (linepos - 1) % 2;
						if (altLine == 0) { // Parse the parameters for the next rover
							roverNumber += 1;
							currentRover = new Rover(line);
							if (mapBounds != null)
								currentRover.setBounds(mapBounds);
							inputReport.add("Initialise Rover # " + roverNumber + " : " + currentRover.toString());
							landedRovers.add(currentRover);
							linepos += 1;
							continue;
						}
						List<Character> charCommandTokens = getCharacterTokens(line);
						if (charCommandTokens != null) {
							for (char cmdtoken : charCommandTokens) {
								// Apply the character commands to the current rover
								if (currentRover != null)
									currentRover.applyCommand(cmdtoken);
							}
						}
					}
					linepos += 1;
				}
				switch (reportCat) {
				case REPORT_INPUT : {
					reportInputData(inputReport);
					break;					
				}
				case REPORT_OUTPUT : {
					reportOutputData(landedRovers);
					break;					
				}
				case REPORT_ALL : {
					reportInputData(inputReport);
					reportOutputData(landedRovers);
					break;					
				}
				default: {
					
				}
				}
			}
		} else {
			System.out.println("Please supply arguments to the RoverChallenge application");
			System.out.println("Allowed arguments");
			System.out.println("--inputfile <file>");
			System.out.println("--report [input,output,all]");
		}
	}

}
