package com.example.sesion5.controller;

import com.example.sesion5.model.Game;
import com.example.sesion5.view.GameStage;
import com.example.sesion5.view.HomeStage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controller for the game stage. This class manages user interactions
 * in the game interface, including entering letters, using hints, guessing the word and watching the sun eclipsing.
 * @author Libardo Alejandro Quintero Gómez
 * @version 1.0
 */
public class GameController {
    @FXML
    private HBox hBox;

    @FXML
    private Button hintsButton;

    @FXML
    private ImageView sunEclipsedImage;

    @FXML
    private Label successGameLabel;

    @FXML
    private ImageView gameBackground;

    @FXML
    private ImageView gameSuccessImage;

    @FXML
    private ImageView transitionImage;

    @FXML
    private VBox hintsVbox;

    @FXML
    private VBox backVbox;

    @FXML
    private Label hintsLabel;

    private TextField[] txtInputArray;

    private Game game;

    private Scene scene;

    /**
     * This method initializes the JavaFX components, setting their anchor positions,
     * sizes, and loading custom fonts for the labels.
     */
    @FXML
    public void initialize() {

        AnchorPane.setTopAnchor(backVbox, 55.0);
        AnchorPane.setLeftAnchor(backVbox, 30.0);

        AnchorPane.setTopAnchor(hintsVbox, 55.0);
        AnchorPane.setRightAnchor(hintsVbox, 30.0);

        gameBackground.setFitWidth(1000);
        gameBackground.setFitHeight(800);

        AnchorPane.setTopAnchor(gameBackground, 65.0);
        AnchorPane.setLeftAnchor(gameBackground, 180.0);

        AnchorPane.setBottomAnchor(hBox, 50.0);
        AnchorPane.setLeftAnchor(hBox, 180.0);

        AnchorPane.setTopAnchor(sunEclipsedImage, 0.0);
        AnchorPane.setLeftAnchor(sunEclipsedImage, 450.0);

        sunEclipsedImage.setFitHeight(500);
        sunEclipsedImage.setFitWidth(500);

        hBox.setPrefWidth(1000);
        hBox.setPrefHeight(350);

        AnchorPane.setTopAnchor(gameSuccessImage, 425.0);
        AnchorPane.setLeftAnchor(gameSuccessImage, 270.0);

        AnchorPane.setTopAnchor(successGameLabel, 575.0);
        AnchorPane.setLeftAnchor(successGameLabel, 500.0);

        gameSuccessImage.setFitHeight(800);
        gameSuccessImage.setFitWidth(800);

        Font fontHints = Font.loadFont(getClass().getResourceAsStream("/com/example/sesion5/fonts/PressStart2P-Regular.ttf"), 20);
        Font fontSuccess = Font.loadFont(getClass().getResourceAsStream("/com/example/sesion5/fonts/PressStart2P-Regular.ttf"), 15);

        hintsLabel.setText("3");
        hintsLabel.setFont(fontHints);
        hintsLabel.setPrefWidth(40);
        hintsLabel.setPrefHeight(40);

        successGameLabel.setFont(fontSuccess);
        successGameLabel.setPrefWidth(600);
        successGameLabel.setPrefHeight(30);
    }

    /**
     * This method dynamically creates and adds TextField components to the HBox.
     * Each TextField corresponds to a character in the secret word of the game.
     */
    public void createTextFields() {
        txtInputArray = new TextField[game.getSecretWord().length];
        for (int i = 0; i < game.getSecretWord().length; i++) {
            Font font = Font.loadFont(getClass().getResourceAsStream("/com/example/sesion5/fonts/PressStart2P-Regular.ttf"), 20);
            TextField txt = new TextField();
            txt.setFont(font);
            txtInputArray[i] = txt;

            txt.prefWidth(200);
            txt.prefHeight(300);
            txt.getStyleClass().add("letterDefaultInputField");
            handleTextField(txt, i);

            hBox.getChildren().add(txt);
        }
    }

    /**
     * Sets the game object for the current stage.
     *
     * @param game the game instance
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Sets the scene for the current stage.
     *
     * @param scene the current scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Changes the current stage, transitioning to a new scene after a delay.
     */
    public void changingStage() {
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
                GameStage.deletedInstance();
                try {
                    HomeStage.getInstance();
                } catch (IOException exception) {
                    System.out.println("Error: New stage can't appear");
                }
            });
        }, 3, TimeUnit.SECONDS);

        executor.shutdown();
    }

    /**
     * Updates the image of the eclipse based on the game stage.
     *
     * @param stage the current game stage
     */
    public void changeEclipseStage(int stage) {
        InputStream stream = getClass().getResourceAsStream("/com/example/sesion5/sunEclipse/SunclipsedFrame" + stage + ".png");
        Image image = new Image(stream);
        sunEclipsedImage.setImage(image);
        sunEclipsedImage.setOpacity(0.9);
    }

    /**
     * Handles the game progress by displaying the game result based on the status.
     *
     * @param gameStatus the status of the game (1 for win, -1 for loss)
     */
    public void gameToll(int gameStatus) {
        if (gameStatus == 1) {
            gameSuccessImage.getStyleClass().add("winClass");
            for (TextField txt : txtInputArray) {
                txt.setEditable(false);
            }
        } else if (gameStatus == -1) {
            gameSuccessImage.getStyleClass().add("loseClass");
            successGameLabel.setText("La palabra era " + game.getSecretWordString());
            for (TextField txt : txtInputArray) {
                txt.setEditable(false);
            }
        }
    }

    /**
     * Handles the behavior of a TextField for letter guessing.
     * It processes key events to handle user input and validate it against the game's logic.
     *
     * @param txt      the TextField to handle
     * @param position the position of the letter in the secret word
     * @see #gameToll(int)
     */
    private void handleTextField(TextField txt, int position) {
        txt.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (txt.isEditable()) {
                    String inputText;
                    if (!keyEvent.getText().isEmpty()) {
                        inputText = txt.getText().toUpperCase();
                        txt.setText(inputText);
                        if (game.isInputCorrect(txt.getText())) {
                            if (game.isLetterGuessed(inputText, position)) {
                                game.tickCorrectLetter(position);
                                txt.getStyleClass().add("letterCorrectInputField");
                                txt.setEditable(false);

                                for (int i = txtInputArray.length - 1; i >= 0; i--) {
                                    if (txtInputArray[i].isEditable()) {
                                        txtInputArray[i].requestFocus();
                                    }
                                }
                            } else {
                                game.substractTrie();
                                changeEclipseStage(6 - game.getAttempts());
                                txt.getStyleClass().add("letterWrongInputField");
                            }
                            gameToll(game.checkGameProgress());
                        } else {
                            txt.clear();
                            System.out.println("Entrada incorrecta");
                        }
                    }
                } else {
                    System.out.println("Input no editable, ya adivinaste la palabra");
                }
            }
        });

        txt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (txt.isEditable()) {
                    if (game.isInputFull(txt.getText())) {
                        txt.clear();
                    }
                }
            }
        });
    }

    /**
     * Handles the event when the hint button is clicked.
     * This method checks if hints are available and if so, it uses one hint by
     * revealing a letter from the secret word. The hint is then marked as used,
     * and the UI is updated accordingly.
     *
     * @param event The mouse event triggered when the hint button is clicked.
     *
     */
    @FXML
    void onHandleHints(MouseEvent event) {
        Game.Hint hint = game.new Hint();

        // Check if hints can be used
        if (hint.canUseHints()) {
            System.out.println("Hint used");
            int randomHint = hint.randomHint();

            // Reveal the hint in the text input
            txtInputArray[randomHint].setText(game.getSecretWord()[randomHint]);
            txtInputArray[randomHint].setEditable(false);
            game.tickCorrectLetter(randomHint);
            txtInputArray[randomHint].getStyleClass().add("letterCorrectInputField");

            // Update the game status and hints label
            gameToll(game.checkGameProgress());
            hintsLabel.setText(String.valueOf(game.getHints()));

            // Disable the hint button if no hints are left
            if (!(hint.canUseHints())) {
                hintsButton.setDisable(true);
                hintsLabel.setDisable(true);
            }

        } else {
            // Disable the hint button and label if hints are not available
            hintsButton.setDisable(true);
            hintsLabel.setDisable(true);
        }
    }

    /**
     * Handles the event when the back button is clicked.
     * This method transitions the user back to the home stage of the game.
     *
     * @param event The mouse event triggered when the back button is clicked.
     * @throws IOException if an error occurs while changing stages.
     * @see #changingStage()
     */
    @FXML
    void onHandleBack(MouseEvent event) throws IOException {
        changingStage();
    }

}


