package ift2255.robotix.View.Fournisseur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

/**
 * Vue pour l'enregistrement d'une composante. Permet à l'utilisateur de saisir les détails d'une composante
 * et de l'enregistrer.
 */
public class EnregistrerComposantsView extends VBox {
    private TextField nomField;
    private ComboBox<String> typeComboBox;
    private TextField descriptionField;
    private TextField prixField;
    private Button enregistrerButton;
    private Button retourButton;
    private Label errorMessage;

    /**
     * Constructeur pour initialiser la vue d'enregistrement des composantes.
     */
    public EnregistrerComposantsView() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Enregistrer une Composante");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        Label nomLabel = new Label("Nom:");
        nomLabel.setStyle("-fx-text-fill: white;");
        nomField = new TextField();
        nomField.setPromptText("Nom");
        nomField.setMaxWidth(400);

        Label typeLabel = new Label("Type:");
        typeLabel.setStyle("-fx-text-fill: white;");
        typeComboBox = new ComboBox<>();
        ObservableList<String> types = FXCollections.observableArrayList("CPU", "Roues", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran");
        typeComboBox.setItems(types);
        typeComboBox.setPromptText("Type");
        typeComboBox.setMaxWidth(400);

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-text-fill: white;");
        descriptionField = new TextField();
        descriptionField.setPromptText("Description ne doit pas dépasser 30 caractères.");
        descriptionField.setMaxWidth(400);
        descriptionField.setTextFormatter(new TextFormatter<>(change -> {
            // Limiter le texte à 30 caractères
            if (change.getControlNewText().length() > 30) {
                return null; // Ne pas appliquer le changement
            }
            return change; // Appliquer le changement
        }));

        Label prixLabel = new Label("Prix en $:");
        prixLabel.setStyle("-fx-text-fill: white;");
        prixField = new TextField();
        prixField.setPromptText("00.00");
        prixField.setMaxWidth(400);

        enregistrerButton = new Button("Enregistrer");
        enregistrerButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");

        retourButton = new Button("Retour");
        retourButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        errorMessage = new Label();
        errorMessage.setTextFill(javafx.scene.paint.Color.RED);

        VBox formLayout = new VBox(10, nomLabel, nomField, typeLabel, typeComboBox, descriptionLabel, descriptionField, prixLabel, prixField);
        formLayout.setStyle("-fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        formLayout.setPadding(new Insets(10));
        formLayout.setPrefWidth(420);
        formLayout.setMaxWidth(420);
        formLayout.setAlignment(Pos.CENTER);

        VBox buttonLayout = new VBox(10, enregistrerButton, retourButton);
        buttonLayout.setPadding(new Insets(10));
        buttonLayout.setAlignment(Pos.CENTER);

        getChildren().addAll(titleLabel, formLayout, buttonLayout);
        setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    /**
     * Obtient le champ de texte pour le nom de la composante.
     *
     * @return Le champ de texte du nom.
     */
    public TextField getNomField() {
        return nomField;
    }

    /**
     * Obtient la liste déroulante pour le type de composante.
     *
     * @return La liste déroulante du type.
     */
    public ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    /**
     * Obtient le champ de texte pour la description de la composante.
     *
     * @return Le champ de texte de la description.
     */
    public TextField getDescriptionField() {
        return descriptionField;
    }

    /**
     * Obtient le champ de texte pour le prix de la composante.
     *
     * @return Le champ de texte du prix.
     */
    public TextField getPrixField() {
        return prixField;
    }

    /**
     * Obtient le bouton pour enregistrer la composante.
     *
     * @return Le bouton d'enregistrement.
     */
    public Button getEnregistrerButton() {
        return enregistrerButton;
    }

    /**
     * Obtient le bouton pour retourner au menu précédent.
     *
     * @return Le bouton de retour.
     */
    public Button getRetourButton() {
        return retourButton;
    }
}