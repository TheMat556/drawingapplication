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
    public void handleDrawRectangle(double x, double y,double width)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);

        gc.strokeRect(x, y, width, width);
    }

    @Override
    public void drawCommentBox(double x, double y)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineDashes(5);
        gc.setLineWidth(2.0);

        gc.strokeRect(x, y, rectangleWidth, rectangleWidth);
        gc.setLineDashes(1);
    }

    @Override
    public void drawCommentBox(double x, double y, double width)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineDashes(3.0);
        gc.setLineWidth(1.0);

        gc.strokeRect(x, y, width, width);
        gc.setLineDashes(1);
    }

    @Override
    public void handleDrawDottedLine(Line line)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineDashes(5);
        this.handleDrawLine(line);
        gc.setLineDashes(1);
    }

    @Override
    public void handleDrawLine(Line line)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);

        gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());

        double arrowSize = 10;
        drawArrowhead(gc, line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY(), arrowSize);
    }

    private void drawArrowhead(GraphicsContext gc, double x1, double y1, double x2, double y2, double size) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double x3 = x2 - size * Math.cos(angle - Math.toRadians(30));
        double y3 = y2 - size * Math.sin(angle - Math.toRadians(30));

        double x4 = x2 - size * Math.cos(angle + Math.toRadians(30));
        double y4 = y2 - size * Math.sin(angle + Math.toRadians(30));

        gc.setFill(Color.BLACK);
        gc.fillPolygon(new double[]{x2, x3, x4}, new double[]{y2, y3, y4}, 3);
    }

    @Override
    public void handleDrawText(String text, double x, double y) {
        System.out.println("!!!");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillText(text, x, y);
    }
}
