package ift2255.robotix.View.Utilisateur;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Vue pour ajouter un robot dans l'application.
 * Cette classe étend {@link VBox} et fournit une interface utilisateur pour entrer les détails d'un nouveau robot
 * et pour le soumettre ou revenir à la vue précédente.
 */
public class AjouterRobotView extends VBox {
    private TextField numeroSerieField;
    private TextField nomField;
    private TextField typeField;
    private TextField positionField;
    private TextField vitesseField;
    private TextField niveauBatterieField;
    private TextField consommationCPUField;
    private TextField consommationMemoireField;
    private Button ajouterButton;
    private Button retourButton;
    private Label errorMessage;

    /**
     * Constructeur pour initialiser la vue d'ajout de robot.
     * Configure les champs de texte, les étiquettes, les boutons et leur mise en page.
     */
    public AjouterRobotView() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Ajouter un Robot");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        Label numeroSerieLabel = new Label("Numéro de Série:");
        numeroSerieLabel.setStyle("-fx-text-fill: white;");
        numeroSerieField = new TextField();
        numeroSerieField.setPromptText("Numéro de Série");
        numeroSerieField.setMaxWidth(700);

        Label nomLabel = new Label("Nom:");
        nomLabel.setStyle("-fx-text-fill: white;");
        nomField = new TextField();
        nomField.setPromptText("Nom");
        nomField.setMaxWidth(700);

        Label typeLabel = new Label("Type:");
        typeLabel.setStyle("-fx-text-fill: white;");
        typeField = new TextField();
        typeField.setPromptText("Type");
        typeField.setMaxWidth(700);

        Label positionLabel = new Label("Position:");
        positionLabel.setStyle("-fx-text-fill: white;");
        positionField = new TextField();
        positionField.setPromptText("Position");
        positionField.setMaxWidth(700);

        Label vitesseLabel = new Label("Vitesse (m/s):");
        vitesseLabel.setStyle("-fx-text-fill: white;");
        vitesseField = new TextField();
        vitesseField.setPromptText("00.00");
        vitesseField.setMaxWidth(700);

        Label niveauBatterieLabel = new Label("Niveau de Batterie (%):");
        niveauBatterieLabel.setStyle("-fx-text-fill: white;");
        niveauBatterieField = new TextField();
        niveauBatterieField.setPromptText("00");
        niveauBatterieField.setMaxWidth(700);

        Label consommationCPULabel = new Label("Consommation CPU (%):");
        consommationCPULabel.setStyle("-fx-text-fill: white;");
        consommationCPUField = new TextField();
        consommationCPUField.setPromptText("00.00");
        consommationCPUField.setMaxWidth(700);

        Label consommationMemoireLabel = new Label("Consommation Mémoire (MB):");
        consommationMemoireLabel.setStyle("-fx-text-fill: white;");
        consommationMemoireField = new TextField();
        consommationMemoireField.setPromptText("00.00");
        consommationMemoireField.setMaxWidth(700);

        ajouterButton = new Button("Ajouter");
        ajouterButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");

        retourButton = new Button("Retour");
        retourButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        errorMessage = new Label();
        errorMessage.setTextFill(javafx.scene.paint.Color.RED);

        VBox formLayout = new VBox(10, numeroSerieLabel, numeroSerieField, nomLabel, nomField, typeLabel, typeField, positionLabel, positionField, vitesseLabel, vitesseField, niveauBatterieLabel, niveauBatterieField, consommationCPULabel, consommationCPUField, consommationMemoireLabel, consommationMemoireField);
        formLayout.setStyle("-fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        formLayout.setPadding(new Insets(10));
        formLayout.setPrefWidth(420);
        formLayout.setMaxWidth(420);
        formLayout.setAlignment(Pos.CENTER);

        VBox buttonLayout = new VBox(10, ajouterButton, retourButton);
        buttonLayout.setPadding(new Insets(10));
        buttonLayout.setAlignment(Pos.CENTER);

        getChildren().addAll(titleLabel, formLayout, buttonLayout);
        setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    /**
     * Retourne le champ de texte pour le numéro de série du robot.
     *
     * @return Le champ de texte pour le numéro de série.
     */
    public TextField getNumeroSerieField() {
        return numeroSerieField;
    }

    /**
     * Retourne le champ de texte pour le nom du robot.
     *
     * @return Le champ de texte pour le nom du robot.
     */
    public TextField getNomField() {
        return nomField;
    }

    /**
     * Retourne le champ de texte pour le type du robot.
     *
     * @return Le champ de texte pour le type du robot.
     */
    public TextField getTypeField() {
        return typeField;
    }

    /**
     * Retourne le champ de texte pour la position du robot.
     *
     * @return Le champ de texte pour la position du robot.
     */
    public TextField getPositionField() {
        return positionField;
    }

    /**
     * Retourne le champ de texte pour la vitesse du robot.
     *
     * @return Le champ de texte pour la vitesse du robot.
     */
    public TextField getVitesseField() {
        return vitesseField;
    }

    /**
     * Retourne le champ de texte pour le niveau de batterie du robot.
     *
     * @return Le champ de texte pour le niveau de batterie.
     */
    public TextField getNiveauBatterieField() {
        return niveauBatterieField;
    }

    /**
     * Retourne le champ de texte pour la consommation CPU du robot.
     *
     * @return Le champ de texte pour la consommation CPU.
     */
    public TextField getConsommationCPUField() {
        return consommationCPUField;
    }

    /**
     * Retourne le champ de texte pour la consommation de mémoire du robot.
     *
     * @return Le champ de texte pour la consommation de mémoire.
     */
    public TextField getConsommationMemoireField() {
        return consommationMemoireField;
    }

    /**
     * Retourne le bouton pour ajouter un robot.
     *
     * @return Le bouton pour ajouter un robot.
     */
    public Button getAjouterButton() {
        return ajouterButton;
    }

    /**
     * Retourne le bouton pour revenir à la vue précédente.
     *
     * @return Le bouton de retour.
     */
    public Button getRetourButton() {
        return retourButton;
    }
}