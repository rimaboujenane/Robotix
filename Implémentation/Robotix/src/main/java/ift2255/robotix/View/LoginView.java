package ift2255.robotix.View;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginView extends VBox {
    private TextField usernameField;
    private PasswordField passwordField;
    private ComboBox<String> loginTypeComboBox;
    private Button loginButton;
    private Label registerLabel;

    public LoginView() {

        // Titre principale
        Label titleLabel = new Label("Bienvenue sur Robotix!");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        // Titre pour la section de connexion
        Label loginTitle = new Label("Se connecter");
        loginTitle.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        // Section de connexion
        Label usernameLabel = new Label("Email:");
        usernameLabel.setStyle("-fx-text-fill: white;");
        usernameField = new TextField();

        Label passwordLabel = new Label("Mot de passe:");
        passwordLabel.setStyle("-fx-text-fill: white;");
        passwordField = new PasswordField();

        // ComboBox pour le type de compte
        Label loginTypeLabel = new Label("Type de Compte:");
        loginTypeLabel.setStyle("-fx-text-fill: white;");
        loginTypeComboBox = new ComboBox<>();
        loginTypeComboBox.getItems().addAll("Utilisateur", "Fournisseur");
        loginTypeComboBox.getSelectionModel().selectFirst(); // Sélectionne par défaut le premier élément

        loginButton = new Button("Connexion");
        loginButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white; ");
        loginButton.prefWidthProperty().bind(usernameField.widthProperty());

        registerLabel = new Label("Première fois sur Robotix? Créé un compte dès maintenant!");
        registerLabel.setStyle("-fx-text-fill: white;");
        // Souligné le texte quand on passe dessus
        registerLabel.setOnMouseEntered(event -> registerLabel.setStyle("-fx-text-fill: white; -fx-underline: true;"));
        registerLabel.setOnMouseExited(event -> registerLabel.setStyle("-fx-text-fill: white;"));

        VBox loginSection = new VBox(10, loginTitle, usernameLabel, usernameField, passwordLabel, passwordField,
                loginTypeLabel, loginTypeComboBox, loginButton, registerLabel);
        loginSection.setStyle("-fx-padding: 20; -fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        loginSection.setSpacing(10);
        loginSection.setPrefWidth(400);
        loginSection.setMaxWidth(400);

        // Ajout des sections au conteneur principal
        this.getChildren().addAll(titleLabel, loginSection);
        this.setStyle("-fx-background: #0D1B2A; -fx-background-color: #0D1B2A;");
        this.setAlignment(Pos.CENTER);
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public ComboBox<String> getLoginTypeComboBox() {
        return loginTypeComboBox;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Label getRegisterLabel() {
        return registerLabel;
    }
}