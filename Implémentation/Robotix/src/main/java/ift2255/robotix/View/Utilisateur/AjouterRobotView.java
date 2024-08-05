package ift2255.robotix.View.Utilisateur;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Vue pour ajouter un robot dans l'application.
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

    public TextField getNumeroSerieField() {
        return numeroSerieField;
    }

    public TextField getNomField() {
        return nomField;
    }

    public TextField getTypeField() {
        return typeField;
    }

    public TextField getPositionField() {
        return positionField;
    }

    public TextField getVitesseField() {
        return vitesseField;
    }

    public TextField getNiveauBatterieField() {
        return niveauBatterieField;
    }

    public TextField getConsommationCPUField() {
        return consommationCPUField;
    }

    public TextField getConsommationMemoireField() {
        return consommationMemoireField;
    }

    
    public Button getAjouterButton() {
        return ajouterButton;
    }

    public Button getRetourButton() {
        return retourButton;
    }
}
