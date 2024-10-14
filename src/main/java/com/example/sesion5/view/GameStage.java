package com.example.sesion5.view;

import com.example.sesion5.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GameStage class represents the stage where the main game is displayed.
 * It loads the game-view FXML file, applies the controller, sets up the scene, and manages the game interface.
 * @author Libardo Alejandro Quintero Gómez
 * @version 1.0
 */
public class GameStage extends Stage {

    /** The controller for managing the game view and logic. */
    private GameController gameController;

    /**
     * Constructor for GameStage.
     * It initializes the game stage by loading the FXML file, applying stylesheets,
     * and setting the title, icon, and other properties of the stage.
     *
     * @throws IOException If there is an error loading the FXML file.
     */
    public GameStage() throws IOException {
        setMaximized(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/sesion5/game-view.fxml"
        ));
        Parent root = loader.load();
        gameController = loader.getController();
        Scene scene = new Scene(root);

        gameController.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/com/example/sesion5/style/game.css").toExternalForm());
        setScene(scene);
        setTitle("Sol eclipsado");
        getIcons().add(new Image(String.valueOf(
                getClass().getResource("/com/example/sesion5/images/favicon.png"))
        ));
        setResizable(false);
        show();
    }

    /**
     * Returns the GameController associated with the current GameStage.
     *
     * @return The GameController instance managing the game logic.
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Inner static class for holding a single instance of GameStage.
     * This is part of the Singleton pattern to ensure only one instance of GameStage exists.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    /**
     * Returns the single instance of GameStage, creating it if it does not already exist.
     *
     * @return The single instance of GameStage.
     * @throws IOException If there is an error creating the stage.
     */
    public static GameStage getInstance() throws IOException {
        GameStage.GameStageHolder.INSTANCE =
                GameStage.GameStageHolder.INSTANCE != null ? GameStage.GameStageHolder.INSTANCE : new GameStage();
        return GameStage.GameStageHolder.INSTANCE;
    }

    /**
     * Deletes the current instance of GameStage by closing the stage and setting the instance to null.
     */
    public static void deletedInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }
}
