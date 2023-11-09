package com.syseng.drawingapplication.draw;

import javafx.scene.shape.Line;

public interface DrawService {
    void handleDrawRectangle(double x, double y);

    void drawCommentBox(double x, double y);

    void handleDrawLine(Line line);

    void handleDrawDottedLine(Line line);

    void handleDrawText(String text, double x, double y);
}
