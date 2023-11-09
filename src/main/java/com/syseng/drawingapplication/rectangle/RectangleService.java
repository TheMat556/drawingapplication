package com.syseng.drawingapplication.rectangle;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public interface RectangleService {

    boolean doesRectangleOverlap(Rectangle newRectangle);

    Point2D calcMiddleInRectBounds(double x, double y);

    boolean containsCommentBox(double x, double y);

    void addRectangle(Rectangle rectangle);

    void addCommentBox(Rectangle rectangle);

    void addTitle(String title, double x, double y);

    boolean hasTitle(double x, double y);
}
