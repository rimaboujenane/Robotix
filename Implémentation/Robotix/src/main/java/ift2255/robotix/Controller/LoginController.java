package ift2255.robotix.Controller;

import ift2255.robotix.Controller.Fournisseur.MenuFournisseurController;
import ift2255.robotix.Controller.Utilisateur.MenuController;
import ift2255.robotix.Modeles.*;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import ift2255.robotix.View.InscriptionView;
import ift2255.robotix.View.LoginView;
import ift2255.robotix.View.Utilisateur.MenuView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue de connexion, gérant les actions liées à la connexion des utilisateurs et des fournisseurs.
 */
public class LoginController {
    private LoginView view; // Vue associée au contrôleur
    private Stage stage; // Fenêtre principale de l'application
    private Utilisateur utilisateur; // Instance de l'utilisateur connecté
    private Fournisseur fournisseur; // Instance du fournisseur connecté

    /**
     * Constructeur pour initialiser le contrôleur avec la vue et la fenêtre principale.
     *
     * @param stage La fenêtre principale de l'application
     * @param view La vue de connexion
     */
    public LoginController(Stage stage, LoginView view) {
        this.stage = stage;
        this.view = view;

        // Définir l'action pour le bouton de connexion
        this.view.getLoginButton().setOnAction(e -> handleLogin(view.getLoginTypeComboBox()));

        // Définir l'action pour l'étiquette d'inscription pour les utilisateurs
        this.view.getRegisterLabel().setOnMouseClicked(e -> inscription(false));

        // Définir l'action pour l'étiquette d'inscription pour les fournisseurs
        this.view.getRegisterFournisseurLabel().setOnMouseClicked(e -> inscription(true));
    }

    /**
     * Gère la connexion de l'utilisateur ou du fournisseur en fonction du type sélectionné.
     *
     * @param type Le ComboBox pour sélectionner le type d'utilisateur (Utilisateur ou Fournisseur)
     */
    private void handleLogin(ComboBox<String> type) {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();

        // Vérifier les informations de connexion pour un utilisateur
        if ("Utilisateur".equals(type.getValue())) {
            GestionUtilisateurs utilisateurs = new GestionUtilisateurs(); // Gestion des utilisateurs
            if (utilisateurs.isValidUser(username, password)) {
                this.utilisateur = utilisateurs.getUtilisateur();
                RegisterUtilisateur.getInstance().setUtilisateur(this.utilisateur);
                NotifService.getInstance().getNotifications().clear();
                navigateToMenu(); // Naviguer vers le menu utilisateur
            } else {
                invalid(); // Afficher un message d'erreur si les informations de connexion sont incorrectes
            }
        }
        // Vérifier les informations de connexion pour un fournisseur
        else if ("Fournisseur".equals(type.getValue())) {
            GestionFournisseurs fournisseurs = new GestionFournisseurs(); // Gestion des fournisseurs
            if (fournisseurs.isValidUser(username, password)) {
                this.fournisseur = fournisseurs.getFournisseur();
                RegisterFournisseur.getInstance().setFournisseur(this.fournisseur);
                navigateToMenuFournisseur(); // Naviguer vers le menu fournisseur
            } else {
                invalid(); // Afficher un message d'erreur si les informations de connexion sont incorrectes
            }
        }
    }

    /**
     * Ouvre la vue d'inscription pour permettre à un nouvel utilisateur ou fournisseur de s'inscrire.
     *
     * @param fournisseur Indique si l'inscription concerne un fournisseur (true) ou un utilisateur (false)
     */
    public void inscription(Boolean fournisseur) {
        InscriptionView inscriptionView = new InscriptionView(fournisseur);
        InscriptionController inscriptionController = new InscriptionController(stage, inscriptionView);
        stage.setScene(new Scene(inscriptionView, 900, 700));
    }

    /**
     * Navigue vers le menu principal pour l'utilisateur connecté.
     */
    public void navigateToMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }

    /**
     * Navigue vers le menu principal pour le fournisseur connecté.
     */
    public void navigateToMenuFournisseur() {
        MenuFournisseurView menuView = new MenuFournisseurView();
        MenuFournisseurController menuController = new MenuFournisseurController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }

    /**
     * Affiche un message d'erreur si les informations de connexion sont incorrectes.
     */
    public void invalid() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText(null);
        alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
        alert.showAndWait();
    }
}
