package com.syseng.drawingapplication.rectangle;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RectangleServiceImpl implements RectangleService {
    List<AdvancedRectangle> rectangleList = new ArrayList<>();

    @Override
    public boolean doesRectangleOverlap(Rectangle pendingRectangle) {
        for (AdvancedRectangle rectangle : rectangleList) {
            boolean isOverlapping = rectangle.getBoundsInParent()
                    .intersects(pendingRectangle.getBoundsInParent());
            if (isOverlapping) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Point2D calcMiddleInRectBounds(double x, double y) {
        for (AdvancedRectangle rectangle : rectangleList) {
            boolean inBounds = rectangle.contains(x, y);
            if (inBounds) {
                return new Point2D(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            }
        }
        return null;
    }

    @Override
    public boolean containsCommentBox(double x, double y) {
        for (AdvancedRectangle rectangle : rectangleList) {
            boolean inBounds = rectangle.contains(x, y);
            if (inBounds) {
               if(rectangle.isCommentBox()) {
                   return true;
               }
            }
        }
        return false;
    }

    @Override
    public void addRectangle(Rectangle rectangle) {
        AdvancedRectangle advancedRectangle = this.createAdvancedRectangle(rectangle);
        rectangleList.add(advancedRectangle);
    }

    @Override
    public void addCommentBox(Rectangle rectangle) {
        AdvancedRectangle advancedRectangle = this.createAdvancedRectangle(rectangle);
        advancedRectangle.setCommentBox(true);
        rectangleList.add(advancedRectangle);
    }

    @Override
    public void addTitle(String title, double x, double y) {
        for (AdvancedRectangle rectangle : rectangleList) {
            boolean inBounds = rectangle.contains(x, y);
            if (inBounds) {
                rectangle.setTitle(title);
            }
        }
    }

    @Override
    public boolean hasTitle(double x, double y) {
        for (AdvancedRectangle rectangle : rectangleList) {
            boolean inBounds = rectangle.contains(x, y);
            if (inBounds) {
                return !Objects.equals(rectangle.getTitle(), "");
            }
        }
        return false;
    }

    private AdvancedRectangle createAdvancedRectangle(Rectangle rectangle) {
        return new AdvancedRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }}
