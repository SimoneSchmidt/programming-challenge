package de.exxcellent.challenge.dataObjects;

/**
 * interface for data objects, on which value differences should be calculated
 */
public interface DataObjectValueDifference extends DataObject {

    /**
     * returns the difference between the comparison values of the DataObjectValueDifference object
     *
     * @return difference between the comparison values
     */
    int getDifference();
}
