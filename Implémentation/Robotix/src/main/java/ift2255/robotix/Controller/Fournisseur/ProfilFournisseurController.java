package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import ift2255.robotix.View.Fournisseur.ProfilFournisseurView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ProfilFournisseurController {
    private ProfilFournisseurView profilFournisseurView;
    private Stage stage;
    private Fournisseur fournisseur;
    private Fournisseur fournisseurModifie;

    public ProfilFournisseurController(Stage stage, ProfilFournisseurView profilFournisseurView, Fournisseur fournisseur) {
        this.stage = stage;
        this.profilFournisseurView = profilFournisseurView;
        this.fournisseur = fournisseur;

        this.profilFournisseurView.getSaveButton().setOnAction(e -> updateProfile());

        this.profilFournisseurView.getBackButton().setOnAction(e -> goBackMenu());
    }

    private void goBackMenu() {
        MenuFournisseurView menuFournisseurView = new MenuFournisseurView(fournisseur);
        MenuFournisseurController menuFournisseurController = new MenuFournisseurController(stage, menuFournisseurView,fournisseur);
        stage.setScene(new Scene(menuFournisseurView, 900, 700));
    }

    private void updateProfile(){
        String name = profilFournisseurView.getUserName();
        String prenom = profilFournisseurView.getUserPrenom();
        String email = profilFournisseurView.getUserEmail();
        String phone = profilFournisseurView.getUserPhone();
        String password = profilFournisseurView.getUserPassword();

        // Si le mot de passe a ete modifie
        if (password.isEmpty()) {

            String existingPassword = fournisseur.getPassword();
            fournisseurModifie = new Fournisseur(name, prenom, existingPassword, email, phone);
        } else {
            fournisseurModifie = new Fournisseur(name, prenom, password, email, phone);
        }
        GestionFournisseurs gestionFournisseurs = new GestionFournisseurs();
        gestionFournisseurs.updateFournisseur(fournisseurModifie);

        this.fournisseur = fournisseurModifie;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification");
        alert.setHeaderText(null);
        alert.setContentText("Modification enregistrée!");
        alert.show();
    }
}
