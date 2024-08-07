package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Controller.LoginController;
import ift2255.robotix.Controller.Utilisateur.NotifMenuController;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionComposantes;
import ift2255.robotix.Modeles.RegisterFournisseur;
import ift2255.robotix.View.Fournisseur.*;
import ift2255.robotix.View.LoginView;
import ift2255.robotix.View.Utilisateur.NotifMenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contrôleur pour le menu du fournisseur. Gère la navigation entre les différentes vues disponibles pour le fournisseur.
 */
public class MenuFournisseurController {
    private MenuFournisseurView view;
    private Stage stage;
    private GestionComposantes gestionComposantes;
    private Fournisseur fournisseur = RegisterFournisseur.getInstance().getFournisseur();

    /**
     * Constructeur pour initialiser le contrôleur du menu du fournisseur.
     *
     * @param stage La scène principale de l'application.
     * @param view  La vue du menu du fournisseur.
     */
    public MenuFournisseurController(Stage stage, MenuFournisseurView view) {
        this.view = view;
        this.stage = stage;
        this.gestionComposantes = new GestionComposantes();
        this.view.getProfilMenuButton().setOnAction(e -> navigateToProfil());
        this.view.getNotifFournisseurButton().setOnAction(e -> navigateToNotifFournisseur());
        this.view.getMesComposantsMenuButton().setOnAction(e -> navigateToMesComposants());
        this.view.getEnregistrerComposantsMenuButton().setOnAction(e -> navigateToEnregistrer());
        this.view.getLogoutMenuButton().setOnAction(e -> returnToLoginMenu());
        this.view.getExitMenuButton().setOnAction(e -> System.exit(0));
    }

    /**
     * Navigue vers la vue de profil du fournisseur.
     */
    private void navigateToProfil() {
        ProfilFournisseurView profilFournisseurView = new ProfilFournisseurView();
        ProfilFournisseurController profilFournisseurController = new ProfilFournisseurController(stage, profilFournisseurView);
        stage.setScene(new Scene(profilFournisseurView, 900, 700));
    }

    /**
     * Navigue vers la vue de notifications du fournisseur.
     */
    private void navigateToNotifFournisseur() {
        NotifFournisseurView notifFournisseurView = new NotifFournisseurView(fournisseur.getEmail());
        NotifFournisseurController notifFournisseurController = new NotifFournisseurController(stage, notifFournisseurView);
        stage.setScene(new Scene(notifFournisseurView, 900, 700));
    }

    /**
     * Navigue vers la vue de gestion des composants.
     */
    private void navigateToMesComposants() {
        MesComposantsView mesComposantsView = new MesComposantsView();
        MesComposantsController mesComposantsController = new MesComposantsController(stage, mesComposantsView, gestionComposantes);
        stage.setScene(new Scene(mesComposantsView, 900, 700));
    }

    /**
     * Navigue vers la vue d'enregistrement des composants.
     */
    private void navigateToEnregistrer() {
        EnregistrerComposantsView enregistrerComposantsView = new EnregistrerComposantsView();
        EnregistrerComposantsController enregistrerComposantsController = new EnregistrerComposantsController(stage, enregistrerComposantsView, gestionComposantes);
        stage.setScene(new Scene(enregistrerComposantsView, 900, 700));
    }

    /**
     * Retourne au menu de connexion.
     */
    private void returnToLoginMenu() {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(stage, loginView);
        stage.setScene(new Scene(loginView, 900, 700));
    }
}
