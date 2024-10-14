package com.example.sesion5;

import com.example.sesion5.view.HomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for launching the JavaFX application.
 * This class extends {@link Application} and serves as the entry point for the application.
 */
public class Main extends Application {

    /**
     * Main method which serves as the starting point for the application.
     * It calls {@link Application#launch(String...)} to start the JavaFX application lifecycle.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The entry point for the JavaFX application.
     * This method is called after {@link #main(String[])} and is responsible for setting up the primary stage.
     *
     * @param primaryStage The primary stage provided by JavaFX.
     * @throws IOException If there is an issue loading the initial stage.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        HomeStage.getInstance(); // Initializes the HomeStage (main window) of the application.
    }
}
