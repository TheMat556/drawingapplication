package com.syseng.drawingapplication.line;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public interface LineService {
    void addLine(Line line);

    boolean isFirstPointSet();

    void setFirstPoint(Point2D point2D);

    void resetFirstPoint();

    Point2D getFirstPoint();

    boolean checkIfLineExists(Line pendingLine);
}
