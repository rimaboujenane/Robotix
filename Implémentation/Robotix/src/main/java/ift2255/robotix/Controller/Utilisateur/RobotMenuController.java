package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.RobotMenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RobotMenuController {
    private RobotMenuView robotMenuView;
    private Stage stage;
    private Utilisateur utilisateur;

    public RobotMenuController(Stage stage,RobotMenuView robotMenuView, Utilisateur utilisateur) {
        this.robotMenuView = robotMenuView;
        this.stage = stage;
        this.utilisateur = utilisateur;

        this.robotMenuView.getBackButton().setOnAction(e -> goBackMenu());
    }

    private void goBackMenu() {
        MenuView menuView = new MenuView(utilisateur);
        MenuController menuController = new MenuController(stage, menuView, utilisateur);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
