package de.exxcellent.challenge;

import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.taskRunner.TaskRunner;
import de.exxcellent.challenge.taskRunner.impl.FindSmallestTaskFactory;

import static de.exxcellent.challenge.defaults.DefaultValues.filePathPrefix;

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

        TaskRunner weatherTask = null;
        TaskRunner footballTask = null;

        // handle input
        if(args.length == 0) {
            // prepare task runners with default values
            FindSmallestTaskFactory taskFactory = new FindSmallestTaskFactory(DefaultValues.DataObjectType.WEATHER_DATA);
            weatherTask = taskFactory.createTask();
            taskFactory.setDataObjectType(DefaultValues.DataObjectType.FOOTBALL_DATA);
            footballTask = taskFactory.createTask();
        } else {
            if(args.length == 2 && (args[0].equals("--weather") || args[0].equals("--football"))) {
                if(args[0].equals("--weather")){
                    // prepare task runner with given file name
                    String filename = filePathPrefix + args[1];
                    FindSmallestTaskFactory taskFactory =
                            new FindSmallestTaskFactory(DefaultValues.DataObjectType.WEATHER_DATA, filename);
                    weatherTask = taskFactory.createTask();
                }
                if(args[0].equals("--football")){
                    // prepare task runner with given file name
                    String filename = filePathPrefix + args[1];
                    FindSmallestTaskFactory taskFactory =
                            new FindSmallestTaskFactory(DefaultValues.DataObjectType.FOOTBALL_DATA, filename);
                    footballTask = taskFactory.createTask();
                }
            } else if(args.length == 4 && ((args[0].equals("--weather") && args[2].equals("--football"))
                    || (args[2].equals("--weather") && args[0].equals("--football")))) {

                if (args[0].equals("--weather")) {
                    // prepare task runners with given file names
                    String filename = filePathPrefix + args[1];
                    FindSmallestTaskFactory taskFactory =
                            new FindSmallestTaskFactory(DefaultValues.DataObjectType.WEATHER_DATA, filename);
                    weatherTask = taskFactory.createTask();

                    filename = filePathPrefix + args[3];
                    taskFactory.setDataObjectType(DefaultValues.DataObjectType.FOOTBALL_DATA, filename);
                    footballTask = taskFactory.createTask();
                }
                if (args[0].equals("--football")) {
                    // prepare task runners with given file names
                    String filename = filePathPrefix + args[1];
                    FindSmallestTaskFactory taskFactory =
                            new FindSmallestTaskFactory(DefaultValues.DataObjectType.FOOTBALL_DATA, filename);
                    footballTask = taskFactory.createTask();

                    filename = filePathPrefix + args[3];
                    taskFactory.setDataObjectType(DefaultValues.DataObjectType.WEATHER_DATA, filename);
                    weatherTask = taskFactory.createTask();
                }
            }
        }

        if(weatherTask != null){
            DataObject result = weatherTask.findResult();
            if(result != null){
                String dayWithSmallestTempSpread = result.getLabel();     // Your day analysis function call …
                System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
            }
        }

        if(footballTask != null) {
            DataObject result = footballTask.findResult();
            if(result != null){
                String teamWithSmallestGoalSpread = result.getLabel();   // Your goal analysis function call …
                System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
            }
        }
    }
}
