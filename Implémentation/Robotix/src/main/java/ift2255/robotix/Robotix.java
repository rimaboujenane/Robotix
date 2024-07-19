package ift2255.robotix;

import ift2255.robotix.Controller.LoginController;
import ift2255.robotix.View.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Robotix extends Application {
    @Override
    public void start(Stage primaryStage) {

        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(primaryStage,loginView);

        primaryStage.setTitle("Robotix");
        primaryStage.setScene(new Scene(loginView, 900, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
