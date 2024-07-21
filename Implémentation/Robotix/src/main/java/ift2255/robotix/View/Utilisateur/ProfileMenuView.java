package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.Utilisateur;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProfileMenuView extends VBox {

    private Button backButton;
    private Button saveButton;
    private TextField nameField;
    private TextField prenomField;
    private TextField emailField;
    private TextField phoneField;
    private PasswordField passwordField;

    public ProfileMenuView(Utilisateur utilisateur) {
        Label titleLabel = new Label("Mon profil");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        // Initialiser les champs de texte avec les informations de l'utilisateur
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
        passwordField = new PasswordField(); // Vous pouvez choisir si vous voulez afficher le mot de passe initial ou non
        passwordField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        // Boutons
        saveButton = new Button("Enregistrer les modifications");
        saveButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");

        VBox profileSection = new VBox(10, nameLabel, nameField, prenomLabel, prenomField, emailLabel, emailField,
                phoneLabel, phoneField, passwordLabel, passwordField, saveButton);
        profileSection.setStyle("-fx-padding: 20; -fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        profileSection.setSpacing(10);
        profileSection.setPrefWidth(600);
        profileSection.setMaxWidth(600);


        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(titleLabel, profileSection, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public String getUserName() {
        return nameField.getText();
    }

    public String getUserPrenom(){
        return prenomField.getText();
    }

    public String getUserEmail() {
        return emailField.getText();
    }

    public String getUserPhone() {
        return phoneField.getText();
    }

    public String getUserPassword() {
        return passwordField.getText();
    }

}
