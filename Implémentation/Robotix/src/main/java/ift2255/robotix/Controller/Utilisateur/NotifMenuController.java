package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Activite;
import ift2255.robotix.Modeles.NotifService;
import ift2255.robotix.Modeles.Notification;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.NotifMenuView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotifMenuController {
    private NotifMenuView notifMenuView;
    private Stage stage;

    public NotifMenuController(Stage stage, NotifMenuView notifMenuView) {
        this.notifMenuView = notifMenuView;
        this.stage = stage;

        this.notifMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.notifMenuView.setButtonAction(event -> handleDelete((Notification) ((Button) event.getSource()).getUserData()));
    }

    private void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }

    private void handleDelete(Notification notification) {
        NotifService.getInstance().suppressNotif(notification);

        notifMenuView.afficher();
        this.notifMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.notifMenuView.setButtonAction(event -> handleDelete((Notification) ((Button) event.getSource()).getUserData()));
    }
}
