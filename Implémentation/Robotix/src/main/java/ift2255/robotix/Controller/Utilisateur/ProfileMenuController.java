package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.GestionUtilisateurs;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.ProfileMenuView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ProfileMenuController {
    private ProfileMenuView profileMenuView;
    private Stage stage;
    private Utilisateur utilisateur;
    private Utilisateur utilisateurModifie;

    public ProfileMenuController(Stage stage, ProfileMenuView profileMenuView, Utilisateur utilisateur) {
        this.stage = stage;
        this.profileMenuView = profileMenuView;
        this.utilisateur = utilisateur;

        this.profileMenuView.getSaveButton().setOnAction(e -> updateProfile());

        this.profileMenuView.getBackButton().setOnAction(e -> goBackMenu());
    }

    private void goBackMenu() {
        MenuView menuView = new MenuView(utilisateur);
        MenuController menuController = new MenuController(stage, menuView, utilisateur);
        stage.setScene(new Scene(menuView, 900, 700));
    }

    private void updateProfile(){
        String name = profileMenuView.getUserName();
        String prenom = profileMenuView.getUserPrenom();
        String email = profileMenuView.getUserEmail();
        String phone = profileMenuView.getUserPhone();
        String password = profileMenuView.getUserPassword();

        // Si le mot de passe a ete modifie
        if (password.isEmpty()) {

            String existingPassword = utilisateur.getPassword();
            utilisateurModifie = new Utilisateur(name, prenom, existingPassword, email, phone);
        } else {
            utilisateurModifie = new Utilisateur(name, prenom, password, email, phone);
        }
        GestionUtilisateurs utilisateurs = new GestionUtilisateurs();
        utilisateurs.updateUtilisateur(utilisateurModifie);

        this.utilisateur = utilisateurModifie;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification");
        alert.setHeaderText(null);
        alert.setContentText("Modification enregistrée!");
        alert.show();
    }
}
