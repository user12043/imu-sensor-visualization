package com.user12043.imuSensorVisualization;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.AnchorPane;

public class MainViewController {
    int count = 0;
    XYChart.Series<String, Number> xData = new XYChart.Series<>();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private LineChart<String, Number> accX;
    @FXML
    private ComboBox<String> serialPortSelect;
    @FXML
    private Spinner<Integer> baudRateInput;
    @FXML
    private Button connectButton;

    public void afterStart() {
//        XYChart.Series<String, Number> data = new XYChart.Series<>();
//        data.getData().add(new XYChart.Data<>("0", 2));
//        data.getData().add(new XYChart.Data<>("1", 3));
//        data.getData().add(new XYChart.Data<>("2", 5));
//        data.getData().add(new XYChart.Data<>("3", 7));
        accX.getData().add(xData);
        SubScene objectViewScene = App.getObjectViewScene();
        anchorPane.getChildren().add(objectViewScene);

        // serial port select
        serialPortSelect.getItems().addAll(App.getReader().getPortNames(false));

        // baud rate input
        IntegerSpinnerValueFactory valueFactory = new IntegerSpinnerValueFactory(0, 2000000);
        valueFactory.setValue(115200);
        baudRateInput.setValueFactory(valueFactory);
        baudRateInput.valueProperty().addListener(this::baudRateSelected);
        serialPortSelect.getSelectionModel().selectLast();
        App.getObjectViewController().afterStart();
    }

    public void receiveSensorData(String rawData) {
        System.out.println(rawData);
        Platform.runLater(() -> {
            String data = rawData.substring(1);
            String[] split = data.split(",");
            if (xData.getData().size() > 3000) {
                xData.getData().remove(0);
            }
            // clear after some
            if (count > 6000) {
                count = 0;
                xData.getData().clear();
            }
            xData.getData().add(new XYChart.Data<>(Integer.toString(count++), Integer.valueOf(split[0])));
        });
    }

    @FXML
    public void serialPortSelected(ActionEvent event) {
        int index = serialPortSelect.getSelectionModel().getSelectedIndex();
        App.getReader().initializePort(index, baudRateInput.getValue());
    }

    private void baudRateSelected(Observable observable, Integer oldValue, Integer newValue) {
        int index = serialPortSelect.getSelectionModel().getSelectedIndex();
        App.getReader().initializePort(index, baudRateInput.getValue());
    }

    @FXML
    private void connectButtonPressed(ActionEvent event) {
        App.getReader().openPort();
    }
}
