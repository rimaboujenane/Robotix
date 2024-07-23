package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Activite;
import ift2255.robotix.Modeles.GestionActivites;
import ift2255.robotix.Modeles.Utilisateur;
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
    private Utilisateur utilisateur; // Utilisateur actuel

    /**
     * Constructeur pour initialiser le contrôleur avec la vue, la fenêtre principale et l'utilisateur.
     *
     * @param stage La fenêtre principale de l'application
     * @param activiteMenuView La vue du menu des activités
     * @param utilisateur L'utilisateur actuel
     */
    public ActiviteMenuController(Stage stage, ActiviteMenuView activiteMenuView, Utilisateur utilisateur) {
        this.stage = stage;
        this.activiteMenuView = activiteMenuView;
        this.utilisateur = utilisateur;

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
        GestionActivites activites = new GestionActivites(utilisateur);
        activites.inscription(activite, utilisateur);
        activiteMenuView.afficherVue(utilisateur);

        this.activiteMenuView.getBackButton().setOnAction(e -> goBackMenu());

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
        GestionActivites activites = new GestionActivites(utilisateur);
        activites.desinscription(activite);
        activiteMenuView.afficherVue(utilisateur);

        this.activiteMenuView.getBackButton().setOnAction(e -> goBackMenu());

        // Réinitialiser les actions des boutons après la désinscription
        this.activiteMenuView.setButtonAction("S'inscrire", event -> handleInscription((Activite) ((Button) event.getSource()).getUserData()));
        this.activiteMenuView.setButtonAction("Se désinscrire", event -> handleDesinscription((Activite) ((Button) event.getSource()).getUserData()));
    }

    /**
     * Retourne au menu principal.
     */
    public void goBackMenu() {
        MenuView menuView = new MenuView(utilisateur);
        MenuController menuController = new MenuController(stage, menuView, utilisateur);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
