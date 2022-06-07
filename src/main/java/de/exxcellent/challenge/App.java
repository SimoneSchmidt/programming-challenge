package de.exxcellent.challenge;

import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.exceptions.IncompatibleDataException;
import de.exxcellent.challenge.exceptions.IncorrectFileTypeException;
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
     * prints a message describing appropriate options for input
     */
    private static void printWrongInputMessage(){
        System.out.println("Unknown option, allowed are either '--weather' or '--football' " +
                "followed by the name of the csv file containing the appropriate data.");
    }

    /**
     * gets and prints the result for a task within a message
     *
     * @param task the task to be run to get the result
     * @param message the message, in which the result should be included
     */
    private static void printResult(TaskRunner task, String message){
        if(task != null){
            DataObject result = task.findResult();
            if(result != null){
                String labelOfSmallestSpread = result.getLabel();     // Your day analysis function call …
                System.out.printf(message, labelOfSmallestSpread);
            }
        }
    }

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        // Your preparation code …

        String weather = DefaultValues.weatherCommand;
        String football = DefaultValues.footballCommand;

        String weatherMessage = DefaultValues.weatherMessage;
        String footballMessage = DefaultValues.footballMessage;

        TaskRunner weatherTask = null;
        TaskRunner footballTask = null;

        try {
            // handle input
            if(args.length == 0) {
                // prepare both task runners with default values
                FindSmallestTaskFactory taskFactory = new FindSmallestTaskFactory(DefaultValues.DataObjectType.WEATHER_DATA);
                weatherTask = taskFactory.createTask();
                taskFactory.setDataObjectType(DefaultValues.DataObjectType.FOOTBALL_DATA);
                footballTask = taskFactory.createTask();
            } else {
                if(args.length == 2) {
                    if(args[0].equals(weather)){
                        // prepare weather task runner with given file name
                        String filename = filePathPrefix + args[1];
                        FindSmallestTaskFactory taskFactory =
                                new FindSmallestTaskFactory(DefaultValues.DataObjectType.WEATHER_DATA, filename);
                        weatherTask = taskFactory.createTask();
                    } else if(args[0].equals(football)) {
                        // prepare football task runner with given file name
                        String filename = filePathPrefix + args[1];
                        FindSmallestTaskFactory taskFactory =
                                new FindSmallestTaskFactory(DefaultValues.DataObjectType.FOOTBALL_DATA, filename);
                        footballTask = taskFactory.createTask();
                    } else {
                        printWrongInputMessage();
                    }
                } else if(args.length == 4 && ((args[0].equals(weather) && args[2].equals(football))
                        || (args[2].equals(weather) && args[0].equals(football)))) {

                    if (args[0].equals(weather)) {
                        // prepare both task runners with given file names
                        String filename = filePathPrefix + args[1];
                        FindSmallestTaskFactory taskFactory =
                                new FindSmallestTaskFactory(DefaultValues.DataObjectType.WEATHER_DATA, filename);
                        weatherTask = taskFactory.createTask();

                        filename = filePathPrefix + args[3];
                        taskFactory.setDataObjectType(DefaultValues.DataObjectType.FOOTBALL_DATA, filename);
                        footballTask = taskFactory.createTask();
                    }
                    if (args[0].equals(football)) {
                        // prepare both task runners with given file names
                        String filename = filePathPrefix + args[1];
                        FindSmallestTaskFactory taskFactory =
                                new FindSmallestTaskFactory(DefaultValues.DataObjectType.FOOTBALL_DATA, filename);
                        footballTask = taskFactory.createTask();

                        filename = filePathPrefix + args[3];
                        taskFactory.setDataObjectType(DefaultValues.DataObjectType.WEATHER_DATA, filename);
                        weatherTask = taskFactory.createTask();
                    }
                } else {
                    printWrongInputMessage();
                }
            }
        } catch (IncompatibleDataException | IncorrectFileTypeException e) {
            e.printStackTrace();
        }

        // get and print results
        printResult(weatherTask, weatherMessage);
        printResult(footballTask, footballMessage);
    }
}
