package dk.statsbiblioteket.cdrip.robot;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeSet;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Simplest possible implementation for jobfile_627.job.xml. Provide the jobfile path string as args[0].
 * Writes files to current directory
 */
public class Main {

    private static final String PATTERN1 = "'CD-Inspector_'yyyy-MM-dd_HH-mm'.log'";
    private static final String PATTERN2 = "'Result_'yyyy-MM-dd_HH-mm'.log'";

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            throw new IllegalArgumentException("usage: jobfile-path");
        }
        String jobfilename = args[0];

        LocalDateTime startTime = LocalDateTime.now();

        // Job.readJobFileContent()
        SAXReader reader = new SAXReader();
        reader.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                System.out.println("Resolving entity publicID='" + publicId + "' SystemID='" + systemId + "'");
                if (systemId.contains("Joblist")) {
                    return new InputSource(new StringReader(""));
                } else {
                    return null;
                }
            }
        });
        org.dom4j.Document doc = reader.read(jobfilename);


        TreeSet<String> slots = new TreeSet<>();

        List<Element> list = doc.selectNodes("/JOBLIST/BLOCK");

        for (Element e : list) {
            String outputNameText = e.valueOf("OUTPUT/NAME/text()"); // 208046/track.wav
            String inputNameText = e.valueOf("OUTPUT/INPUT/NAME/text()"); // 101
            slots.add(inputNameText);
        }

        String filename1 = startTime.format(ofPattern(PATTERN1));
        File file1 = new File(filename1);
        PrintWriter pw1 = new PrintWriter(file1);
        pw1.println("CD-Inspector Version: 2. 0. 1. 4  LogfileStart:   10/21/2014   14:52\n" +
                "\n" +
                "Date    Time    File                                              Status      Comment                               \n" +
                "--------------------------------------------------------------------------------------------------------------------\n" +
                "10/21   14:52   Init UserManager:                                 ok          \n");
        pw1.println("10/21   14:52   " + jobfilename + " loaded successful");
        pw1.println("10/21   15:03   Scanning JukeBox:                                 ok          \n" +
                "10/21   15:03   Found " + slots.size() + " filled Slots");


        for (String slot : slots) {
            String pre = "10/21   15:03   Slot: " + slot + " ";
            pw1.println(pre + "In work");
            pw1.println(pre + "ups/ean code: not found");
            pw1.println(pre + "track: 01 isrc code: FAKE" + slot);
            pw1.println(pre + "Operation was successfull");
        }
        pw1.println("10/21   15:11   All Slots processed");
        pw1.close();

        String filename2 = startTime.format(ofPattern(PATTERN2));
        File file2 = new File(filename2);
        PrintWriter pw2 = new PrintWriter(file2);
        pw2.println("CD-Inspector Version: 2. 0. 1. 4  Logfile\n" +
                "\n" +
                "Start:   10/21/2014   14:52\n" +
                "\n" +
                "Date    Time    File                                              Status      Comment                               \n" +
                "--------------------------------------------------------------------------------------------------------------------\n" +
                "CD-Inspector Version: 2. 0. 1. 4  Logfile\n" +
                "\n" +
                "Start:   10/21/2014   15:00\n" +
                "\n" +
                "Date    Time    File                                              Status      Comment                               \n" +
                "--------------------------------------------------------------------------------------------------------------------");
        for (String slot : slots) {
            pw2.println("10/21   15:06   " + slot + "                                               ok");
        }
        pw2.close();
    }
}
