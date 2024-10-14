package com.example.sesion5.model;

/**
 * This interface defines methods for validating and processing word inputs.
 * It provides functionality to check if the input is correct, full, and of the correct length.
 * @author Libardo Alejandro Quintero Gómez
 * @version 1.0
 */
public interface IWord {

    /**
     * Checks if the provided word input is correct.
     *
     * @param wordInput the word input to validate
     * @return true if the input is correct, false otherwise
     */
    boolean isInputCorrect(String wordInput);

    /**
     * Checks if the provided word input is full.
     *
     * @param wordInput the word input to check
     * @return true if the input is full, false otherwise
     */
    boolean isInputFull(String wordInput);

    /**
     * Checks if the length of the provided word input is correct.
     *
     * @param wordInput the word input to validate
     * @return true if the length is correct, false otherwise
     */
    boolean isLengthCorrect(String wordInput);

    /**
     * The type of this word interface.
     */
    String TYPE = "Word";

    /**
     * Cleans the provided word input by trimming whitespace, removing tabs,
     * and converting it to uppercase.
     *
     * @param wordInput the word input to clean
     * @return the cleaned word input
     */
    default String cleanStringInput(String wordInput) {
        wordInput = wordInput.trim();
        wordInput = wordInput.replaceAll("\\s+", "");
        wordInput = wordInput.replaceAll("\\t+", "");
        wordInput = wordInput.toUpperCase();
        return wordInput;
    }
}
