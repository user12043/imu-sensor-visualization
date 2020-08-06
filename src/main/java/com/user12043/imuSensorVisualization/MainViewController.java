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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class MainViewController {
    int count = 0;
    XYChart.Series<Number, Number> xData = new XYChart.Series<>();
    XYChart.Series<Number, Number> yData = new XYChart.Series<>();
    XYChart.Series<Number, Number> zData = new XYChart.Series<>();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private LineChart<Number, Number> chart;
    @FXML
    private ComboBox<String> serialPortSelect;
    @FXML
    private Spinner<Integer> baudRateInput;
    @FXML
    private Button connectButton;
    @FXML
    private Button sceneFocusButton;

    public void afterStart() {
        xData.setName("X");
        yData.setName("Y");
        zData.setName("Z");
        //noinspection unchecked
        chart.getData().addAll(xData, yData, zData);
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
        sceneFocusButton.addEventFilter(KeyEvent.KEY_PRESSED, App.getObjectViewController()::keyEvent);
        sceneFocusButton.requestFocus();
        App.getObjectViewController().afterStart();
    }

    public void receiveSensorData(String rawData) {
        System.out.println(rawData);
        Platform.runLater(() -> {
            String data = rawData.substring(1);
            String[] split = data.split(",");
            /*if (xData.getData().size() > 300) {
                xData.getData().remove(0);
                yData.getData().remove(0);
                zData.getData().remove(0);
            }*/
            // clear after some
            if (count >= 1000) {
                count = 0;
                xData.getData().clear();
                yData.getData().clear();
                zData.getData().clear();
                chart.getXAxis().setAutoRanging(false);
            }
            xData.getData().add(new XYChart.Data<>(count, Integer.valueOf(split[0])));
            yData.getData().add(new XYChart.Data<>(count, Integer.valueOf(split[1])));
            zData.getData().add(new XYChart.Data<>(count++, Integer.valueOf(split[2])));
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
