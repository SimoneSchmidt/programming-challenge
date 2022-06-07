package de.exxcellent.challenge.taskRunner.impl;

import de.exxcellent.challenge.dataObjects.DataObjectFactory;
import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.exceptions.IncompatibleDataException;
import de.exxcellent.challenge.exceptions.IncorrectFileTypeException;
import de.exxcellent.challenge.fileReader.DataFileReader;
import de.exxcellent.challenge.fileReader.impl.CSVFileReader;
import de.exxcellent.challenge.taskRunner.TaskRunner;
import de.exxcellent.challenge.taskRunner.TaskRunnerFactory;

/**
 * factory for creating FindSmallestTask task objects
 */
public class FindSmallestTaskFactory implements TaskRunnerFactory {

    DataFileReader fileReader;

    /**
     * constructor
     *
     * @param dataObjectType the data object type, for which the FindSmallestTask should be created
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    public FindSmallestTaskFactory(DefaultValues.DataObjectType dataObjectType)
            throws IncompatibleDataException, IncorrectFileTypeException {
        setDataObjectType(dataObjectType);
    }

    /**
     * constructor
     *
     * @param dataObjectType the data object type, for which the FindSmallestTask should be created
     * @param dataFilePath the path to the data file, which should be used
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    public FindSmallestTaskFactory(DefaultValues.DataObjectType dataObjectType, String dataFilePath)
            throws IncompatibleDataException, IncorrectFileTypeException {
        setDataObjectType(dataObjectType, dataFilePath);
    }

    /**
     * constructor
     *
     * @param fileReader the appropriate file reader for the data with which the FindSmallestTask should be created
     */
    public FindSmallestTaskFactory(DataFileReader fileReader){
        this.fileReader = fileReader;
    }

    /**
     * changes the data object type, for which the FindSmallestTask should be created
     *
     * @param dataObjectType the data object type
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    public void setDataObjectType(DefaultValues.DataObjectType dataObjectType)
            throws IncompatibleDataException, IncorrectFileTypeException {
        String filename = "";

        if(dataObjectType == DefaultValues.DataObjectType.WEATHER_DATA) {
            filename = DefaultValues.filePathWeather;
        } else if(dataObjectType == DefaultValues.DataObjectType.FOOTBALL_DATA) {
            filename = DefaultValues.filePathFootball;
        }

        DataObjectFactory dataObjectFactory = new DataObjectFactory(dataObjectType);
        fileReader = new CSVFileReader(filename, dataObjectFactory);
    }

    /**
     * changes the data object type, for which the FindSmallestTask should be created
     *
     * @param dataObjectType the data object type
     * @param dataFilePath the path to the data file, which should be used
     * @throws IncorrectFileTypeException
     * @throws IncompatibleDataException
     */
    public void setDataObjectType(DefaultValues.DataObjectType dataObjectType, String dataFilePath)
            throws IncompatibleDataException, IncorrectFileTypeException {
        DataObjectFactory dataObjectFactory = new DataObjectFactory(dataObjectType);
        fileReader = new CSVFileReader(dataFilePath, dataObjectFactory);
    }

    /**
     * creates and returns a FindSmallestTask task object with the defined properties
     *
     * @return the FindSmallestTask task object
     */
    public TaskRunner createTask(){
        return new FindSmallestTask(fileReader.getEntries());
    }
}
