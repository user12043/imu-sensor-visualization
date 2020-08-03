package com.user12043.imuSensorVisualization.serial;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 3.08.2020 - 16:15
 * part of project imu-sensor-visualization
 *
 * @author user12043
 */
public class SerialReader {
    private static final String separator = "|";
    private SerialPort[] serialPorts;
    private SerialPort serialPort;
    private SerialListener serialListener;
    private long lastOpenMillis;

    public SerialReader() {
        initialize();
    }

    private void initialize() {
        serialPorts = SerialPort.getCommPorts();
        serialListener = new SerialListener();
    }

    public final List<String> getPortNames(boolean refresh) {
        if (refresh) initialize();
        return Arrays.stream(serialPorts).map(s -> s.getSystemPortName() + ", " + s.getDescriptivePortName()).collect(Collectors.toUnmodifiableList());
    }

    public void initializePort(int index, int baudRate) {
        serialPort = serialPorts[index];
        serialPort.setBaudRate(baudRate);
    }

    public void openPort() {
        if (serialPort == null) {
            throw new IllegalStateException("Port is not initialized!");
        }
        serialPort.openPort(100);
        serialPort.addDataListener(serialListener);
        lastOpenMillis = System.nanoTime();
    }

    public void close() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.removeDataListener();
            serialPort.closePort();
        }
    }
}
