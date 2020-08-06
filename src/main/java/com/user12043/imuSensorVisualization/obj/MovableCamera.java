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
    private final DoubleProperty xRotateAngle;
    private final DoubleProperty yRotateAngle;
    private final DoubleProperty zRotateAngle;
    private final DoubleProperty xTranslate;
    private final DoubleProperty yTranslate;
    private final DoubleProperty zTranslate;

    public MovableCamera(boolean fixedEyeAtCameraZero) {
        super(fixedEyeAtCameraZero);
        xRotateAngle = new SimpleDoubleProperty();
        yRotateAngle = new SimpleDoubleProperty();
        zRotateAngle = new SimpleDoubleProperty();
        xTranslate = new SimpleDoubleProperty();
        yTranslate = new SimpleDoubleProperty();
        zTranslate = new SimpleDoubleProperty();
        Rotate xRotate = new Rotate(xRotateAngle.get(), Rotate.X_AXIS);
        Rotate yRotate = new Rotate(yRotateAngle.get(), Rotate.Y_AXIS);
        Rotate zRotate = new Rotate(yRotateAngle.get(), Rotate.Z_AXIS);
        xRotate.angleProperty().bind(xRotateAngle);
        yRotate.angleProperty().bind(yRotateAngle);
        zRotate.angleProperty().bind(zRotateAngle);
        translateXProperty().bind(xTranslate);
        translateYProperty().bind(yTranslate);
        translateZProperty().bind(zTranslate);
        getTransforms().addAll(xRotate, yRotate, zRotate);
        resetPosition();
    }

    public void rotateX(double amount) {
        xRotateAngle.set(xRotateAngle.get() + amount);
    }

    public void rotateY(double amount) {
        yRotateAngle.set(yRotateAngle.get() + amount);
    }

    public void rotateZ(double amount) {
        zRotateAngle.set(zRotateAngle.get() + amount);
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

    public void resetPosition() {
        xTranslate.set(200);
        yTranslate.set(-350);
        zTranslate.set(-500);
        xRotateAngle.set(-30);
        yRotateAngle.set(-15);
    }
}
