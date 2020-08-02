package com.user12043.imuSensorVisualization;

import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * Created on 8/2/20 - 8:14 PM
 * part of project acc-sensor-visualization
 *
 * @author user12043
 */
public class ObjectViewController {
    private static final double cameraStep = 10;
    private static final double rotateStep = 3;
    @FXML
    private Box box;

    @FXML
    private Box xBox;
    @FXML
    private Box yBox;
    @FXML
    private Box zBox;

    private PerspectiveCamera camera;

    public void afterStart() {
        drawAxes();
//        PhongMaterial material = new PhongMaterial(Color.BLUE);
//        box.setMaterial(material);
        camera = new PerspectiveCamera(true);
//        camera.setTranslateX(100);
//        camera.setTranslateY(100);
//        camera.setTranslateZ(100);
        camera.setFieldOfView(10000);
        App.getObjectViewScene().setCamera(camera);
        App.getStage().addEventHandler(KeyEvent.KEY_PRESSED, this::keyEvent);
    }

    private void drawAxes() {
        PhongMaterial red = new PhongMaterial(Color.RED);
        PhongMaterial green = new PhongMaterial(Color.GREEN);
        PhongMaterial blue = new PhongMaterial(Color.BLUE);
        xBox.setMaterial(red);
        yBox.setMaterial(green);
        zBox.setMaterial(blue);
    }

    public void keyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                camera.setTranslateZ(camera.getTranslateZ() + cameraStep);
                break;
            case S:
                camera.setTranslateZ(camera.getTranslateZ() - cameraStep);
                break;
            case A:
                camera.setTranslateX(camera.getTranslateX() - cameraStep);
                break;
            case D:
                camera.setTranslateX(camera.getTranslateX() + cameraStep);
                break;
            case Q:
                camera.setTranslateY(camera.getTranslateY() - cameraStep);
                break;
            case E:
                camera.setTranslateY(camera.getTranslateY() + cameraStep);
                break;
            case UP:
                camera.setRotationAxis(new Point3D(1.0, 0.0, 0.0));
                camera.setRotate(camera.getRotate() + rotateStep);
                break;
            case DOWN:
                camera.setRotationAxis(new Point3D(1.0, 0.0, 0.0));
                camera.setRotate(camera.getRotate() - rotateStep);
                break;
            case LEFT:
                camera.setRotationAxis(new Point3D(0.0, 1.0, 0.0));
                camera.setRotate(camera.getRotate() + rotateStep);
                break;
            case RIGHT:
                camera.setRotationAxis(new Point3D(0.0, 1.0, 0.0));
                camera.setRotate(camera.getRotate() - rotateStep);
                break;
        }
    }
}
