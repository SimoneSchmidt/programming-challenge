package de.exxcellent.challenge.exceptions;

/**
 * this class provides a custom exception type for cases, when provided data
 * does not conform to expectations and is therefore unusable
 */
public class IncompatibleDataException extends Exception {

    /**
     * constructor
     *
     * @param errorMessage the error message of the exception
     */
    public IncompatibleDataException(String errorMessage) {
        super(errorMessage);
    }
}
