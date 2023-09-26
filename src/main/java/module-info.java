module com.syseng.drawingapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.syseng.drawingapplication to javafx.fxml;
    exports com.syseng.drawingapplication;
}