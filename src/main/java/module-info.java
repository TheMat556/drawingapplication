module com.syseng.drawingapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens com.syseng.drawingapplication to javafx.fxml;
    exports com.syseng.drawingapplication;
    exports com.syseng.drawingapplication.rectangle;
    opens com.syseng.drawingapplication.rectangle to javafx.fxml;
}