package ift2255.robotix.View;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * La vue d'inscription pour l'application Robotix.
 *
 * Cette classe étend `VBox` et fournit une interface utilisateur pour l'inscription avec des champs pour le nom,
 * le prénom, l'email, le téléphone, le mot de passe, ainsi qu'un bouton et un label pour l'interaction de l'utilisateur.
 *
 * @see javafx.scene.control.TextField
 * @see javafx.scene.control.PasswordField
 * @see javafx.scene.control.ComboBox
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.Label
 * @see javafx.scene.layout.VBox
 */
public class InscriptionView extends VBox {

    private TextField registerNomField;
    private TextField registerPrenomField;
    private TextField registerEmailField;
    private TextField registerPhoneField;
    private PasswordField registerPasswordField;
    private TextField registerCompanieField;
    private CheckBox emailCheckbox;
    private Button registerButton;
    private Label loginLabel;

    /**
     * Constructeur de la vue d'inscription.
     * Initialise les éléments de l'interface utilisateur et les styles associés.
     *
     * @param fournisseur Indique si l'inscription est pour un fournisseur ou non.
     *                    Si true, un champ pour le nom de la compagnie est ajouté.
     */
    public InscriptionView(Boolean fournisseur) {
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

        emailCheckbox = new CheckBox("Recevoir les notifications par email");
        emailCheckbox.setStyle("-fx-text-fill: white;");

        Label registerPasswordLabel = new Label("Mot de passe:");
        registerPasswordLabel.setStyle("-fx-text-fill: white;");
        registerPasswordField = new PasswordField();

        registerButton = new Button("S'inscrire");
        registerButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");
        registerButton.prefWidthProperty().bind(registerNomField.widthProperty());

        loginLabel = new Label("Déjà un Compte? Connectez-vous!");
        loginLabel.setStyle("-fx-text-fill: white;");
        // Souligné le texte quand on passe dessus
        loginLabel.setOnMouseEntered(event -> loginLabel.setStyle("-fx-text-fill: white; -fx-underline: true;"));
        loginLabel.setOnMouseExited(event -> loginLabel.setStyle("-fx-text-fill: white;"));

        VBox registerSection = new VBox(10, registerTitle, registerNomLabel, registerNomField, registerPrenomLabel,
                registerPrenomField, registerEmailLabel, registerEmailField, registerPhoneLabel, registerPhoneField,
                registerPasswordLabel, registerPasswordField);

        if (fournisseur) {
            Label registerCompanieLabel = new Label("Nom de votre compagnie");
            registerCompanieLabel.setStyle("-fx-text-fill: white;");
            registerCompanieField = new TextField();
            registerSection.getChildren().addAll(registerCompanieLabel, registerCompanieField);
            registerButton.setUserData(true);
        }
        else {
            registerButton.setUserData(false);
        }
        registerSection.getChildren().addAll(emailCheckbox, registerButton, loginLabel);
        registerSection.setStyle("-fx-padding: 20; -fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        registerSection.setSpacing(10);
        registerSection.setPrefWidth(400);
        registerSection.setMaxWidth(400);

        this.getChildren().add(registerSection);
        this.setStyle("-fx-background: #0D1B2A; -fx-background-color: #0D1B2A;");
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Retourne le champ de texte pour le nom.
     *
     * @return Le champ de texte pour le nom.
     */
    public TextField getRegisterNomField() {
        return registerNomField;
    }

    /**
     * Retourne le champ de texte pour le prénom.
     *
     * @return Le champ de texte pour le prénom.
     */
    public TextField getRegisterPrenomField() {
        return registerPrenomField;
    }

    /**
     * Retourne le champ de texte pour l'email.
     *
     * @return Le champ de texte pour l'email.
     */
    public TextField getRegisterEmailField() {
        return registerEmailField;
    }

    /**
     * Retourne le champ de texte pour le téléphone.
     *
     * @return Le champ de texte pour le téléphone.
     */
    public TextField getRegisterPhoneField() {
        return registerPhoneField;
    }

    /**
     * Retourne le champ de texte pour le mot de passe.
     *
     * @return Le champ de texte pour le mot de passe.
     */
    public PasswordField getRegisterPasswordField() {
        return registerPasswordField;
    }

    /**
     * Retourne le bouton d'inscription.
     *
     * @return Le bouton d'inscription.
     */
    public Button getRegisterButton() {
        return registerButton;
    }

    /**
     * Retourne le label de connexion.
     *
     * @return Le label de connexion.
     */
    public Label getLoginLabel() {
        return loginLabel;
    }

    /**
     * Retourne le champ de texte pour le nom de la compagnie.
     *
     * @return Le champ de texte pour le nom de la compagnie, ou null si non applicable.
     */
    public TextField getRegisterCompanieField() {
        return registerCompanieField;
    }
}
