package de.exxcellent.challenge.fileReader;

import de.exxcellent.challenge.dataObjects.DataObject;

import java.util.List;

/**
 * interface for data file readers
 */
public interface DataFileReader {

    /**
     * returns a list of DataObject objects, which was generated from the file which was read
     *
     * @return a list of DataObject objects, generated from the file
     */
    List<DataObject> getEntries();
}
