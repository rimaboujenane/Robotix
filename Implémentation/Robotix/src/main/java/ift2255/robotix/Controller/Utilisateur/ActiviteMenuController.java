package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.*;
import ift2255.robotix.View.Utilisateur.ActiviteMenuView;
import ift2255.robotix.View.Utilisateur.MenuView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue du menu des activités, gérant les inscriptions et désinscriptions aux activités.
 */
public class ActiviteMenuController {
    private ActiviteMenuView activiteMenuView; // Vue associée au contrôleur
    private Stage stage; // Fenêtre principale de l'application
    private Utilisateur utilisateur = RegisterUtilisateur.getInstance().getUtilisateur();

    /**
     * Constructeur pour initialiser le contrôleur avec la vue, la fenêtre principale et l'utilisateur.
     *
     * @param stage La fenêtre principale de l'application
     * @param activiteMenuView La vue du menu des activités
     */
    public ActiviteMenuController(Stage stage, ActiviteMenuView activiteMenuView) {
        this.stage = stage;
        this.activiteMenuView = activiteMenuView;

        // Définir l'action pour le bouton "Retour"
        this.activiteMenuView.getBackButton().setOnAction(e -> goBackMenu());

        // Définir les actions pour les boutons d'inscription et de désinscription
        this.activiteMenuView.setButtonAction("S'inscrire", event -> handleInscription((Activite) ((Button) event.getSource()).getUserData()));
        this.activiteMenuView.setButtonAction("Se désinscrire", event -> handleDesinscription((Activite) ((Button) event.getSource()).getUserData()));
    }

    /**
     * Gère l'inscription de l'utilisateur à une activité.
     *
     * @param activite L'activité à laquelle l'utilisateur souhaite s'inscrire
     */
    public void handleInscription(Activite activite) {
        GestionActivites activites = new GestionActivites();
        activites.inscription(activite);
        activiteMenuView.afficherVue();

        this.activiteMenuView.getBackButton().setOnAction(e -> goBackMenu());

        NotifService.getInstance().sendNotif("Inscription à l'activité : " + activite.getNom()); // envoyer une notification

        // Réinitialiser les actions des boutons après l'inscription
        this.activiteMenuView.setButtonAction("S'inscrire", event -> handleInscription((Activite) ((Button) event.getSource()).getUserData()));
        this.activiteMenuView.setButtonAction("Se désinscrire", event -> handleDesinscription((Activite) ((Button) event.getSource()).getUserData()));
    }

    /**
     * Gère la désinscription de l'utilisateur d'une activité.
     *
     * @param activite L'activité dont l'utilisateur souhaite se désinscrire
     */
    public void handleDesinscription(Activite activite) {
        GestionActivites activites = new GestionActivites();
        activites.desinscription(activite);
        activiteMenuView.afficherVue();

        this.activiteMenuView.getBackButton().setOnAction(e -> goBackMenu());
        NotifService.getInstance().sendNotif("Désinscription à l'activité : " + activite.getNom()); // envoyer une notification

        // Réinitialiser les actions des boutons après la désinscription
        this.activiteMenuView.setButtonAction("S'inscrire", event -> handleInscription((Activite) ((Button) event.getSource()).getUserData()));
        this.activiteMenuView.setButtonAction("Se désinscrire", event -> handleDesinscription((Activite) ((Button) event.getSource()).getUserData()));
    }

    /**
     * Retourne au menu principal.
     */
    public void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
