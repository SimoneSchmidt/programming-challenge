package de.exxcellent.challenge.exceptions;

/**
 * this class provides a custom exception type for cases, when provided
 * data files are of incorrect type and/or cannot be parsed
 */
public class IncorrectFileTypeException extends Exception {

    /**
     * constructor
     *
     * @param errorMessage the error message of the exception
     */
    public IncorrectFileTypeException(String errorMessage) {
        super(errorMessage);
    }
}
