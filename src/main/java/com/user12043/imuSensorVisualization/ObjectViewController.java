package com.user12043.imuSensorVisualization;

import com.user12043.imuSensorVisualization.obj.MovableCamera;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
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

    private MovableCamera camera;

    public void afterStart() {
        drawAxes();
        PhongMaterial material = new PhongMaterial(Color.BLUE);
        box.setMaterial(material);
        camera = new MovableCamera(true);
        camera.setFarClip(1000);
        camera.setNearClip(0.5);
        App.getObjectViewScene().setCamera(camera);
//        App.getStage().addEventHandler(KeyEvent.KEY_PRESSED, this::keyEvent);
    }

    private void drawAxes() {
        PhongMaterial red = new PhongMaterial(Color.DARKRED);
        red.setSpecularColor(Color.RED);
        PhongMaterial green = new PhongMaterial(Color.DARKGREEN);
        green.setSpecularColor(Color.GREEN);
        PhongMaterial blue = new PhongMaterial(Color.DARKBLUE);
        blue.setSpecularColor(Color.BLUE);
        xBox.setMaterial(red);
        yBox.setMaterial(green);
        zBox.setMaterial(blue);
    }

    public void keyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                camera.moveZ(cameraStep);
                break;
            case S:
                camera.moveZ(-cameraStep);
                break;
            case A:
                camera.moveX(-cameraStep);
                break;
            case D:
                camera.moveX(cameraStep);
                break;
            case Q:
                camera.moveY(cameraStep);
                break;
            case E:
                camera.moveY(-cameraStep);
                break;
            case UP:
                camera.rotateX(rotateStep);
                break;
            case DOWN:
                camera.rotateX(-rotateStep);
                break;
            case LEFT:
                camera.rotateY(-rotateStep);
                break;
            case RIGHT:
                camera.rotateY(rotateStep);
                break;
            case HOME:
                camera.rotateZ(rotateStep);
                break;
            case END:
                camera.rotateZ(-rotateStep);
                break;
            case SPACE:
                camera.resetPosition();
                break;
        }
        // block event to prevent focusing other nodes
        if (keyEvent.getCode() != KeyCode.TAB) keyEvent.consume();
    }
}
