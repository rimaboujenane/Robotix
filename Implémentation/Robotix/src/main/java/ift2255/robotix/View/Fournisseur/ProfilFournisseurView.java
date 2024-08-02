package ift2255.robotix.View.Fournisseur;

import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.RegisterFournisseur;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

/**
 * Vue pour afficher et modifier le profil d'un fournisseur.
 * Permet à l'utilisateur de voir et de mettre à jour ses informations personnelles.
 */
public class ProfilFournisseurView extends VBox {
    private Button backButton;
    private Button saveButton;
    private TextField nameField;
    private TextField prenomField;
    private TextField emailField;
    private TextField phoneField;
    private PasswordField passwordField;
    private Fournisseur fournisseur = RegisterFournisseur.getInstance().getFournisseur();

    /**
     * Constructeur pour initialiser la vue du profil du fournisseur.
     * Configure les éléments d'interface utilisateur et les styles.
     */
    public ProfilFournisseurView() {
        Label titleLabel = new Label("Mon profil");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        // Initialiser les champs de texte avec les informations de l'utilisateur
        Label nameLabel = new Label("Nom:");
        nameLabel.setStyle("-fx-text-fill: white");
        nameField = new TextField(fournisseur.getNom());
        nameField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label prenomLabel = new Label("Prénom:");
        prenomLabel.setStyle("-fx-text-fill: white");
        prenomField = new TextField(fournisseur.getPrenom());
        prenomField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: white");
        emailField = new TextField(fournisseur.getEmail());
        emailField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label phoneLabel = new Label("Téléphone:");
        phoneLabel.setStyle("-fx-text-fill: white");
        phoneField = new TextField(fournisseur.getTelephone());
        phoneField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label passwordLabel = new Label("Mot de passe:");
        passwordLabel.setStyle("-fx-text-fill: white");
        passwordField = new PasswordField(); // Vous pouvez choisir si vous voulez afficher le mot de passe initial ou non
        passwordField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        // Boutons
        saveButton = new Button("Enregistrer les modifications");
        saveButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");

        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        VBox profileSection = new VBox(10, nameLabel, nameField, prenomLabel, prenomField, emailLabel, emailField,
                phoneLabel, phoneField, passwordLabel, passwordField, saveButton);
        profileSection.setStyle("-fx-padding: 20; -fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        profileSection.setSpacing(10);
        profileSection.setPrefWidth(600);
        profileSection.setMaxWidth(600);
        profileSection.setAlignment(Pos.CENTER); // Center the elements in profileSection

        // Center the entire VBox
        this.setAlignment(Pos.CENTER);
        this.setSpacing(40);
        this.getChildren().addAll(titleLabel, profileSection, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    /**
     * Obtient le bouton de retour.
     *
     * @return Le bouton de retour.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Obtient le bouton d'enregistrement des modifications.
     *
     * @return Le bouton d'enregistrement.
     */
    public Button getSaveButton() {
        return saveButton;
    }

    /**
     * Obtient le texte du champ de nom.
     *
     * @return Le texte du champ de nom.
     */
    public String getUserName() {
        return nameField.getText();
    }

    /**
     * Obtient le texte du champ de prénom.
     *
     * @return Le texte du champ de prénom.
     */
    public String getUserPrenom() {
        return prenomField.getText();
    }

    /**
     * Obtient le texte du champ d'email.
     *
     * @return Le texte du champ d'email.
     */
    public String getUserEmail() {
        return emailField.getText();
    }

    /**
     * Obtient le texte du champ de téléphone.
     *
     * @return Le texte du champ de téléphone.
     */
    public String getUserPhone() {
        return phoneField.getText();
    }

    /**
     * Obtient le texte du champ de mot de passe.
     *
     * @return Le texte du champ de mot de passe.
     */
    public String getUserPassword() {
        return passwordField.getText();
    }
}
