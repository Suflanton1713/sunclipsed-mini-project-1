package com.example.sesion5.view;

import com.example.sesion5.controller.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * HomeStage class represents the primary stage for the "Sol eclipsado" game.
 * It loads the FXML file, sets up the scene, and applies the controller and stylesheets.
 * @author Libardo Alejandro Quintero Gómez
 * @version 1.0
 */
public class HomeStage extends Stage {

    /** The controller for managing the home view. */
    private HomeController homeController;

    /**
     * Constructor for HomeStage.
     * It initializes the stage by loading the FXML file, setting the scene, applying stylesheets,
     * and setting the title and icon for the application.
     *
     * @throws IOException If there is an error loading the FXML file.
     */
    public HomeStage() throws IOException {
        setMaximized(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/sesion5/home-view.fxml"
        ));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        homeController = loader.getController();
        homeController.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/com/example/sesion5/style/home.css").toExternalForm());
        setScene(scene);
        setTitle("Sol eclipsado");
        getIcons().add(new Image(String.valueOf(
                getClass().getResource("/com/example/sesion5/images/favicon.png"))
        ));
        show();
    }

    /**
     * Inner static class for holding a single instance of HomeStage.
     * This is part of the Singleton pattern to ensure only one instance of HomeStage exists.
     */
    private static class HomeStageHolder {
        private static HomeStage INSTANCE;
    }

    /**
     * Returns the single instance of HomeStage, creating it if it does not already exist.
     *
     * @return The single instance of HomeStage.
     * @throws IOException If there is an error creating the stage.
     */
    public static HomeStage getInstance() throws IOException{
        HomeStageHolder.INSTANCE =
                HomeStageHolder.INSTANCE != null ? HomeStageHolder.INSTANCE : new HomeStage();
        return HomeStageHolder.INSTANCE;
    }

    /**
     * Deletes the current instance of HomeStage by closing the stage and setting the instance to null.
     */
    public static void deletedInstance() {
        HomeStageHolder.INSTANCE.close();
        HomeStageHolder.INSTANCE = null;
    }
}
