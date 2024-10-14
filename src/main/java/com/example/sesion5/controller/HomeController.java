package com.example.sesion5.controller;

import com.example.sesion5.model.Game;
import com.example.sesion5.model.Home;
import com.example.sesion5.view.GameStage;
import com.example.sesion5.view.HomeStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controller for the home stage of the game. This class manages user interactions
 * in the home interface, including entering a secret word and transitioning to the game stage.
 * @author Libardo Alejandro Quintero Gómez
 * @version 1.0
 */
public class HomeController {

    @FXML
    private TextField secretWordInput;

    @FXML
    private Label alertLabel;

    @FXML
    private ImageView transitionImage;

    private Home home;

    private Scene scene;

    /**
     * Initializes the controller. This method sets up the user interface components,
     * including fonts, styles, and initial text for input fields and labels.
     */
    @FXML
    private void initialize() {
        home = new Home();
        Font font = Font.loadFont(getClass().getResourceAsStream("/com/example/sesion5/fonts/PressStart2P-Regular.ttf"), 9);
        alertLabel.setFont(font);
        alertLabel.setStyle("-fx-color-label-visible: white;");
        alertLabel.setPrefWidth(800);
        alertLabel.setPrefHeight(50);
        alertLabel.setText("Ingresa la palabra secreta");
        secretWordInput.setPromptText("Ingresa la palabra");
        secretWordInput.getStyleClass().add("secretWordInput");
        secretWordInput.setFont(font);
        System.out.println("Initialize HomeController");
    }

    /**
     * Handles the event when the play button is clicked. This method retrieves the secret
     * word inputted by the user, validates it, and starts the game if the input is valid.
     *
     * @throws IOException if an error occurs while transitioning to the game stage.
     * @see #changingStage(Game) 
     */
    @FXML
    public void handlePlay() throws IOException {
        String secretWord = secretWordInput.getText();
        secretWord = cleanWord(secretWord);
        System.out.println(secretWord);
        boolean isCorrect = home.isInputCorrect(secretWord) && home.isLengthCorrect(secretWord);
        System.out.println(isCorrect);

        if (isCorrect) {
            Game game = new Game(secretWordInput.getText());
            secretWordInput.setText("");
            changingStage(game);
        } else {
            alertLabel.setText("La palabra debe ser mayor a 6 letras \n" +
                    "y no puede tener números u otros caracteres");
        }
    }

    /**
     * Limits the input in the secret word text field. This method checks if the pressed key is valid
     * and manages the input length. It also triggers the game start if the Enter key is pressed.
     *
     * @param event The key event triggered by user input.
     * @throws IOException if an error occurs during the handling of input.
     */
    @FXML
    void handleLimitInput(KeyEvent event) throws IOException {
        String currentText = secretWordInput.getText();
        System.out.println(event.getCode());

        if (event.getCode().isLetterKey() || event.getCode().isDigitKey() ||
                event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.DEAD_ACUTE){
            if (home.isInputFull(currentText)) {
                secretWordInput.setText(currentText.substring(0, currentText.length() - 1));
            }
        }

        if (event.getCode() == KeyCode.ENTER) {
            handlePlay();
        }
    }

    /**
     * Cleans the input word by removing invalid characters. This method updates the input field
     * with the cleaned word.
     *
     * @param inputWord The word to be cleaned.
     * @return The cleaned word.
     */
    public String cleanWord(String inputWord) {
        inputWord = home.cleanStringInput(inputWord);
        secretWordInput.setText(inputWord);
        return inputWord;
    }

    /**
     * Sets the current scene for the home controller.
     *
     * @param scene The scene to be set.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Changes the stage to the game stage. This method handles the transition effect and
     * sets the game instance in the game controller.
     *
     * @param game The game instance to be set in the new stage.
     * @throws IOException if an error occurs while changing the stage.
     */
    public void changingStage(Game game) throws IOException {
        InputStream stream2 = getClass().getResourceAsStream("/com/example/sesion5/sunEclipse/transition.gif");
        if (stream2 != null) {
            Image image2 = new Image(stream2);
            transitionImage.setVisible(true);
            transitionImage.setImage(image2);
            transitionImage.fitWidthProperty().bind(scene.widthProperty());
            transitionImage.fitHeightProperty().bind(scene.heightProperty());
        } else {
            System.out.println("Image not found");
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.schedule(() -> {
            Platform.runLater(() -> {
                HomeStage.deletedInstance();
                try {
                    GameStage.getInstance().getGameController().setGame(game);
                    GameStage.getInstance().getGameController().createTextFields();
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            });
        }, 3, TimeUnit.SECONDS);

        executor.shutdown();
    }
}
