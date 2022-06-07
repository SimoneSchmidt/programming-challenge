package de.exxcellent.challenge.fileReader.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.dataObjects.DataObjectFactory;
import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.exceptions.IncompatibleDataException;
import de.exxcellent.challenge.exceptions.IncorrectFileTypeException;
import de.exxcellent.challenge.fileReader.DataFileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * this class provides a DataFileReader implementation for csv files
 */
public class CSVFileReader implements DataFileReader {

    private final String filename;
    private char delimiter = DefaultValues.csvDelimiter;
    private boolean hasHeader = DefaultValues.csvHasHeader;
    private final List<DataObject> entries = new ArrayList<>();

    /**
     * constructor
     *
     * @param filename the csv file, which should be read
     * @param dataObjectFactory the factory for transforming read data values into usable objects
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    public CSVFileReader(String filename, DataObjectFactory dataObjectFactory)
            throws IncompatibleDataException, IncorrectFileTypeException {
        this.filename = filename;

        readCSV(dataObjectFactory);
    }

    /**
     * constructor
     *
     * @param filename the csv file, which should be read
     * @param dataObjectFactory the factory for transforming read data values into usable objects
     * @param delimiter the delimiter on which to split the csv lines
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    public CSVFileReader(String filename, DataObjectFactory dataObjectFactory, char delimiter)
            throws IncompatibleDataException, IncorrectFileTypeException {
        this.filename = filename;
        this.delimiter = delimiter;

        readCSV(dataObjectFactory);
    }

    /**
     * constructor
     *
     * @param filename the csv file, which should be read
     * @param dataObjectFactory the factory for transforming read data values into usable objects
     * @param hasHeader indicates, whether the first line of csv file should be read as header or not
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    public CSVFileReader(String filename, DataObjectFactory dataObjectFactory, boolean hasHeader)
            throws IncompatibleDataException, IncorrectFileTypeException {
        this.filename = filename;
        this.hasHeader = hasHeader;

        readCSV(dataObjectFactory);
    }

    /**
     * constructor
     *
     * @param filename the csv file, which should be read
     * @param dataObjectFactory the factory for transforming read data values into usable objects
     * @param delimiter the delimiter on which to split the csv lines
     * @param hasHeader indicates, whether the first line of csv file should be read as header or not
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    public CSVFileReader(String filename, DataObjectFactory dataObjectFactory, char delimiter, boolean hasHeader)
            throws IncompatibleDataException, IncorrectFileTypeException {
        this.filename = filename;
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;

        readCSV(dataObjectFactory);
    }

    /**
     * returns a list of DataObject objects, which was generated from the csv file
     *
     * @return a list of DataObject objects, generated from the csv file
     */
    public List<DataObject> getEntries() {
        return entries;
    }

    /**
     * reads the csv file and transforms the data into a list of DataObject objects
     *
     * @param dataObjectFactory the factory for transforming read data values into usable objects
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    private void readCSV(DataObjectFactory dataObjectFactory)
            throws IncorrectFileTypeException, IncompatibleDataException {
        try {
            // check if file is csv file
            if(filename.split("\\.")[filename.split("\\.").length - 1].equals("csv")) {
                // prepare file reader
                BufferedReader fileReader = new BufferedReader(new FileReader(filename));
                CSVParser parser = new CSVParserBuilder()
                        .withSeparator(delimiter)
                        .withIgnoreQuotations(true)
                        .build();
                CSVReader csvReader = new CSVReaderBuilder(fileReader)
                        .withSkipLines(0)
                        .withCSVParser(parser)
                        .build();

                // read first line
                String[] fileLine = csvReader.readNext();

                // extract column names from (first) header line or give standard names
                List<String> columnNames;
                if(hasHeader) {
                    columnNames = Arrays.asList(fileLine);

                    // read next line, if first line is header
                    fileLine = csvReader.readNext();
                } else {
                    String[] columns = new String[fileLine.length];

                    for(int i = 0; i < fileLine.length; i++){
                        columns[i] = Integer.toString(i + 1);
                    }

                    columnNames = Arrays.asList(columns);
                }

                // read values from the file line by line
                do {
                    Map<String, String> csvEntry = new HashMap<>();
                    for(int i = 0; i < fileLine.length; i++) {
                        csvEntry.put(columnNames.get(i), fileLine[i]);
                    }

                    // convert values to DataObject using the dataObjectFactory
                    DataObject dataObject = dataObjectFactory.createValueDifferenceObject(csvEntry);

                    // add DataObject to list
                    entries.add(dataObject);
                } while((fileLine = csvReader.readNext()) != null);
            } else {
                throw new IncorrectFileTypeException("Not a csv file, cannot be parsed!");
            }
        } catch (IOException | CsvValidationException  e) {
            e.printStackTrace();
            throw new IncorrectFileTypeException("Provided file could not be parsed!");
        }
    }
}
