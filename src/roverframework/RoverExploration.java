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

	public static void main(String[] args) {
		if (args.length >= 1) {
			String input = args[0];
			if ( (args.length >= 2) && (input.startsWith("--inputfile")) ) {
				String argumentFile = args[1];
				try {
					input = readFileAsString(argumentFile);
				} catch (IOException ioError) {
					System.out.println("Cannot read input file = " + argumentFile + ":Error = " + ioError.getMessage());
				}
			}
			List<String> lineData = parseInputStream(input);
			ArrayList<Rover> landedRovers = new ArrayList<Rover>();
			Rover currentRover = null;
			if ((lineData != null) && (lineData.size() > 1)) {
				int linepos = 0;
				for (String line : lineData) {
					if (linepos == 0) {
						mapBounds.readInput(line);
					} else {
						int altLine = (linepos - 1) % 2;
						if (altLine == 0) { // Parse the parameters for the next rover
							currentRover = new Rover(line);
							if (mapBounds != null)
								currentRover.setBounds(mapBounds);
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
				System.out.println("Rover output ::");
				for (Rover myRover : landedRovers) {
					System.out.println(myRover);
				}
			}
		} else {
			System.out.println("Please supply arguments to the RoverChallenge application");
			System.out.println("Allowed arguments");
			System.out.println("--inputfile <file>");
		}
	}

}
