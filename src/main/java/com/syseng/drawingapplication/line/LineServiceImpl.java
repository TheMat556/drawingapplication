package com.syseng.drawingapplication.line;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LineServiceImpl implements LineService {

    List<Line> lineList = new ArrayList<>();

    private Point2D point2D = null;

    @Override
    public void addLine(Line line) {
        lineList.add(line);
    }

    @Override
    public boolean isFirstPointSet() {
        return point2D != null;
    }

    @Override
    public void setFirstPoint(Point2D point2D) {
        this.point2D = point2D;
    }

    @Override
    public void resetFirstPoint() {
        this.point2D = null;
    }

    @Override
    public Point2D getFirstPoint() {
        return point2D;
    }

    @Override
    public boolean checkIfLineExists(Line pendingLine) {
        for(Line line : lineList) {
            Point2D startPoint = new Point2D(pendingLine.getStartX(), pendingLine.getStartY());
            Point2D endPoint2D = new Point2D(pendingLine.getEndX(), pendingLine.getEndY());
            if (line.contains(startPoint) && line.contains(endPoint2D)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Line> getLineList()
    {
        return this.lineList;
    }

    @Override
    public Point2D calcMiddleOfLines(double x, double y) {
        Point2D mousePoint = new Point2D(x, y);

        for (Line line : lineList) {
            if (isPointInMiddleOfLine(mousePoint, line, 30.0)) {
                return new Point2D((line.getStartX() + line.getEndX()) / 2, (line.getStartY() + line.getEndY()) / 2);
            }
        }

        return null;
    }

    private boolean isPointInMiddleOfLine(Point2D point, Line line, double threshold) {
        double mouseX = point.getX();
        double mouseY = point.getY();

        double lineMidX = (line.getStartX() + line.getEndX()) / 2;
        double lineMidY = (line.getStartY() + line.getEndY()) / 2;

        double distance = Math.sqrt(Math.pow(mouseX - lineMidX, 2) + Math.pow(mouseY - lineMidY, 2));

        return distance <= threshold;
    }
}
