package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Modeles.NotifFournisseur;
import ift2255.robotix.Modeles.NotifService;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import ift2255.robotix.View.Fournisseur.NotifFournisseurView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue des notifications fournisseur.
 * Cette classe gère les interactions entre la vue des notifications fournisseur
 * et les actions de l'utilisateur, telles que la suppression d'une notification
 * et le retour au menu fournisseur.
 */
public class NotifFournisseurController {
    private NotifFournisseurView notifFournisseurView;
    private Stage stage;

    /**
     * Constructeur de la classe NotifFournisseurController.
     *
     * @param stage La scène principale de l'application.
     * @param notifFournisseurView La vue des notifications fournisseur à contrôler.
     */
    public NotifFournisseurController(Stage stage, NotifFournisseurView notifFournisseurView) {
        this.notifFournisseurView = notifFournisseurView;
        this.stage = stage;

        this.notifFournisseurView.getBackButton().setOnAction(e -> goBackMenu());
        this.notifFournisseurView.setButtonAction(event -> handleDelete((NotifFournisseur) ((Button) event.getSource()).getUserData()));
    }

    /**
     * Retourne à la vue du menu fournisseur.
     */
    private void goBackMenu() {
        MenuFournisseurView menuFournisseurView = new MenuFournisseurView();
        MenuFournisseurController menuFournisseurController = new MenuFournisseurController(stage, menuFournisseurView);
        stage.setScene(new Scene(menuFournisseurView, 900, 700));
    }

    /**
     * Gère la suppression d'une notification fournisseur.
     *
     * @param notifFournisseur La notification fournisseur à supprimer.
     */
    private void handleDelete(NotifFournisseur notifFournisseur) {
        NotifService.getInstance().suppressNotifFournisseur(notifFournisseur);
        notifFournisseurView.afficher();
        this.notifFournisseurView.getBackButton().setOnAction(e -> goBackMenu());
        this.notifFournisseurView.setButtonAction(event -> handleDelete((NotifFournisseur) ((Button) event.getSource()).getUserData()));
    }
}
