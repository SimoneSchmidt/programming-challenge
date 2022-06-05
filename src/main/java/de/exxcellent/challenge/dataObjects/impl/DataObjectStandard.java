package de.exxcellent.challenge.dataObjects.impl;

import de.exxcellent.challenge.dataObjects.DataObjectValueDifference;

/**
 * example class for testing purposes for data objects, on which value differences should be calculated
 */
public class DataObjectStandard implements DataObjectValueDifference {
    private final String label;
    private final int compareValueOne;
    private final int compareValueTwo;

    /**
     * constructor
     *
     * @param label the object label
     * @param compareValueOne the first value for comparison
     * @param compareValueTwo the second value for comparison
     */
    public DataObjectStandard(String label, int compareValueOne, int compareValueTwo){
        this.label = label;
        this.compareValueOne = compareValueOne;
        this.compareValueTwo = compareValueTwo;
    }

    /**
     * returns the label of the DataObjectStandard object
     *
     * @return the label of the object
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * returns the difference between the comparison values of the DataObjectStandard object
     *
     * @return difference between the comparison values
     */
    @Override
    public int getDifference() {
        return compareValueOne - compareValueTwo;
    }

    /**
     * returns the first value for comparison of the DataObjectStandard object
     *
     * @return first value for comparison
     */
    public int getCompareValueOne() {
        return compareValueOne;
    }

    /**
     * returns the second value for comparison of the DataObjectStandard object
     *
     * @return second value for comparison
     */
    public int getCompareValueTwo() {
        return compareValueTwo;
    }
}
