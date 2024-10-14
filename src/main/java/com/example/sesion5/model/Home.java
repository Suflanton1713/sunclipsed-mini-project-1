package com.example.sesion5.model;

/**
 * The Home class extends the WordAdapter class and provides specific implementations
 * for input validation related to words.
 * @author Libardo Alejandro Quintero Gómez
 * @version 1.0
 */
public class Home extends WordAdapter {

    /**
     * Constructs a new Home instance.
     */
    public Home() {
    }

    /**
     * Checks if the provided word input is correct.
     * A valid input must not contain digits and must consist only of letters (including accented characters).
     *
     * @param wordInput the word input to validate
     * @return true if the input is correct, false otherwise
     */
    @Override
    public boolean isInputCorrect(String wordInput) {
        return (!(wordInput.matches(".*\\d.*")) && (wordInput.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]+$")));
    }

    /**
     * Checks if the provided word input is full.
     * In this implementation, a full input must have exactly 12 characters.
     *
     * @param wordInput the word input to check
     * @return true if the input is full, false otherwise
     */
    @Override
    public boolean isInputFull(String wordInput) {
        return wordInput.length() >= 12;
    }

    /**
     * Checks if the length of the provided word input is correct.
     * In this implementation, the input length must be at least 6 characters and 12 characters maximum.
     *
     * @param wordInput the word input to validate
     * @return true if the length is correct, false otherwise
     */
    @Override
    public boolean isLengthCorrect(String wordInput) {
        return wordInput.length() >= 6 && wordInput.length() <= 12;
    }
}
