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

/**
 * Contrôleur pour la vue des notifications. Permet la gestion des notifications de l'utilisateur.
 */
public class NotifMenuController {
    private NotifMenuView notifMenuView;
    private Stage stage;

    /**
     * Constructeur pour initialiser le contrôleur des notifications.
     *
     * @param stage La scène principale de l'application.
     * @param notifMenuView La vue des notifications.
     */
    public NotifMenuController(Stage stage, NotifMenuView notifMenuView) {
        this.notifMenuView = notifMenuView;
        this.stage = stage;

        this.notifMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.notifMenuView.setButtonAction(event -> handleDelete((Notification) ((Button) event.getSource()).getUserData()));
    }

    /**
     * Retourne au menu principal de l'utilisateur.
     */
    private void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }

    /**
     * Gère la suppression de la notification spécifiée.
     *
     * @param notification La notification à supprimer.
     */
    private void handleDelete(Notification notification) {
        NotifService.getInstance().suppressNotif(notification);

        notifMenuView.afficher();
        this.notifMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.notifMenuView.setButtonAction(event -> handleDelete((Notification) ((Button) event.getSource()).getUserData()));
    }
}
