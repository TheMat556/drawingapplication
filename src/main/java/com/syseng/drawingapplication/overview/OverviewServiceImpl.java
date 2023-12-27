package com.syseng.drawingapplication.overview;

import com.syseng.drawingapplication.draw.DrawService;
import com.syseng.drawingapplication.draw.DrawServiceImpl;
import com.syseng.drawingapplication.line.LineService;
import com.syseng.drawingapplication.observer.Observer;
import com.syseng.drawingapplication.observer.Subject;
import com.syseng.drawingapplication.rectangle.RectangleService;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class OverviewServiceImpl implements Observer {
    private Subject subject;
    private RectangleService rectangleService;

    private LineService lineService;

    private final Integer rectangleWidth = 10;

    private DrawService drawService;

    public OverviewServiceImpl(Subject subject, Canvas canvas,
                               RectangleService rectangleService, LineService lineService)
    {
        this.drawService = new DrawServiceImpl(canvas);
        this.rectangleService = rectangleService;
        this.lineService = lineService;

        GraphicsContext overviewRef = canvas.getGraphicsContext2D();
        overviewRef.setFill(Color.WHITE);
        overviewRef.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        this.subject = subject;
        this.subject.addObserver(this);
    }

    @Override
    public void update()
    {
    }

    @Override
    public void updateRectangle()
    {
        var rectangleList = this.rectangleService.getRectangleList();
        var rectangle = rectangleList.get(rectangleList.size() - 1);

        double x = rectangle.getX() / 4;
        double y = rectangle.getY() / 4;

        drawService.handleDrawRectangle(x, y, 10);
    }

    @Override
    public void updateCommentbox()
    {
        var rectangleList = this.rectangleService.getRectangleList();
        var rectangle = rectangleList.get(rectangleList.size() - 1);

        double x = rectangle.getX() / 4;
        double y = rectangle.getY() / 4;

        drawService.drawCommentBox(x, y, 10);
    }

    @Override
    public void updateLine()
    {
        var lineList = this.lineService.getLineList();
        var line = lineList.get(lineList.size() - 1);
        var scalledLine = new Line(line.getStartX() / 4,
                line.getStartY() / 4,
                line.getEndX() / 4,
                line.getEndY() / 4);

        drawService.handleDrawLine(scalledLine);
    }

    @Override
    public void updateDottedLine()
    {
        var lineList = this.lineService.getLineList();
        var line = lineList.get(lineList.size() - 1);
        var scalledLine = new Line(line.getStartX() / 4,
                line.getStartY() / 4,
                line.getEndX() / 4,
                line.getEndY() / 4);

        drawService.handleDrawDottedLine(scalledLine);
    }
}
