package ift2255.robotix.Controller;

import ift2255.robotix.Controller.Fournisseur.MenuFournisseurController;
import ift2255.robotix.Controller.Utilisateur.MenuController;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.GestionUtilisateurs;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import ift2255.robotix.View.InscriptionView;
import ift2255.robotix.View.LoginView;
import ift2255.robotix.View.Utilisateur.MenuView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class InscriptionController {
    private InscriptionView view;
    private Stage stage;
    private Utilisateur utilisateur;
    private Fournisseur fournisseur;

    public InscriptionController(Stage stage, InscriptionView view){
        this.stage = stage;
        this.view = view;

        this.view.getRegisterButton().setOnAction(e -> handleUtilisateurRegister());
        this.view.getLoginLabel().setOnMouseClicked(e -> login());
    }

    // Inscrit la personne
    private void handleUtilisateurRegister() {
        String nom = view.getRegisterNomField().getText();
        String prenom = view.getRegisterPrenomField().getText();
        String email = view.getRegisterEmailField().getText();
        String phone = view.getRegisterPhoneField().getText();
        String password = view.getRegisterPasswordField().getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de l'inscription");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs requis.");

            alert.showAndWait();
            return; // Sortir de la méthode si des champs sont vides
        }

        if (view.getRegisterTypeComboBox().getValue().equals("Utilisateur")) {
            this.utilisateur = new Utilisateur(nom, prenom, password, email, phone);
            GestionUtilisateurs utilisateurs = new GestionUtilisateurs();
            utilisateurs.addUtilisateur(utilisateur);
            navigateToMenu();
        } else if (view.getRegisterTypeComboBox().getValue().equals("Fournisseur")) {
            this.fournisseur = new Fournisseur(nom, prenom, password, email, phone);
            GestionFournisseurs fournisseurs = new GestionFournisseurs();
            fournisseurs.addFournisseur(fournisseur);
            navigateToMenuFournisseur();
        }
    }

    public void login(){
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(stage, loginView);
        stage.setScene(new Scene(loginView, 900, 700));
    }

    public void navigateToMenu(){
        MenuView menuView = new MenuView(utilisateur);
        MenuController menuController = new MenuController(stage, menuView, utilisateur);
        stage.setScene(new Scene(menuView, 900, 700));
    }

    public void navigateToMenuFournisseur(){
        MenuFournisseurView menuView = new MenuFournisseurView(fournisseur);
        MenuFournisseurController menuController = new MenuFournisseurController(stage, menuView, fournisseur);
        stage.setScene(new Scene(menuView, 900, 700));
    }

}
