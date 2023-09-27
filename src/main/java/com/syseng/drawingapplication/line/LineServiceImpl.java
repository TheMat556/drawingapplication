package com.syseng.drawingapplication.line;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

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
}
