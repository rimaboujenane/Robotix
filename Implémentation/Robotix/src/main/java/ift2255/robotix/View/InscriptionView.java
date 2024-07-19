package ift2255.robotix.View;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class InscriptionView extends VBox {
    private TextField registerNomField;
    private TextField registerPrenomField;
    private TextField registerEmailField;
    private TextField registerPhoneField;
    private PasswordField registerPasswordField;
    private ComboBox<String> registerTypeComboBox;
    private Button registerButton;
    private Button loginButton;

    public InscriptionView(){
        // Titre pour la section d'inscription
        Label registerTitle = new Label("S'inscrire");
        registerTitle.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        // Section d'inscription
        Label registerNomLabel = new Label("Nom:");
        registerNomLabel.setStyle("-fx-text-fill: white;");
        registerNomField = new TextField();

        Label registerPrenomLabel = new Label("Prénom:");
        registerPrenomLabel.setStyle("-fx-text-fill: white;");
        registerPrenomField = new TextField();

        Label registerEmailLabel = new Label("Email:");
        registerEmailLabel.setStyle("-fx-text-fill: white;");
        registerEmailField = new TextField();
        registerEmailField.setPromptText("exemple@gmail.com");

        Label registerPhoneLabel = new Label("Téléphone:");
        registerPhoneLabel.setStyle("-fx-text-fill: white;");
        registerPhoneField = new TextField();
        registerPhoneField.setPromptText("123-456-7890");

        Label registerPasswordLabel = new Label("Mot de passe:");
        registerPasswordLabel.setStyle("-fx-text-fill: white;");
        registerPasswordField = new PasswordField();

        // ComboBox pour le type de compte
        Label registerTypeLabel = new Label("Type de Compte:");
        registerTypeLabel.setStyle("-fx-text-fill: white;");
        registerTypeComboBox = new ComboBox<>();
        registerTypeComboBox.getItems().addAll("Utilisateur", "Fournisseur");
        registerTypeComboBox.getSelectionModel().selectFirst(); // Sélectionne par défaut le premier élément

        registerButton = new Button("S'incrire");
        registerButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");

        loginButton = new Button("Se connecter");
        loginButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");


        VBox registerSection = new VBox(10, registerTitle, registerNomLabel, registerNomField, registerPrenomLabel,
                registerPrenomField, registerEmailLabel, registerEmailField, registerPhoneLabel, registerPhoneField,
                registerPasswordLabel, registerPasswordField, registerTypeLabel, registerTypeComboBox, registerButton, loginButton);
        registerSection.setStyle("-fx-padding: 20; -fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        registerSection.setSpacing(10);
        registerSection.setPrefWidth(400);
        registerSection.setMaxWidth(400);

        this.getChildren().add(registerSection);
        this.setStyle("-fx-background: #0D1B2A; -fx-background-color: #0D1B2A;");
        this.setAlignment(Pos.CENTER);
    }

    public TextField getRegisterNomField() {
        return registerNomField;
    }

    public TextField getRegisterPrenomField() {
        return registerPrenomField;
    }

    public TextField getRegisterEmailField() {
        return registerEmailField;
    }

    public TextField getRegisterPhoneField() {
        return registerPhoneField;
    }

    public PasswordField getRegisterPasswordField() {
        return registerPasswordField;
    }

    public ComboBox<String> getRegisterTypeComboBox() {
        return registerTypeComboBox;
    }

    public Button getRegisterButton() {
        return registerButton;
    }
    public Button getLoginButton() {
        return loginButton;
    }
}
