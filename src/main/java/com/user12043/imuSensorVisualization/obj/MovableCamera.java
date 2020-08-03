package com.user12043.imuSensorVisualization.obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;

/**
 * Created on 3.08.2020 - 15:32
 * part of project imu-sensor-visualization
 *
 * @author user12043
 */
public class MovableCamera extends PerspectiveCamera {
    private Rotate xRotate;
    private Rotate yRotate;
    private DoubleProperty xRotateAngle;
    private DoubleProperty yRotateAngle;
    private DoubleProperty xTranslate;
    private DoubleProperty yTranslate;
    private DoubleProperty zTranslate;

    public MovableCamera(boolean fixedEyeAtCameraZero) {
        super(fixedEyeAtCameraZero);
        xRotateAngle = new SimpleDoubleProperty(0.0);
        yRotateAngle = new SimpleDoubleProperty(0.0);
        xTranslate = new SimpleDoubleProperty(200);
        yTranslate = new SimpleDoubleProperty(-200);
        zTranslate = new SimpleDoubleProperty(-200);
        xRotate = new Rotate(xRotateAngle.get(), Rotate.X_AXIS);
        yRotate = new Rotate(yRotateAngle.get(), Rotate.Y_AXIS);
        xRotate.angleProperty().bind(xRotateAngle);
        yRotate.angleProperty().bind(yRotateAngle);
        xTranslate = new SimpleDoubleProperty();
        translateXProperty().bind(xTranslate);
        translateYProperty().bind(yTranslate);
        translateZProperty().bind(zTranslate);
        getTransforms().addAll(xRotate, yRotate);
    }

    public void rotateX(double amount) {
        xRotateAngle.set(xRotateAngle.get() + amount);
    }

    public void rotateY(double amount) {
        yRotateAngle.set(yRotateAngle.get() + amount);
    }

    public void moveX(double amount) {
        xTranslate.set(xTranslate.get() + amount);
    }

    public void moveY(double amount) {
        yTranslate.set(yTranslate.get() + amount);
    }

    public void moveZ(double amount) {
        zTranslate.set(zTranslate.get() + amount);
    }
}
