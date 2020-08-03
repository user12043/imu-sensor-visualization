module com.user12043.imuSensorVisualization {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fazecast.jSerialComm;

    opens com.user12043.imuSensorVisualization to javafx.fxml;
    exports com.user12043.imuSensorVisualization;
}