package com.example.sesion5.model;

import java.util.Arrays;
import java.util.Random;

/**
 * The Game class extends WordAdapter and manages the gameplay for a word guessing game.
 * It handles the secret word, player attempts, and hints.
 * @author Libardo Alejandro Quintero Gómez
 * @version 1.0
 */
public class Game extends WordAdapter {
    /**
     * Array that holds the characters of the secret word for the game.
     */
    private String[] secretWord;

    /**
     * String representation of the secret word.
     */
    private String secretWordString;

    /**
     * The number of hints available for the player in the game.
     */
    private int hints;

    /**
     * The number of attempts remaining for the player to guess the word.
     */
    private int attempts;

    /**
     * Constructs a new Game instance with the given secret word.
     *
     * @param secretWord the secret word to be guessed
     */
    public Game(String secretWord) {
        this.secretWord = secretWord.trim().split("");
        this.secretWordString = secretWord;
        this.hints = 3;
        this.attempts = 5;
    }

    /**
     * Checks if the provided letter input is correct.
     * A valid input must not be a digit or whitespace and must be a letter.
     *
     * @param letterInput the letter input to validate
     * @return true if the input is correct, false otherwise
     */
    @Override
    public boolean isInputCorrect(String letterInput) {
        return (!(Character.isDigit(letterInput.charAt(0)))
                && !(Character.isWhitespace(letterInput.charAt(0)))
                && letterInput.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]+$"));
    }

    /**
     * Checks if the provided word input is full.
     * In this implementation, a full input must have exactly 1 character.
     *
     * @param wordInput the word input to check
     * @return true if the input is full, false otherwise
     */
    @Override
    public boolean isInputFull(String wordInput) {
        return (wordInput.length() == 1);
    }

    /**
     * Marks a letter as correctly guessed by replacing it in the secret word array.
     *
     * @param letterPosition the position of the correctly guessed letter
     */
    public void tickCorrectLetter(int letterPosition) {
        this.secretWord[letterPosition] = "";
    }

    /**
     * Checks if the guessed letter at a specific position matches the secret word.
     *
     * @param letterInput the letter input to check
     * @param position the position of the letter in the secret word
     * @return true if the letter is correctly guessed, false otherwise
     */
    public boolean isLetterGuessed(String letterInput, int position) {
        return secretWord[position].equals(letterInput);
    }

    /**
     * Retrieves the secret word as an array of characters.
     *
     * @return the secret word array
     */
    public String[] getSecretWord() {
        return this.secretWord;
    }

    /**
     * Retrieves the secret word as a string.
     *
     * @return the secret word string
     */
    public String getSecretWordString() {
        return this.secretWordString;
    }

    /**
     * Retrieves the number of hints remaining.
     *
     * @return the number of hints
     */
    public int getHints() {
        return this.hints;
    }

    /**
     * Sets a new secret word for the game.
     *
     * @param secretWord the new secret word to set
     */
    public void setSecretWord(String[] secretWord) {
        this.secretWord = secretWord;
    }

    /**
     * Retrieves the number of attempts remaining.
     *
     * @return the number of attempts
     */
    public int getAttempts() {
        return this.attempts;
    }

    /**
     * Sets the number of attempts remaining.
     *
     * @param fails the number of failed attempts to set
     */
    public void setAttempts(int fails) {
        this.attempts = fails;
    }

    /**
     * Decreases the number of attempts by one.
     */
    public void substractTrie() {
        this.attempts--;
    }

    /**
     * Finds the next available input position in the secret word.
     *
     * @param actualInput the current input position
     * @return the next input position, or -1 if none are available
     */
    public int nearestFreeInput(int actualInput) {
        int nextInputFocus = -1;
        do {
            if (actualInput < secretWord.length - 1) {
                if (!(secretWord[actualInput + 1].equals(""))) {
                    nextInputFocus = actualInput + 1;
                }
            } else {
                actualInput = 0;
            }
        } while (nextInputFocus == -1);

        return nextInputFocus;
    }

    /**
     * Checks the progress of the game.
     * Returns -1 if the game is over, 1 if the player has won, or 0 if the game can continue.
     *
     * @return the game progress status
     */
    public int checkGameProgress() {
        if (this.attempts == 0) {
            System.out.println("Game over!");
            return -1;
        }

        if (this.attempts != 0 && Arrays.stream(secretWord).filter(i -> i.equals("")).count() == secretWord.length) {
            System.out.println("Congratulations! Winner!");
            return 1;
        }
        return 0;
    }

    /**
     * The Hint class provides functionality for using hints in the game.
     * @author Libardo Alejandro Quintero Gómez
     * @version 1.0
     */
    public class Hint {

        /**
         * Chooses a random position in the secret word for a hint.
         * Ensures the hint has not already been used.
         *
         * @return the position of the hint
         */
        public int randomHint() {
            Random rand = new Random();
            int randomHintPosition = -1;
            int doWhileAttempts = 0;
            do {
                if (doWhileAttempts == 0) {
                    randomHintPosition = rand.nextInt(secretWord.length);
                } else {
                    if (randomHintPosition == secretWord.length - 1) {
                        randomHintPosition = 0;
                    } else {
                        randomHintPosition++;
                    }
                }
                doWhileAttempts++;
            } while (secretWord[randomHintPosition].equals(""));
            hints--;
            System.out.println(hints);
            return randomHintPosition;
        }

        /**
         * Checks if hints can still be used.
         * Hints can be used if there are hints left and not all letters are guessed.
         *
         * @return true if hints can be used, false otherwise
         */
        public boolean canUseHints() {
            return hints != 0
                    && !(Arrays.stream(secretWord).filter(i -> i.equals("")).count() == secretWord.length)
                    && attempts != 0;
        }
    }
}
