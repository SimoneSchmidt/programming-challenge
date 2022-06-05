package de.exxcellent.challenge.dataObjects.impl;

import de.exxcellent.challenge.dataObjects.DataObjectValueDifference;

/**
 * class for weather data objects, on which difference between
 * the maximum and minimum temperature for a day should be calculated
 */
public class DataObjectWeather implements DataObjectValueDifference {
    private final String day;
    private final int maxTemp;
    private final int minTemp;

    /**
     * constructor
     *
     * @param day the day
     * @param maxTemp the number of scored goals
     * @param minTemp the number of received goals scored by opponents
     */
    public DataObjectWeather(String day, int maxTemp, int minTemp){
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    /**
     * returns the day, which is the label of the DataObjectWeather object
     *
     * @return the day
     */
    @Override
    public String getLabel() {
        return day;
    }

    /**
     * returns the difference between the maximum temperature
     * and the minimum temperature of the DataObjectWeather object
     *
     * @return difference between the comparison values
     */
    @Override
    public int getDifference() {
        int difference = maxTemp - minTemp;

        return difference;
    }
}
