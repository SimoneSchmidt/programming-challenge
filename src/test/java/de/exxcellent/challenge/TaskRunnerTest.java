package de.exxcellent.challenge;

import de.exxcellent.challenge.dataObjects.DataObject;
import de.exxcellent.challenge.dataObjects.DataObjectFactory;
import de.exxcellent.challenge.defaults.DefaultValues;
import de.exxcellent.challenge.taskRunner.TaskRunner;
import de.exxcellent.challenge.taskRunner.impl.FindSmallestTask;
import de.exxcellent.challenge.taskRunner.impl.FindSmallestTaskFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the TaskRunner class FindSmallestTask
 */
public class TaskRunnerTest {

    /**
     * test for weather objects from the weather csv file, whether right day is found
     */
    @Test
    void testFindDayCSV(){
        FindSmallestTaskFactory taskFactory = new FindSmallestTaskFactory(DefaultValues.DataObjectType.WEATHER_DATA);
        TaskRunner weatherTask = taskFactory.createTask();

        String dayWithSmallestTempSpread = weatherTask.findResult().getLabel();

        assertEquals("14", dayWithSmallestTempSpread, "Wrong smallest difference weather csv");
    }

    /**
     * test for few weather objects, whether right day is found
     */
    @Test
    void testFindDaySingleValues(){
        DataObjectFactory dataObjectFactory = new DataObjectFactory(DefaultValues.DataObjectType.WEATHER_DATA);

        List<DataObject> testList1 = new ArrayList<>();
        List<DataObject> testList2 = new ArrayList<>();

        Map<String, String> values = new HashMap<>();
        values.put(DefaultValues.labelColumnNameWeather, "1");
        values.put(DefaultValues.compareValueOneColumnNameWeather, "10");
        values.put(DefaultValues.compareValueTwoColumnNameWeather, "10");
        testList1.add(dataObjectFactory.createValueDifferenceObject(values));
        values.put(DefaultValues.compareValueOneColumnNameWeather, "30");
        values.put(DefaultValues.compareValueTwoColumnNameWeather, "10");
        testList2.add(dataObjectFactory.createValueDifferenceObject(values));

        values.put(DefaultValues.labelColumnNameWeather, "2");
        values.put(DefaultValues.compareValueOneColumnNameWeather, "10");
        values.put(DefaultValues.compareValueTwoColumnNameWeather, "0");
        testList1.add(dataObjectFactory.createValueDifferenceObject(values));
        values.put(DefaultValues.compareValueOneColumnNameWeather, "30");
        values.put(DefaultValues.compareValueTwoColumnNameWeather, "0");
        testList2.add(dataObjectFactory.createValueDifferenceObject(values));

        values.put(DefaultValues.labelColumnNameWeather, "3");
        values.put(DefaultValues.compareValueOneColumnNameWeather, "10");
        values.put(DefaultValues.compareValueTwoColumnNameWeather, "2");
        testList1.add(dataObjectFactory.createValueDifferenceObject(values));
        testList2.add(dataObjectFactory.createValueDifferenceObject(values));

        values.put(DefaultValues.labelColumnNameWeather, "4");
        values.put(DefaultValues.compareValueOneColumnNameWeather, "10");
        values.put(DefaultValues.compareValueTwoColumnNameWeather, "5");
        testList1.add(dataObjectFactory.createValueDifferenceObject(values));
        testList2.add(dataObjectFactory.createValueDifferenceObject(values));

        FindSmallestTask task1 = new FindSmallestTask(testList1);
        FindSmallestTask task2 = new FindSmallestTask(testList2);

        assertEquals("1", task1.findResult().getLabel(), "Wrong smallest difference weather single values 1");
        assertEquals("4", task2.findResult().getLabel(), "Wrong smallest difference weather single values 2");
    }

    /**
     * test for football objects from the football csv file, whether right team name is found
     */
    @Test
    void testFindTeamCSV(){
        FindSmallestTaskFactory taskFactory = new FindSmallestTaskFactory(DefaultValues.DataObjectType.FOOTBALL_DATA);
        TaskRunner footballTask = taskFactory.createTask();

        String teamWithSmallestGoalSpread = footballTask.findResult().getLabel();

        assertEquals("Aston_Villa", teamWithSmallestGoalSpread, "Wrong smallest difference football csv");

    }

    /**
     * test for few football objects, whether right team name is found
     */
    @Test
    void testFindTeamSingleValues(){
        DataObjectFactory dataObjectFactory = new DataObjectFactory(DefaultValues.DataObjectType.FOOTBALL_DATA);

        List<DataObject> testList1 = new ArrayList<>();
        List<DataObject> testList2 = new ArrayList<>();
        List<DataObject> testList3 = new ArrayList<>();

        Map<String, String> values = new HashMap<>();
        values.put(DefaultValues.labelColumnNameFootball, "A");
        values.put(DefaultValues.compareValueOneColumnNameFootball, "10");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "10");
        testList1.add(dataObjectFactory.createValueDifferenceObject(values));
        values.put(DefaultValues.compareValueOneColumnNameFootball, "30");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "10");
        testList2.add(dataObjectFactory.createValueDifferenceObject(values));
        values.put(DefaultValues.compareValueOneColumnNameFootball, "10");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "30");
        testList3.add(dataObjectFactory.createValueDifferenceObject(values));

        values.put(DefaultValues.labelColumnNameFootball, "B");
        values.put(DefaultValues.compareValueOneColumnNameFootball, "10");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "0");
        testList1.add(dataObjectFactory.createValueDifferenceObject(values));
        values.put(DefaultValues.compareValueOneColumnNameFootball, "30");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "0");
        testList2.add(dataObjectFactory.createValueDifferenceObject(values));
        values.put(DefaultValues.compareValueOneColumnNameFootball, "30");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "0");
        testList3.add(dataObjectFactory.createValueDifferenceObject(values));

        values.put(DefaultValues.labelColumnNameFootball, "C");
        values.put(DefaultValues.compareValueOneColumnNameFootball, "10");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "2");
        testList1.add(dataObjectFactory.createValueDifferenceObject(values));
        testList2.add(dataObjectFactory.createValueDifferenceObject(values));
        values.put(DefaultValues.compareValueOneColumnNameFootball, "15");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "1");
        testList3.add(dataObjectFactory.createValueDifferenceObject(values));

        values.put(DefaultValues.labelColumnNameFootball, "D");
        values.put(DefaultValues.compareValueOneColumnNameFootball, "15");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "5");
        testList1.add(dataObjectFactory.createValueDifferenceObject(values));
        testList2.add(dataObjectFactory.createValueDifferenceObject(values));
        values.put(DefaultValues.compareValueOneColumnNameFootball, "10");
        values.put(DefaultValues.compareValueTwoColumnNameFootball, "11");
        testList3.add(dataObjectFactory.createValueDifferenceObject(values));

        FindSmallestTask task1 = new FindSmallestTask(testList1);
        FindSmallestTask task2 = new FindSmallestTask(testList2);
        FindSmallestTask task3 = new FindSmallestTask(testList3);

        assertEquals("A", task1.findResult().getLabel(), "Wrong smallest difference football single values 1");
        assertEquals("C", task2.findResult().getLabel(), "Wrong smallest difference football single values 2");
        assertEquals("D", task3.findResult().getLabel(), "Wrong smallest difference football single values 3");
    }
}
