package ift2255.robotix.Controller;

import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.GestionUtilisateurs;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.InscriptionView;
import ift2255.robotix.View.LoginView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue d'inscription, gérant les actions liées à l'inscription de nouveaux utilisateurs et fournisseurs.
 */
public class InscriptionController {
    private InscriptionView view; // Vue associée au contrôleur
    private Stage stage; // Fenêtre principale de l'application
    private Utilisateur utilisateur; // Instance de l'utilisateur en cours d'inscription
    private Fournisseur fournisseur; // Instance du fournisseur en cours d'inscription

    /**
     * Constructeur pour initialiser le contrôleur avec la vue et la fenêtre principale.
     *
     * @param stage La fenêtre principale de l'application
     * @param view La vue d'inscription
     */
    public InscriptionController(Stage stage, InscriptionView view) {
        this.stage = stage;
        this.view = view;

        // Définir l'action pour le bouton d'inscription
        this.view.getRegisterButton().setOnAction(e -> handleUtilisateurRegister());

        // Définir l'action pour l'étiquette de connexion
        this.view.getLoginLabel().setOnMouseClicked(e -> login());
    }

    /**
     * Gère l'inscription d'un nouvel utilisateur ou fournisseur en fonction du type sélectionné.
     */
    private void handleUtilisateurRegister() {
        String nom = view.getRegisterNomField().getText();
        String prenom = view.getRegisterPrenomField().getText();
        String email = view.getRegisterEmailField().getText();
        String phone = view.getRegisterPhoneField().getText();
        String password = view.getRegisterPasswordField().getText();

        // Vérifier que tous les champs sont remplis
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de l'inscription");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs requis.");
            alert.showAndWait();
            return; // Sortir de la méthode si des champs sont vides
        }

        // Créer un utilisateur ou un fournisseur en fonction du type sélectionné
        if (view.getRegisterTypeComboBox().getValue().equals("Utilisateur")) {
            this.utilisateur = new Utilisateur(nom, prenom, password, email, phone);
            GestionUtilisateurs utilisateurs = new GestionUtilisateurs();
            utilisateurs.addUtilisateur(utilisateur);
            navigateToMenu(); // Naviguer vers le menu utilisateur
        } else if (view.getRegisterTypeComboBox().getValue().equals("Fournisseur")) {
            this.fournisseur = new Fournisseur(nom, prenom, password, email, phone);
            GestionFournisseurs fournisseurs = new GestionFournisseurs();
            fournisseurs.addFournisseur(fournisseur);
            navigateToMenu(); // Naviguer vers le menu fournisseur
        }
    }

    /**
     * Ouvre la vue de connexion pour permettre à un utilisateur existant de se connecter.
     */
    public void login() {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(stage, loginView);
        stage.setScene(new Scene(loginView, 900, 700));
    }

    /**
     * Navigue vers le menu principal de l'utilisateur inscrit.
     */
    public void navigateToMenu() {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(stage, loginView);
        stage.setScene(new Scene(loginView, 900, 700));
    }

}
