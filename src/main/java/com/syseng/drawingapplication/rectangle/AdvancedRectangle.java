package com.syseng.drawingapplication.rectangle;

import javafx.scene.shape.Rectangle;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AdvancedRectangle extends Rectangle {
    private String title = "";
    private boolean commentBox = false;

    public AdvancedRectangle(double x, double y, double rectWidth, double rectHeight) {
        super(x, y, rectWidth, rectHeight);
    }
}
