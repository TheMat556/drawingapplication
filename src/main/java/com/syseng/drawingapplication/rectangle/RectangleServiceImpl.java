package com.syseng.drawingapplication.rectangle;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class RectangleServiceImpl implements RectangleService {
    List<Rectangle> rectangleList = new ArrayList<>();

    @Override
    public boolean doesRectangleOverlap(Rectangle pendingRectangle)
    {
        for (Rectangle rectangle : rectangleList) {
            boolean isOverlapping = rectangle.getBoundsInParent()
                    .intersects(pendingRectangle.getBoundsInParent());
            if (isOverlapping) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Point2D calcMiddleInRectBounds(double x, double y)
    {
        for (Rectangle rectangle : rectangleList) {
            boolean inBounds = rectangle.contains(x, y);
            if (inBounds) {
                return new Point2D(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            }
        }
        return null;
    }

    @Override
    public void addRectangle(Rectangle rectangle)
    {
        rectangleList.add(rectangle);
    }

}
