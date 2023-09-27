package com.syseng.drawingapplication.draw;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DrawServiceImpl implements DrawService {
    Canvas canvas;

    private static final double rectangleWidth = 50;

    public DrawServiceImpl(Canvas canvas)
    {
        this.canvas = canvas;
    }

    @Override
    public void handleDrawRectangle(double x, double y)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);

        gc.strokeRect(x, y, rectangleWidth, rectangleWidth);
    }

    @Override
    public void handleDrawLine(Line line)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);

        gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }
}
