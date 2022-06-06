package de.exxcellent.challenge.taskRunner;

import de.exxcellent.challenge.dataObjects.DataObject;

import java.util.List;

/**
 * interface for performing tasks
 */
public interface TaskRunner {

    /**
     * performs a task, which results in a DataObject object and returns it
     *
     * @return the resulting DataObject
     */
    DataObject findResult();
}
