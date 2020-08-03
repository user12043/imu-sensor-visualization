package com.user12043.imuSensorVisualization;

import com.user12043.imuSensorVisualization.serial.SerialReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * JavaFX App to visualize sensor values
 */
public class App extends Application {
    private static Stage stage;
    private static MainViewController mainViewController;
    private static ObjectViewController objectViewController;
    private static Scene mainViewScene;
    private static SubScene objectViewScene;
    private static SerialReader serialReader;

    /*static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }*/

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent loaded = fxmlLoader.load();
        switch (fxml) {
            case "main-view":
                mainViewController = fxmlLoader.getController();
                break;
            case "object-view":
                objectViewController = fxmlLoader.getController();
                break;
        }
        return loaded;
    }

    public static void main(String[] args) {
        // -Dprism.verbose=true -Dprism.forceGPU=true
        launch();
    }

    static Stage getStage() {
        return stage;
    }

    static SubScene getObjectViewScene() {
        if (objectViewScene == null) {
            try {
                objectViewScene = new SubScene(loadFXML("object-view"), 600, 400, false, SceneAntialiasing.BALANCED);
            } catch (IOException e) {
                System.err.println("Could not load sub scene!");
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
        return objectViewScene;
    }

    public static MainViewController getMainViewController() {
        return mainViewController;
    }

    static ObjectViewController getObjectViewController() {
        return objectViewController;
    }

    static SerialReader getReader() {
        return serialReader;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("IMU TEST");
        mainViewScene = new Scene(loadFXML("main-view"), 1200, 800);
        primaryStage.setScene(mainViewScene);
        primaryStage.show();
        serialReader = new SerialReader();
        mainViewController.afterStart();
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            serialReader.close();
            System.exit(0);
        });
    }
}
