module br.com.aula3.aula3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires static lombok;

    opens br.com.aula3.aula3 to javafx.fxml;
    exports br.com.aula3.aula3;
}