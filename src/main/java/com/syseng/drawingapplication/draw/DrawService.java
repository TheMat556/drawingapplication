package com.syseng.drawingapplication.draw;

import javafx.scene.shape.Line;

public interface DrawService {
    void handleDrawRectangle(double x, double y);

    void handleDrawLine(Line line);
}
