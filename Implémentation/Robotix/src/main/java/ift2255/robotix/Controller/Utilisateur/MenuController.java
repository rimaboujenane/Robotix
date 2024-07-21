package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Controller.LoginController;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.*;
import ift2255.robotix.View.Utilisateur.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue du menu principal de l'utilisateur, gérant la navigation vers différentes sections de l'application.
 */
public class MenuController {
    private MenuView view; // Vue associée au contrôleur
    private Stage stage; // Fenêtre principale de l'application
    private Utilisateur utilisateur; // Utilisateur actuel

    /**
     * Constructeur pour initialiser le contrôleur avec la vue, la fenêtre principale et l'utilisateur.
     *
     * @param stage La fenêtre principale de l'application
     * @param view La vue du menu principal
     * @param utilisateur L'utilisateur actuel
     */
    public MenuController(Stage stage, MenuView view, Utilisateur utilisateur) {
        this.stage = stage;
        this.view = view;
        this.utilisateur = utilisateur;

        // Définir l'action pour chaque bouton du menu
        this.view.getRobotMenuButton().setOnAction(e -> navigateToRobotMenu());
        this.view.getNotifMenuButton().setOnAction(e -> navigateToNotifMenu());
        this.view.getProfileMenuButton().setOnAction(e -> navigateToProfileMenu());
        this.view.getActiviteMenuButton().setOnAction(e -> navigateToActiviteMenu());
        this.view.getLogoutMenuButton().setOnAction(e -> returnToLoginMenu());
        this.view.getExitMenuButton().setOnAction(e -> System.exit(0)); // Quitter l'application
    }

    /**
     * Navigue vers le menu des robots.
     */
    private void navigateToRobotMenu() {
        RobotMenuView robotMenuView = new RobotMenuView();
        RobotMenuController robotMenuController = new RobotMenuController(stage, robotMenuView, utilisateur);
        stage.setScene(new Scene(robotMenuView, 900, 700));
    }

    /**
     * Navigue vers le menu des notifications.
     */
    private void navigateToNotifMenu() {
        NotifMenuView notifMenuView = new NotifMenuView();
        NotifMenuController notifMenuController = new NotifMenuController(stage, notifMenuView, utilisateur);
        stage.setScene(new Scene(notifMenuView, 900, 700));
    }

    /**
     * Navigue vers le menu de profil utilisateur.
     */
    private void navigateToProfileMenu() {
        ProfileMenuView profileMenuView = new ProfileMenuView(utilisateur);
        ProfileMenuController profileMenuController = new ProfileMenuController(stage, profileMenuView, utilisateur);
        stage.setScene(new Scene(profileMenuView, 900, 700));
    }

    /**
     * Retourne à l'écran de connexion.
     */
    private void returnToLoginMenu() {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(stage, loginView);
        stage.setScene(new Scene(loginView, 900, 700));
    }

    /**
     * Navigue vers le menu des activités.
     */
    private void navigateToActiviteMenu() {
        ActiviteMenuView activiteMenuView = new ActiviteMenuView(utilisateur);
        ActiviteMenuController activiteMenuController = new ActiviteMenuController(stage, activiteMenuView, utilisateur);
        stage.setScene(new Scene(activiteMenuView, 900, 700));
    }
}
