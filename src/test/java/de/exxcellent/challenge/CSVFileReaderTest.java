package de.exxcellent.challenge;

import com.opencsv.CSVWriter;

import com.opencsv.CSVWriterBuilder;
import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.dataObjects.DataObjectFactory;
import de.exxcellent.challenge.dataObjects.impl.DataObjectStandard;
import de.exxcellent.challenge.fileReader.impl.CSVFileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for the csv file reader class
 */
public class CSVFileReaderTest {

    DataObjectFactory dataObjectFactory = new DataObjectFactory();

    /**
     * compares original data values to values, which are written to file and then read in by the CSVFileReader
     * while using standard delimiter and header
     */
    @Test
    void parseCSVWithHeader() {
        String testFileName = "src/test/resources/testCSVHeader.csv";

        String colAName = "1";
        String colBName = "2";
        String colCName = "3";
        
        String[] header = new String[] {colAName, colBName, colCName};
        String[] line1 = new String[] {"0", "1", "2"};
        String[] line2 = new String[] {"3", "4", "5"};
        String[] line3 = new String[] {"6", "7", "8"};
        String[] line4 = new String[] {"9", "10", "11"};

        List<String[]> dataList = Arrays.asList(header, line1, line2, line3, line4);

        Path testFilePath = Paths.get(testFileName);

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(testFilePath.toString()));
            writer.writeAll(dataList);
            writer.close();

            CSVFileReader reader = new CSVFileReader(testFileName, dataObjectFactory);

            List<DataObject> entries = reader.getEntries();

            testDataListEquality(line1, line2, line3, line4, entries);

            File testFile = new File(testFileName);
            testFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * compares original data values to values, which are written to file and then read in by the CSVFileReader
     * while using no header
     */
    @Test
    void parseCSVNoHeader() {
        String testFileName = "src/test/resources/testCSVNoHeader.csv";

        String[] line1 = new String[] {"0", "1", "2"};
        String[] line2 = new String[] {"3", "4", "5"};
        String[] line3 = new String[] {"6", "7", "8"};
        String[] line4 = new String[] {"9", "10", "11"};

        List<String[]> dataList = Arrays.asList(line1, line2, line3, line4);

        Path testFilePath = Paths.get(testFileName);

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(testFilePath.toString()));
            writer.writeAll(dataList);
            writer.close();

            CSVFileReader reader = new CSVFileReader(testFileName, dataObjectFactory, false);

            List<DataObject> entries = reader.getEntries();

            testDataListEquality(line1, line2, line3, line4, entries);

            File testFile = new File(testFileName);
            testFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * compares original data values to values, which are written to file and then read in by the CSVFileReader
     * while using non-standard delimiter
     */
    @Test
    void parseCSVDifferentDelimiter() {
        String testFileName = "src/test/resources/testCSVDifferentDelimiter.csv";

        char delimiter = ':';

        String colAName = "1";
        String colBName = "2";
        String colCName = "3";

        String[] header = new String[] {colAName, colBName, colCName};

        String[] line1 = new String[] {"0", "1", "2"};
        String[] line2 = new String[] {"3", "4", "5"};
        String[] line3 = new String[] {"6", "7", "8"};
        String[] line4 = new String[] {"9", "10", "11"};

        List<String[]> dataList = Arrays.asList(header, line1, line2, line3, line4);

        Path testFilePath = Paths.get(testFileName);

        try {
            CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(testFilePath.toString()))
                    .withSeparator(delimiter)
                    .build();
            writer.writeAll(dataList);
            writer.close();

            CSVFileReader reader = new CSVFileReader(testFileName, dataObjectFactory, delimiter);

            List<DataObject> entries = reader.getEntries();

            testDataListEquality(line1, line2, line3, line4, entries);

            File testFile = new File(testFileName);
            testFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * compares original data values to values, which are written to file and then read in by the CSVFileReader
     * while using non-standard delimiter and no header
     */
    @Test
    void parseCSVDifferentDelimiterNoHeader() {
        String testFileName = "src/test/resources/testCSVDifferentDelimiterNoHeader.csv";

        char delimiter = ':';

        String[] line1 = new String[] {"0", "1", "2"};
        String[] line2 = new String[] {"3", "4", "5"};
        String[] line3 = new String[] {"6", "7", "8"};
        String[] line4 = new String[] {"9", "10", "11"};

        List<String[]> dataList = Arrays.asList(line1, line2, line3, line4);

        Path testFilePath = Paths.get(testFileName);

        try {
            CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(testFilePath.toString()))
                    .withSeparator(delimiter)
                    .build();
            writer.writeAll(dataList);
            writer.close();

            CSVFileReader reader = new CSVFileReader(testFileName, dataObjectFactory, delimiter, false);

            List<DataObject> entries = reader.getEntries();

            testDataListEquality(line1, line2, line3, line4, entries);

            File testFile = new File(testFileName);
            testFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * compares the original values of data to the values, which were written to file and then read in by the CSVFileReader
     *
     * @param line1 first line of original data
     * @param line2 second line of original data
     * @param line3 third line of original data
     * @param line4 fourth line of original data
     * @param entries list of transformed data created from read csv file
     */
    private void testDataListEquality(String[] line1, String[] line2, String[] line3, String[] line4, List<DataObject> entries){

        assertEquals(line1[0], entries.get(0).getLabel(), "Read CSV value line1, col A not correct");
        assertEquals(line1[1], Integer.toString(((DataObjectStandard) entries.get(0)).getCompareValueOne()), "Read CSV value line1, col B not correct");
        assertEquals(line1[2], Integer.toString(((DataObjectStandard) entries.get(0)).getCompareValueTwo()), "Read CSV value line1, col C not correct");

        assertEquals(line2[0], entries.get(1).getLabel(), "Read CSV value line2, col A not correct");
        assertEquals(line2[1], Integer.toString(((DataObjectStandard) entries.get(1)).getCompareValueOne()), "Read CSV value line2, col B not correct");
        assertEquals(line2[2], Integer.toString(((DataObjectStandard) entries.get(1)).getCompareValueTwo()), "Read CSV value line2, col C not correct");

        assertEquals(line3[0], entries.get(2).getLabel(), "Read CSV value line3, col A not correct");
        assertEquals(line3[1], Integer.toString(((DataObjectStandard) entries.get(2)).getCompareValueOne()), "Read CSV value line3, col B not correct");
        assertEquals(line3[2], Integer.toString(((DataObjectStandard) entries.get(2)).getCompareValueTwo()), "Read CSV value line3, col C not correct");

        assertEquals(line4[0], entries.get(3).getLabel(), "Read CSV value line4, col A not correct");
        assertEquals(line4[1], Integer.toString(((DataObjectStandard) entries.get(3)).getCompareValueOne()), "Read CSV value line4, col B not correct");
        assertEquals(line4[2], Integer.toString(((DataObjectStandard) entries.get(3)).getCompareValueTwo()), "Read CSV value line4, col C not correct");

    }
}
