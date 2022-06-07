package de.exxcellent.challenge.dataObjects;

import de.exxcellent.challenge.dataObjects.impl.DataObjectFootball;
import de.exxcellent.challenge.dataObjects.impl.DataObjectStandard;
import de.exxcellent.challenge.dataObjects.impl.DataObjectWeather;
import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.exceptions.IncompatibleDataException;

import java.util.Map;

/**
 * factory for creating DataObject objects
 */
public class DataObjectFactory {

    private DefaultValues.DataObjectType dataObjectType;

    /**
     * constructor
     */
    public DataObjectFactory() {
        this.dataObjectType = DefaultValues.DataObjectType.STANDARD;
    }

    /**
     * constructor
     *
     * @param dataObjectType the dataObjectType, which determines the DataObject implementation when creating a DataObject
     */
    public DataObjectFactory(DefaultValues.DataObjectType dataObjectType) {
        this.dataObjectType = dataObjectType;
    }

    /**
     * changes the dataObjectType, which determines,
     * which implementation of DataObject is chosen, when creating a DataObject
     *
     * @param dataObjectType the dataObjectType for the factory
     */
    public void setDataObjectType(DefaultValues.DataObjectType dataObjectType) {
        this.dataObjectType = dataObjectType;
    }

    /**
     * checks, whether the data contains the expected columns
     *
     * @param dataEntry the data to be checked
     * @param labelColumnName name of label column
     * @param columnOneName name of first value column
     * @param columnTwoName name of second value column
     * @return true, if all columns exist, false otherwise
     */
    private boolean checkIsDataCompatible(Map<String, String> dataEntry, String labelColumnName,
                                           String columnOneName, String columnTwoName){
        return dataEntry.containsKey(labelColumnName) && dataEntry.containsKey(columnOneName)
                && dataEntry.containsKey(columnTwoName);
    }

    /**
     * throws a IncompatibleDataException exception
     *
     * @throws IncompatibleDataException
     */
    private void throwIncompatibilityException() throws IncompatibleDataException {
        throw new IncompatibleDataException("Could not create DataObjectValueDifference object, " +
                "data does not contain expected columns!");
    }

    /**
     * creates a DataObjectValueDifference with values from dataEntry
     * the dataObjectType defines, which implementation of DataObjectValueDifference should be used
     *
     * @param dataEntry contains values for creating the DataObject
     * @throws IncompatibleDataException
     */
    public DataObjectValueDifference createValueDifferenceObject(Map<String, String> dataEntry) throws IncompatibleDataException {
        DataObjectValueDifference dataObject = null;

        // set standard column names for data
        String labelColumn = DefaultValues.labelColumnNameStandard;
        String compareValueOneColumn = DefaultValues.compareValueOneColumnNameStandard;
        String compareValueTwoColumn = DefaultValues.compareValueTwoColumnNameStandard;

        if(dataObjectType == DefaultValues.DataObjectType.WEATHER_DATA) {
            // set column names for weather data
            labelColumn = DefaultValues.labelColumnNameWeather;
            compareValueOneColumn = DefaultValues.compareValueOneColumnNameWeather;
            compareValueTwoColumn = DefaultValues.compareValueTwoColumnNameWeather;

            if(checkIsDataCompatible(dataEntry, labelColumn, compareValueOneColumn, compareValueTwoColumn)){
                // create weather data object with values from dataEntry
                dataObject = new DataObjectWeather(dataEntry.get(labelColumn),
                        Integer.parseInt(dataEntry.get(compareValueOneColumn)),
                        Integer.parseInt(dataEntry.get(compareValueTwoColumn)));
            } else {
                throwIncompatibilityException();
            }

        } else if(dataObjectType == DefaultValues.DataObjectType.FOOTBALL_DATA) {
            // set column names for football data
            labelColumn = DefaultValues.labelColumnNameFootball;
            compareValueOneColumn = DefaultValues.compareValueOneColumnNameFootball;
            compareValueTwoColumn = DefaultValues.compareValueTwoColumnNameFootball;

            if(checkIsDataCompatible(dataEntry, labelColumn, compareValueOneColumn, compareValueTwoColumn)) {
                // create football data object with values from dataEntry
                dataObject = new DataObjectFootball(dataEntry.get(labelColumn),
                        Integer.parseInt(dataEntry.get(compareValueOneColumn)),
                        Integer.parseInt(dataEntry.get(compareValueTwoColumn)));
            } else {
                throwIncompatibilityException();
            }

        } else if(dataObjectType == DefaultValues.DataObjectType.STANDARD) {

            if(checkIsDataCompatible(dataEntry, labelColumn, compareValueOneColumn, compareValueTwoColumn)) {
            // create standard data object with values from dataEntry
            dataObject = new DataObjectStandard(dataEntry.get(labelColumn),
                    Integer.parseInt(dataEntry.get(compareValueOneColumn)),
                    Integer.parseInt(dataEntry.get(compareValueTwoColumn)));
            } else {
                throwIncompatibilityException();
            }
        }

        return dataObject;
    }
}
