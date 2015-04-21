package dk.statsbiblioteket.cdrip.robot;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Simplest possible implementation. Provide the jobfile path string as args[0].
 * Writes files to current directory
 *
 */
public class Main {

	private static final String PATTERN1 = "'CD-Inspector_'yyyy-MM-dd_HH-mm'.log'";
	private static final String PATTERN2 = "'Result_'yyyy-MM-dd_HH-mm'.log'";

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 1) {
			throw new IllegalArgumentException("usage: jobfile-path");
		}
		String jobfile = args[0];

		LocalDateTime startTime = LocalDateTime.now();

		String filename1 = startTime.format(ofPattern(PATTERN1));
		File file1 = new File(filename1);
		PrintWriter pw1 = new PrintWriter(file1);
		pw1.println("CD-Inspector Version: 2. 0. 1. 4  Logfile");
		pw1.println("11/04   14:08   " + jobfile + " loaded successful");
		pw1.close();

		String filename2 = startTime.format(ofPattern(PATTERN2));
		File file2 = new File(filename2);
		PrintWriter pw2 = new PrintWriter(file2);
		pw2.println("CD-Inspector Version: 2. 0. 1. 4  Logfile");
		pw2.close();
	}
}
