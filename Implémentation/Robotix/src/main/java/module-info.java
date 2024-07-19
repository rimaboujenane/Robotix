module ift2255.robotix {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.opencsv;

    opens ift2255.robotix to javafx.fxml;
    exports ift2255.robotix;
    exports ift2255.robotix.Controller;
    opens ift2255.robotix.Controller to javafx.fxml;
    exports ift2255.robotix.Controller.Utilisateur;
    opens ift2255.robotix.Controller.Utilisateur to javafx.fxml;
}