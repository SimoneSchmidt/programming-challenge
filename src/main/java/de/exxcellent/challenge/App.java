package de.exxcellent.challenge;

import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.dataObjects.DataObjectFactory;
import de.exxcellent.challenge.fileReader.impl.CSVFileReader;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …
        String filename = DefaultValues.filePathWeather;

        DataObjectFactory dataObjectFactory = new DataObjectFactory(DataObjectFactory.DataObjectType.WEATHER_DATA);

        CSVFileReader fileReader = new CSVFileReader(filename, dataObjectFactory);

        for(DataObject entry: fileReader.getEntries()){
            // todo
        }

        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
