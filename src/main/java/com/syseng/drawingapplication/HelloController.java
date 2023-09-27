package com.syseng.drawingapplication;

import com.syseng.drawingapplication.draw.DrawServiceImpl;
import com.syseng.drawingapplication.line.LineServiceImpl;
import com.syseng.drawingapplication.rectangle.RectangleServiceImpl;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML
    private RadioButton optionRectangleRadioButton;

    @FXML
    private RadioButton optionLineRadioButton;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private void initialize()
    {
        drawingCanvas.setOnMouseClicked(this::handleCanvasClick);

        rectangleService = new RectangleServiceImpl();
        lineService = new LineServiceImpl();
        drawService = new DrawServiceImpl(drawingCanvas);

        ToggleGroup toggleGroup = new ToggleGroup();
        optionRectangleRadioButton.setToggleGroup(toggleGroup);
        optionLineRadioButton.setToggleGroup(toggleGroup);
    }

    RectangleServiceImpl rectangleService;
    DrawServiceImpl drawService;
    LineServiceImpl lineService;

    private static final double rectangleWidth = 50;

    private void handleCanvasClick(MouseEvent mouseEvent)
    {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if (optionRectangleRadioButton.isSelected()) {
            Rectangle rectangle = new Rectangle(x, y, rectangleWidth, rectangleWidth);

            if (!rectangleService.doesRectangleOverlap(rectangle)) {
                rectangleService.addRectangle(rectangle);
                drawService.handleDrawRectangle(x, y);
            }
        } else if (optionLineRadioButton.isSelected()) {
            Point2D middleInRectBounds = rectangleService.calcMiddleInRectBounds(x, y);

            if (middleInRectBounds != null) {
                if (!lineService.isFirstPointSet() || lineService.getFirstPoint()
                        .equals(middleInRectBounds)) {
                    lineService.setFirstPoint(middleInRectBounds);
                    return;
                }

                Line line = new Line(lineService.getFirstPoint()
                        .getX(), lineService.getFirstPoint()
                        .getY(), middleInRectBounds.getX(), middleInRectBounds.getY());

                if (lineService.checkIfLineExists(line)) {
                    lineService.resetFirstPoint();
                    return;
                }

                lineService.addLine(line);
                drawService.handleDrawLine(line);
                lineService.resetFirstPoint();
            }
        }
    }
}