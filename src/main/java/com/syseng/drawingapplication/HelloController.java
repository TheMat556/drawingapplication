package com.syseng.drawingapplication;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class HelloController {

    @FXML
    private RadioButton optionRectangleRadioButton;

    @FXML
    private RadioButton optionLineRadioButton;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private void initialize() {
        drawingCanvas.setOnMouseClicked(this::handleCanvasClick);
    }

    private void handleCanvasClick(MouseEvent mouseEvent) {
        if (optionRectangleRadioButton.isSelected()) {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

            GraphicsContext gc = drawingCanvas.getGraphicsContext2D();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2.0);

            gc.strokeRect(x, y, 50, 50);
        }
    }

}