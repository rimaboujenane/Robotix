package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.GestionUtilisateurs;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.ProfileMenuView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue du menu de profil, gérant les actions liées à la modification du profil utilisateur.
 */
public class ProfileMenuController {
    private ProfileMenuView profileMenuView; // Vue associée au contrôleur
    private Stage stage; // Fenêtre principale de l'application
    private Utilisateur utilisateur; // Utilisateur actuel
    private Utilisateur utilisateurModifie; // Utilisateur avec les informations modifiées

    /**
     * Constructeur pour initialiser le contrôleur avec la vue, la fenêtre principale et l'utilisateur.
     *
     * @param stage La fenêtre principale de l'application
     * @param profileMenuView La vue du menu de profil
     * @param utilisateur L'utilisateur actuel dont le profil est affiché
     */
    public ProfileMenuController(Stage stage, ProfileMenuView profileMenuView, Utilisateur utilisateur) {
        this.stage = stage;
        this.profileMenuView = profileMenuView;
        this.utilisateur = utilisateur;

        // Définir l'action pour le bouton de sauvegarde
        this.profileMenuView.getSaveButton().setOnAction(e -> updateProfile());

        // Définir l'action pour le bouton de retour
        this.profileMenuView.getBackButton().setOnAction(e -> goBackMenu());
    }

    /**
     * Navigue vers le menu principal de l'utilisateur.
     */
    private void goBackMenu() {
        MenuView menuView = new MenuView(utilisateur);
        MenuController menuController = new MenuController(stage, menuView, utilisateur);
        stage.setScene(new Scene(menuView, 900, 700));
    }

    /**
     * Met à jour les informations du profil utilisateur.
     * Vérifie si le mot de passe a été modifié, puis enregistre les modifications.
     */
    private void updateProfile() {
        String name = profileMenuView.getUserName();
        String prenom = profileMenuView.getUserPrenom();
        String email = profileMenuView.getUserEmail();
        String phone = profileMenuView.getUserPhone();
        String password = profileMenuView.getUserPassword();

        // Si le mot de passe est vide, conserver l'ancien mot de passe
        if (password.isEmpty()) {
            String existingPassword = utilisateur.getPassword();
            utilisateurModifie = new Utilisateur(name, prenom, existingPassword, email, phone);
        } else {
            utilisateurModifie = new Utilisateur(name, prenom, password, email, phone);
        }

        // Mettre à jour les informations de l'utilisateur
        GestionUtilisateurs utilisateurs = new GestionUtilisateurs();
        utilisateurs.updateUtilisateur(utilisateurModifie);

        // Mettre à jour l'utilisateur actuel avec les nouvelles informations
        this.utilisateur = utilisateurModifie;

        // Afficher une confirmation de la modification
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification");
        alert.setHeaderText(null);
        alert.setContentText("Modification enregistrée!");
        alert.show();
    }
}
