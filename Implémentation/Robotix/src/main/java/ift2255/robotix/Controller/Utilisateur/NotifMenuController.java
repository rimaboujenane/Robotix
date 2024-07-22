package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.NotifMenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotifMenuController {
    private NotifMenuView notifMenuView;
    private Stage stage;
    private Utilisateur utilisateur;

    public NotifMenuController(Stage stage, NotifMenuView notifMenuView, Utilisateur utilisateur) {
        this.notifMenuView = notifMenuView;
        this.stage = stage;
        this.utilisateur = utilisateur;

        this.notifMenuView.getBackButton().setOnAction(e -> goBackMenu());
    }

    private void goBackMenu() {
        MenuView menuView = new MenuView(utilisateur);
        MenuController menuController = new MenuController(stage, menuView, utilisateur);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
