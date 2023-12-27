package com.syseng.drawingapplication;

import com.syseng.drawingapplication.draw.DrawService;
import com.syseng.drawingapplication.draw.DrawServiceImpl;
import com.syseng.drawingapplication.line.LineService;
import com.syseng.drawingapplication.line.LineServiceImpl;
import com.syseng.drawingapplication.observer.Subject;
import com.syseng.drawingapplication.overview.OverviewServiceImpl;
import com.syseng.drawingapplication.rectangle.RectangleService;
import com.syseng.drawingapplication.rectangle.RectangleServiceImpl;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML
    private RadioButton optionRectangleRadioButton;

    @FXML
    private RadioButton optionCommentBoxRadioButton;

    @FXML
    private RadioButton optionLineRadioButton;

    @FXML
    private RadioButton optionTextButton;

    @FXML
    private TextField textInputField;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private Canvas overview;

    @FXML
    private void initialize()
    {
        drawingCanvas.setOnMouseClicked(this::handleCanvasClick);

        rectangleService = new RectangleServiceImpl();
        lineService = new LineServiceImpl();
        drawService = new DrawServiceImpl(drawingCanvas);
        overviewService = new OverviewServiceImpl(new Subject(), overview,
                this.rectangleService, this.lineService);

        ToggleGroup toggleGroup = new ToggleGroup();
        optionRectangleRadioButton.setToggleGroup(toggleGroup);
        optionCommentBoxRadioButton.setToggleGroup(toggleGroup);
        optionLineRadioButton.setToggleGroup(toggleGroup);
        optionTextButton.setToggleGroup(toggleGroup);

        textInputField.visibleProperty()
                .bind(optionTextButton.selectedProperty());
    }

    RectangleService rectangleService;
    DrawService drawService;
    LineService lineService;

    OverviewServiceImpl overviewService;

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
            overviewService.updateRectangle();
        } else if (optionCommentBoxRadioButton.isSelected()) {
            Rectangle rectangle = new Rectangle(x, y, rectangleWidth, rectangleWidth);
            if (!rectangleService.doesRectangleOverlap(rectangle)) {
                rectangleService.addCommentBox(rectangle);
                drawService.drawCommentBox(x, y);
            }
            overviewService.updateCommentbox();
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
                if (rectangleService.containsCommentBox(line.getStartX(), line.getStartY()) &&
                        rectangleService.containsCommentBox(line.getEndX(), line.getEndY())) {
                    lineService.resetFirstPoint();
                    return;
                }
                if (rectangleService.containsCommentBox(line.getStartX(), line.getStartY()) ||
                        rectangleService.containsCommentBox(line.getEndX(), line.getEndY())) {
                    drawService.handleDrawDottedLine(line);
                    overviewService.updateDottedLine();

                } else {
                    drawService.handleDrawLine(line);
                    overviewService.updateLine();
                }

                lineService.resetFirstPoint();
            }
        } else if (optionTextButton.isSelected()) {
            if (textInputField.getText()
                    .isEmpty()) {
                return;
            }

            Point2D middleInRectBounds = rectangleService.calcMiddleInRectBounds(x, y);

            if (middleInRectBounds != null && !rectangleService.hasTitle(x, y)) {
                double offsetX = (middleInRectBounds.getX() - rectangleWidth / 2);
                double offsetY = middleInRectBounds.getY() + rectangleWidth / 2 + 10;
                rectangleService.addTitle(textInputField.getText(), x, y);
                drawService.handleDrawText(textInputField.getText(), offsetX, offsetY);
            } else {
                Point2D middleInLineBounds = lineService.calcMiddleOfLines(x, y);

                if (middleInLineBounds != null) {
                    drawService.handleDrawText(textInputField.getText(), middleInLineBounds.getX(), middleInLineBounds.getY());
                }
            }


        }
    }
}