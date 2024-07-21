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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class LoginController {
    private LoginView view;
    private Stage stage;
    private Utilisateur utilisateur;
    private Fournisseur fournisseur;

    public LoginController(Stage stage,LoginView view) {
        this.stage = stage;
        this.view = view;

        this.view.getLoginButton().setOnAction(e -> handleLogin(view.getLoginTypeComboBox()));
        this.view.getRegisterLabel().setOnMouseClicked(e -> inscription());
    }

    // Verification des identifiants
    private void handleLogin(ComboBox<String> type) {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();

        GestionUtilisateurs utilisateurs = new GestionUtilisateurs(); // recupere tous les Utilisateur dans le CSV
        GestionFournisseurs fournisseurs = new GestionFournisseurs();

        if ("Utilisateur".equals(type.getValue()) && utilisateurs.isValidUser(username,password)){
            this.utilisateur = utilisateurs.getUtilisateur();
            navigateToMenu();
        }

        else if ("Fournisseur".equals(type.getValue()) && fournisseurs.isValidUser(username,password)) {
            this.fournisseur = fournisseurs.getFournisseur();
            navigateToMenuFournisseur();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
            alert.showAndWait();
        }
    }

    public void inscription(){
        InscriptionView inscriptionView = new InscriptionView();
        InscriptionController inscriptionController = new InscriptionController(stage, inscriptionView);
        stage.setScene(new Scene(inscriptionView, 900, 700));
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
