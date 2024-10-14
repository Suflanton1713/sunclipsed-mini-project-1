package com.example.sesion5.model;

/**
 * An abstract class that provides a partial implementation of the IWord interface.
 * It serves as a base for concrete implementations of word validation and processing.
 * @author Libardo Alejandro Quintero Gómez
 * @version 1.0
 */
public abstract class WordAdapter implements IWord {

    /**
     * Checks if the provided input is correct.
     * This default implementation always returns true.
     *
     * @param input the input to validate
     * @return true, indicating the input is correct
     */
    @Override
    public boolean isInputCorrect(String input) {
        return true;
    }

    /**
     * Checks if the provided input is full.
     * This default implementation always returns false.
     *
     * @param input the input to check
     * @return false, indicating the input is not full
     */
    @Override
    public boolean isInputFull(String input) {
        return false;
    }

    /**
     * Checks if the length of the provided word input is correct.
     * This default implementation always returns true.
     *
     * @param wordInput the word input to validate
     * @return true, indicating the length is correct
     */
    @Override
    public boolean isLengthCorrect(String wordInput) {
        return true;
    }
}
