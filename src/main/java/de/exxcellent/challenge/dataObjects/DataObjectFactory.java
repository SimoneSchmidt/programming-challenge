package de.exxcellent.challenge.dataObjects;

import de.exxcellent.challenge.dataObjects.impl.DataObjectFootball;
import de.exxcellent.challenge.dataObjects.impl.DataObjectStandard;
import de.exxcellent.challenge.dataObjects.impl.DataObjectWeather;
import de.exxcellent.challenge.defaults.DefaultValues;

import java.util.Map;

/**
 * factory for creating DataObject objects
 */
public class DataObjectFactory {

    private DataObjectType dataObjectType;

    public enum DataObjectType {
        WEATHER_DATA, FOOTBALL_DATA, STANDARD
    };

    /**
     * constructor
     */
    public DataObjectFactory() {
        this.dataObjectType = DataObjectType.STANDARD;
    }

    /**
     * constructor
     *
     * @param dataObjectType the dataObjectType, which determines the DataObject implementation when creating a DataObject
     */
    public DataObjectFactory(DataObjectType dataObjectType) {
        this.dataObjectType = dataObjectType;
    }

    /**
     * changes the dataObjectType, which determines,
     * which implementation of DataObject is chosen, when creating a DataObject
     *
     * @param dataObjectType the dataObjectType for the factory
     */
    public void setDataObjectType(DataObjectType dataObjectType) {
        this.dataObjectType = dataObjectType;
    }

    /**
     * creates a DataObjectValueDifference with values from dataEntry
     * the
     *
     * @param dataEntry contains values for creating the DataObject
     */
    public DataObjectValueDifference createValueDifferenceObject(Map<String, String> dataEntry) {
        DataObjectValueDifference dataObject = null;

        // set standard column names for data
        String labelColumn = DefaultValues.labelColumnNameStandard;
        String compareValueOneColumn = DefaultValues.compareValueOneColumnNameStandard;
        String compareValueTwoColumn = DefaultValues.compareValueTwoColumnNameStandard;

        if(dataObjectType == DataObjectType.WEATHER_DATA) {
            // set column names for weather data
            labelColumn = DefaultValues.labelColumnNameWeather;
            compareValueOneColumn = DefaultValues.compareValueOneColumnNameWeather;
            compareValueTwoColumn = DefaultValues.compareValueTwoColumnNameWeather;

            // create weather data object with values from dataEntry
            dataObject = new DataObjectWeather(dataEntry.get(labelColumn),
                    Integer.parseInt(dataEntry.get(compareValueOneColumn)),
                    Integer.parseInt(dataEntry.get(compareValueTwoColumn)));

        } else if(dataObjectType == DataObjectType.FOOTBALL_DATA) {
            // set column names for football data
            labelColumn = DefaultValues.labelColumnNameFootball;
            compareValueOneColumn = DefaultValues.compareValueOneColumnNameFootball;
            compareValueTwoColumn = DefaultValues.compareValueTwoColumnNameFootball;

            // create football data object with values from dataEntry
            dataObject = new DataObjectFootball(dataEntry.get(labelColumn),
                    Integer.parseInt(dataEntry.get(compareValueOneColumn)),
                    Integer.parseInt(dataEntry.get(compareValueTwoColumn)));

        } else if(dataObjectType == DataObjectType.STANDARD) {
            // create standard data object with values from dataEntry
            dataObject = new DataObjectStandard(dataEntry.get(labelColumn),
                    Integer.parseInt(dataEntry.get(compareValueOneColumn)),
                    Integer.parseInt(dataEntry.get(compareValueTwoColumn)));
        }

        return dataObject;
    }
}
