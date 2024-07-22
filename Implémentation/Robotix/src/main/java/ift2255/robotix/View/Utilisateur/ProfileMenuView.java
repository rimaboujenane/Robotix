package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.Utilisateur;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Vue du menu de profil utilisateur pour l'application Robotix.
 *
 * Cette classe étend `VBox` et fournit une interface utilisateur pour afficher et modifier les informations du profil
 * utilisateur. Elle inclut des champs pour le nom, le prénom, l'email, le téléphone et le mot de passe, ainsi que des
 * boutons pour enregistrer les modifications ou revenir au menu précédent.
 *
 * @see javafx.scene.control.TextField
 * @see javafx.scene.control.PasswordField
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.Label
 * @see javafx.scene.layout.VBox
 */
public class ProfileMenuView extends VBox {

    private Button backButton;
    private Button saveButton;
    private TextField nameField;
    private TextField prenomField;
    private TextField emailField;
    private TextField phoneField;
    private PasswordField passwordField;

    /**
     * Constructeur de la vue du menu de profil.
     * Initialise les éléments de l'interface utilisateur avec les informations de l'utilisateur spécifié.
     *
     * @param utilisateur L'utilisateur dont les informations seront affichées dans la vue.
     */
    public ProfileMenuView(Utilisateur utilisateur) {
        // Titre de la vue
        Label titleLabel = new Label("Mon profil");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        // Initialisation des champs de texte avec les informations de l'utilisateur
        Label nameLabel = new Label("Nom");
        nameLabel.setStyle("-fx-text-fill: white");
        nameField = new TextField(utilisateur.getNom());
        nameField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label prenomLabel = new Label("Prénom");
        prenomLabel.setStyle("-fx-text-fill: white");
        prenomField = new TextField(utilisateur.getPrenom());
        prenomField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label emailLabel = new Label("Email");
        emailLabel.setStyle("-fx-text-fill: white");
        emailField = new TextField(utilisateur.getEmail());
        emailField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label phoneLabel = new Label("Téléphone");
        phoneLabel.setStyle("-fx-text-fill: white");
        phoneField = new TextField(utilisateur.getTelephone());
        phoneField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label passwordLabel = new Label("Mot de passe");
        passwordLabel.setStyle("-fx-text-fill: white");
        passwordField = new PasswordField(); // Option d'afficher ou non le mot de passe initial
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

        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(titleLabel, profileSection, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    /**
     * Retourne le bouton de retour.
     *
     * @return Le bouton de retour.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Retourne le bouton d'enregistrement des modifications.
     *
     * @return Le bouton d'enregistrement des modifications.
     */
    public Button getSaveButton() {
        return saveButton;
    }

    /**
     * Retourne le nom saisi dans le champ de texte.
     *
     * @return Le nom saisi.
     */
    public String getUserName() {
        return nameField.getText();
    }

    /**
     * Retourne le prénom saisi dans le champ de texte.
     *
     * @return Le prénom saisi.
     */
    public String getUserPrenom() {
        return prenomField.getText();
    }

    /**
     * Retourne l'email saisi dans le champ de texte.
     *
     * @return L'email saisi.
     */
    public String getUserEmail() {
        return emailField.getText();
    }

    /**
     * Retourne le téléphone saisi dans le champ de texte.
     *
     * @return Le téléphone saisi.
     */
    public String getUserPhone() {
        return phoneField.getText();
    }

    /**
     * Retourne le mot de passe saisi dans le champ de texte.
     *
     * @return Le mot de passe saisi.
     */
    public String getUserPassword() {
        return passwordField.getText();
    }
}
