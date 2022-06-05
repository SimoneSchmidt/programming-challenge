package de.exxcellent.challenge.dataObjects.impl;

import de.exxcellent.challenge.dataObjects.DataObjectValueDifference;

/**
 * class for football data objects, on which absolute difference between 
 * the number of scored goals and the number of allowed goals should be calculated
 */
public class DataObjectFootball implements DataObjectValueDifference {
    private final String teamName;
    private final int nrGoals;
    private final int nrGoalsAllowed;

    /**
     * constructor
     *
     * @param teamName the team name
     * @param nrGoals the number of scored goals
     * @param nrGoalsAllowed the number of received goals scored by opponents
     */
    public DataObjectFootball(String teamName, int nrGoals, int nrGoalsAllowed){
        this.teamName = teamName;
        this.nrGoals = nrGoals;
        this.nrGoalsAllowed = nrGoalsAllowed;
    }

    /**
     * returns the team name, which is the label of the DataObjectFootball object
     *
     * @return the team name
     */
    @Override
    public String getLabel() {
        return teamName;
    }

    /**
     * returns the absolute difference between the number of scored goals
     * and the number of allowed goals of the DataObjectFootball object
     *
     * @return difference between the comparison values
     */
    @Override
    public int getDifference() {
        int difference = nrGoals - nrGoalsAllowed;

        return Math.abs(difference);
    }
}
