package de.exxcellent.challenge;

import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.dataObjects.DataObjectFactory;
import de.exxcellent.challenge.fileReader.impl.CSVFileReader;
import de.exxcellent.challenge.taskRunner.TaskRunner;
import de.exxcellent.challenge.taskRunner.TaskRunnerFactory;
import de.exxcellent.challenge.taskRunner.impl.FindSmallestTaskFactory;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …
        // prepare task runners
        FindSmallestTaskFactory taskFactory = new FindSmallestTaskFactory(DefaultValues.DataObjectType.WEATHER_DATA);
        TaskRunner weatherTask = taskFactory.createTask();
        taskFactory.setDataObjectType(DefaultValues.DataObjectType.FOOTBALL_DATA);
        TaskRunner footballTask = taskFactory.createTask();

        String dayWithSmallestTempSpread = weatherTask.findResult().getLabel();     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = footballTask.findResult().getLabel(); // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
