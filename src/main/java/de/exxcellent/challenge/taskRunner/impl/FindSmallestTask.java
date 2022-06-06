package de.exxcellent.challenge.taskRunner.impl;

import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.dataObjects.DataObjectValueDifference;
import de.exxcellent.challenge.taskRunner.TaskRunner;

import java.util.List;

/**
 * this class provides a TaskRunner implementation for finding the object with the smallest difference
 * in a list of DataObjectValueDifference objects provided as List<DataObject>
 */
public class FindSmallestTask implements TaskRunner {
    private List<DataObject> objectList;

    /**
     * constructor
     *
     * @param objectList the list of objects, for which to find the smallest difference should be found
     */
    public FindSmallestTask(List<DataObject> objectList){
        this.objectList = objectList;
        findResult();
    }

    /**
     * from a list of DataObjectValueDifference objects,
     * finds the object with the smallest value difference and returns it
     *
     * @return DataObjectValueDifference object with the smallest difference
     */
    @Override
    public DataObject findResult() {
        DataObjectValueDifference smallestDifferenceObject = (DataObjectValueDifference) objectList.get(0);

        for(int i = 1; i < objectList.size(); i++){
            DataObjectValueDifference currentObject = (DataObjectValueDifference) objectList.get(i);
            if(currentObject.getDifference() < smallestDifferenceObject.getDifference()){
                smallestDifferenceObject = currentObject;
            }
        }

        return smallestDifferenceObject;
    }
}
