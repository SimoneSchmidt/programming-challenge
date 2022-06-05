package de.exxcellent.challenge;

import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.dataObjects.DataObjectFactory;
import de.exxcellent.challenge.dataObjects.DataObjectValueDifference;
import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.fileReader.impl.CSVFileReader;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the DataObject classes
 */
public class DataObjectTest {

    String filenameWeather = DefaultValues.filePathWeather;
    String filenameFootball = DefaultValues.filePathFootball;

    /**
     * test values from data object against actual values from and calculated from the weather csv file
     */
    @Test
    void testDataObjectWeatherCSV() {
        DataObjectFactory dataObjectFactory = new DataObjectFactory(DataObjectFactory.DataObjectType.WEATHER_DATA);
        CSVFileReader fileReader = new CSVFileReader(filenameWeather, dataObjectFactory);

        List<DataObject> entry = fileReader.getEntries();

        testDataObjectEqualityGivenDifference("1", 29,
                (DataObjectValueDifference) entry.get(0), "Check weather csv values 1");
        testDataObjectEqualityGivenDifference("2", 16,
                (DataObjectValueDifference) entry.get(1), "Check weather csv values 2");
        testDataObjectEqualityGivenDifference("3", 22,
                (DataObjectValueDifference) entry.get(2), "Check weather csv values 3");
        testDataObjectEqualityGivenDifference("30", 45,
                (DataObjectValueDifference) entry.get(entry.size() - 1), "Check weather csv values last");
    }

    /**
     * test values from data object against randomly generated values for weather object
     */
    @Test
    void testDataObjectWeatherSingleValues() {
        DataObjectFactory dataObjectFactory = new DataObjectFactory(DataObjectFactory.DataObjectType.WEATHER_DATA);
        Map<String, String> csvEntry1 = new HashMap<>();
        Map<String, String> csvEntry2 = new HashMap<>();
        Map<String, String> csvEntry3 = new HashMap<>();
        List<String> columnNames = Arrays.asList("Day","MxT","MnT","AvT","AvDP","1HrP TPcpn","PDir");

        String day1 = "";
        String day2 = "";
        String day3 = "";
        int val1_1 = 0;
        int val1_2 = 0;
        int val2_1 = 0;
        int val2_2 = 0;
        int val3_1 = 0;
        int val3_2 = 0;

        for(String name: columnNames) {
            int val = (int) ((Math.random() * 50) - 20);
            csvEntry1.put(name, Integer.toString(val));
            if(name.equals("Day")){
                day1 = Integer.toString(val);
            }
            if(name.equals("MxT")){
                val1_1 = val;
            }
            if(name.equals("MnT")){
                val1_2 = val;
            }
        }

        for(String name: columnNames) {
            int val = (int) ((Math.random() * 50) - 20);
            csvEntry2.put(name, Integer.toString(val));
            if(name.equals("Day")){
                day2 = Integer.toString(val);
            }
            if(name.equals("MxT")){
                val2_1 = val;
            }
            if(name.equals("MnT")){
                val2_2 = val;
            }
        }

        for(String name: columnNames) {
            int val = (int) ((Math.random() * 50) - 20);
            csvEntry3.put(name, Integer.toString(val));
            if(name.equals("Day")){
                day3 = Integer.toString(val);
            }
            if(name.equals("MxT")){
                val3_1 = val;
            }
            if(name.equals("MnT")){
                val3_2 = val;
            }
        }

        DataObjectValueDifference object1 = dataObjectFactory.createValueDifferenceObject(csvEntry1);
        DataObjectValueDifference object2 = dataObjectFactory.createValueDifferenceObject(csvEntry2);
        DataObjectValueDifference object3 = dataObjectFactory.createValueDifferenceObject(csvEntry3);

        testDataObjectEqualityPerformCalculation(day1, val1_1, val1_2, false,
                object1, "Random weather object1");
        testDataObjectEqualityPerformCalculation(day2, val2_1, val2_2, false,
                object2, "Random weather object2");
        testDataObjectEqualityPerformCalculation(day3, val3_1, val3_2, false,
                object3, "Random weather object3");
    }

    /**
     * test values from data object against actual values from and calculated from the football csv file
     */
    @Test
    void testDataObjectFootballCSV() {
        DataObjectFactory dataObjectFactory = new DataObjectFactory(DataObjectFactory.DataObjectType.FOOTBALL_DATA);
        CSVFileReader fileReader = new CSVFileReader(filenameFootball, dataObjectFactory);

        List<DataObject> entry = fileReader.getEntries();

        testDataObjectEqualityGivenDifference("Arsenal", 43,
                (DataObjectValueDifference) entry.get(0), "Check football csv values 1");
        testDataObjectEqualityGivenDifference("Liverpool", 37,
                (DataObjectValueDifference) entry.get(1), "Check football csv values 2");
        testDataObjectEqualityGivenDifference("Manchester United", 42,
                (DataObjectValueDifference) entry.get(2), "Check football csv values 3");
        testDataObjectEqualityGivenDifference("Leicester", 34,
                (DataObjectValueDifference) entry.get(entry.size() - 1), "Check football csv values last");
    }

    /**
     * test values from data object against randomly generated values for football object
     */
    @Test
    void testDataObjectFootballSingleValues() {
        DataObjectFactory dataObjectFactory = new DataObjectFactory(DataObjectFactory.DataObjectType.FOOTBALL_DATA);
        Map<String, String> csvEntry1 = new HashMap<>();
        Map<String, String> csvEntry2 = new HashMap<>();
        Map<String, String> csvEntry3 = new HashMap<>();
        List<String> columnNames = Arrays.asList("Team","Games","Wins","Losses","Draws","Goals","Goals Allowed");

        String team1 = "";
        String team2 = "";
        String team3 = "";
        int val1_1 = 0;
        int val1_2 = 0;
        int val2_1 = 0;
        int val2_2 = 0;
        int val3_1 = 0;
        int val3_2 = 0;

        for(String name: columnNames) {
            int val = (int) ((Math.random() * 50) - 20);
            csvEntry1.put(name, Integer.toString(val));
            if(name.equals("Team")){
                team1 = Integer.toString(val);
            }
            if(name.equals("Goals")){
                val1_1 = val;
            }
            if(name.equals("Goals Allowed")){
                val1_2 = val;
            }
        }

        for(String name: columnNames) {
            int val = (int) ((Math.random() * 50) - 20);
            csvEntry2.put(name, Integer.toString(val));
            if(name.equals("Team")){
                team2 = Integer.toString(val);
            }
            if(name.equals("Goals")){
                val2_1 = val;
            }
            if(name.equals("Goals Allowed")){
                val2_2 = val;
            }
        }

        for(String name: columnNames) {
            int val = (int) ((Math.random() * 50) - 20);
            csvEntry3.put(name, Integer.toString(val));
            if(name.equals("Team")){
                team3 = Integer.toString(val);
            }
            if(name.equals("Goals")){
                val3_1 = val;
            }
            if(name.equals("Goals Allowed")){
                val3_2 = val;
            }
        }

        DataObjectValueDifference object1 = dataObjectFactory.createValueDifferenceObject(csvEntry1);
        DataObjectValueDifference object2 = dataObjectFactory.createValueDifferenceObject(csvEntry2);
        DataObjectValueDifference object3 = dataObjectFactory.createValueDifferenceObject(csvEntry3);

        testDataObjectEqualityPerformCalculation(team1, val1_1, val1_2, true,
                object1, "Random football object1");
        testDataObjectEqualityPerformCalculation(team2, val2_1, val2_2, true,
                object2, "Random football object2");
        testDataObjectEqualityPerformCalculation(team3, val3_1, val3_2, true,
                object3, "Random football object3");
    }

    /**
     * calculates the expected difference by calculating the difference between val1 and val2,
     * then compares the values from the object against expected label and difference values
     *
     * @param label expected label
     * @param val1 first value for difference
     * @param val2 second value for difference
     * @param absoluteDifference whether the calculated difference should be absolute
     * @param object object with which to compare
     * @param messagePrefix prefix for fail message
     */
    private void testDataObjectEqualityPerformCalculation(String label, int val1, int val2, boolean absoluteDifference,
                                        DataObjectValueDifference object, String messagePrefix){
        int difference = val1 - val2;

        if(absoluteDifference){
            difference = Math.abs(difference);
        }

        assertEquals(label, object.getLabel(), messagePrefix + ": Wrong label");
        assertEquals(difference, object.getDifference(), messagePrefix + ": Wrong calculation result");
    }

    /**
     * compares the values from the object against expected label and difference values
     *
     * @param label expected label
     * @param difference expected difference
     * @param object object with which to compare
     * @param messagePrefix prefix for fail message
     */
    private void testDataObjectEqualityGivenDifference(String label, int difference,
                                                       DataObjectValueDifference object, String messagePrefix){
        assertEquals(label, object.getLabel(), messagePrefix + ": Wrong label");
        assertEquals(difference, object.getDifference(), messagePrefix + ": Wrong calculation result");
    }

}
