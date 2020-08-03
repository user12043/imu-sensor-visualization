package com.user12043.imuSensorVisualization.serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;
import com.user12043.imuSensorVisualization.App;

/**
 * Created on 3.08.2020 - 18:11
 * part of project imu-sensor-visualization
 *
 * @author user12043
 */
public class SerialListener implements SerialPortMessageListener {
    @Override
    public byte[] getMessageDelimiter() {
        return new byte[]{(byte) '|'};
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return false;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        String data = new String(event.getReceivedData());
        App.getMainViewController().receiveSensorData(data);
    }
}
