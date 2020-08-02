package com.user12043.imuSensorVisualization;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

public class MainViewController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private LineChart<String, Number> accX;

    public void afterStart() {
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.getData().add(new XYChart.Data<>("0", 2));
        data.getData().add(new XYChart.Data<>("1", 3));
        data.getData().add(new XYChart.Data<>("2", 5));
        data.getData().add(new XYChart.Data<>("3", 7));
        accX.getData().add(data);
        SubScene objectViewScene = App.getObjectViewScene();
        anchorPane.getChildren().add(objectViewScene);
        objectViewScene.autosize();
        anchorPane.autosize();
        App.getObjectViewController().afterStart();
    }
}
