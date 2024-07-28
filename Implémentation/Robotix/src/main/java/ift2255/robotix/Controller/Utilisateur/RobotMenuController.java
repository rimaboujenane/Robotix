package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.RobotMenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RobotMenuController {
    private RobotMenuView robotMenuView;
    private Stage stage;

    public RobotMenuController(Stage stage,RobotMenuView robotMenuView) {
        this.robotMenuView = robotMenuView;
        this.stage = stage;

        this.robotMenuView.getBackButton().setOnAction(e -> goBackMenu());
    }

    private void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
