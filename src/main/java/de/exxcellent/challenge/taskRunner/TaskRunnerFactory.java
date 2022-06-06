package de.exxcellent.challenge.taskRunner;

/**
 * interface for creating TaskRunner task objects
 */
public interface TaskRunnerFactory {

    /**
     * creates and returns a TaskRunner task object
     *
     * @return the task object
     */
    public TaskRunner createTask();
}
