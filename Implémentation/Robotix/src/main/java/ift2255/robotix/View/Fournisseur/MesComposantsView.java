package ift2255.robotix.View.Fournisseur;

import ift2255.robotix.Modeles.Composante;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Vue pour afficher et gérer les composants enregistrés.
 */
public class MesComposantsView extends VBox {
    private ListView<Composante> listView;
    private TextField nomField;
    private ComboBox<String> typeComboBox;
    private TextField descriptionField;
    private TextField prixField;
    private Button modifierButton;
    private Button supprimerButton;
    private Button retourButton;
    private Label errorMessage;

    /**
     * Constructeur pour initialiser la vue.
     */
    public MesComposantsView() {
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");

        // Création du titre
        Label titleLabel = new Label("Mes Composants");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

        // Initialisation de la ListView
        listView = new ListView<>();
        listView.setPrefWidth(600);
        listView.setMaxWidth(600);
        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Composante item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox cellContent = new VBox();
                    cellContent.setPadding(new Insets(10));
                    cellContent.setStyle("-fx-background-color: #778DA9; -fx-text-fill: white;");

                    Label nomLabel = new Label("Nom: " + item.getNom());
                    Label typeLabel = new Label("Type: " + item.getType());
                    Label descriptionLabel = new Label("Description: " + item.getDescription());
                    Label prixLabel = new Label("Prix: " + item.getPrix());

                    cellContent.getChildren().addAll(nomLabel, typeLabel, descriptionLabel, prixLabel);
                    setGraphic(cellContent);
                }
            }
        });

        // Création des champs de texte et boutons
        Label nomLabel = new Label("Nom:");
        nomLabel.setStyle("-fx-text-fill: white;");
        nomField = new TextField();
        nomField.setPromptText("Nom");
        nomField.setMaxWidth(400);

        Label typeLabel = new Label("Type:");
        typeLabel.setStyle("-fx-text-fill: white;");
        typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("CPU", "Roues", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran");
        typeComboBox.setPromptText("Type");
        typeComboBox.setMaxWidth(400);
        typeComboBox.setStyle("-fx-text-fill: white; -fx-border-color: #1F2A38; -fx-border-width: 1px;");

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-text-fill: white;");
        descriptionField = new TextField();
        descriptionField.setPromptText("Description ne doit pas dépasser 30 caractères.");
        descriptionField.setMaxWidth(400);
        descriptionField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().length() > 30) {
                return null; // Ne pas appliquer le changement si la longueur dépasse 30 caractères
            }
            return change;
        }));

        Label prixLabel = new Label("Prix en $:");
        prixLabel.setStyle("-fx-text-fill: white;");
        prixField = new TextField();
        prixField.setPromptText("00.00");
        prixField.setMaxWidth(400);

        // Initialisation des boutons
        modifierButton = new Button("Modifier");
        styleButton(modifierButton, "#0466C8", "#ffffff");

        supprimerButton = new Button("Supprimer");
        styleButton(supprimerButton, "#C8102E", "#ffffff");

        retourButton = new Button("Retour");
        styleButton(retourButton, "#1B263B", "#ffffff");

        errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);

        // Organisation des éléments
        VBox formLayout = new VBox(10, nomLabel, nomField, typeLabel, typeComboBox, descriptionLabel, descriptionField, prixLabel, prixField);
        formLayout.setStyle("-fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        formLayout.setPadding(new Insets(10));
        formLayout.setPrefWidth(420);
        formLayout.setMaxWidth(420);
        formLayout.setAlignment(Pos.CENTER);

        HBox buttonLayout = new HBox(10, modifierButton, supprimerButton, retourButton);
        buttonLayout.setPadding(new Insets(10));
        buttonLayout.setAlignment(Pos.CENTER);

        // Ajout des éléments à la vue
        getChildren().addAll(titleLabel, listView, formLayout, buttonLayout);
    }

    /**
     * Applique un style à un bouton.
     *
     * @param button Le bouton à styler.
     * @param bgColor La couleur de fond du bouton.
     * @param textColor La couleur du texte du bouton.
     */
    private void styleButton(Button button, String bgColor, String textColor) {
        button.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;", bgColor, textColor));
        button.setPrefHeight(40);
        button.setMaxWidth(200);
    }

    /**
     * Obtient la ListView des composants.
     *
     * @return La ListView des composants.
     */
    public ListView<Composante> getListView() {
        return listView;
    }

    /**
     * Obtient le champ de texte pour le nom.
     *
     * @return Le champ de texte pour le nom.
     */
    public TextField getNomField() {
        return nomField;
    }

    /**
     * Obtient le ComboBox pour le type.
     *
     * @return Le ComboBox pour le type.
     */
    public ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    /**
     * Obtient le champ de texte pour la description.
     *
     * @return Le champ de texte pour la description.
     */
    public TextField getDescriptionField() {
        return descriptionField;
    }

    /**
     * Obtient le champ de texte pour le prix.
     *
     * @return Le champ de texte pour le prix.
     */
    public TextField getPrixField() {
        return prixField;
    }

    /**
     * Obtient le bouton de modification.
     *
     * @return Le bouton de modification.
     */
    public Button getModifierButton() {
        return modifierButton;
    }

    /**
     * Obtient le bouton de suppression.
     *
     * @return Le bouton de suppression.
     */
    public Button getSupprimerButton() {
        return supprimerButton;
    }

    /**
     * Obtient le bouton de retour.
     *
     * @return Le bouton de retour.
     */
    public Button getRetourButton() {
        return retourButton;
    }

    /**
     * Définit la liste des composants dans la ListView.
     *
     * @param composantesList La liste des composants.
     */
    public void setComposantesList(ObservableList<Composante> composantesList) {
        listView.setItems(composantesList);
    }

    /**
     * Remplit les champs de texte avec les informations d'un composant.
     *
     * @param composante Le composant dont les informations doivent être affichées.
     */
    public void remplirChamps(Composante composante) {
        nomField.setText(composante.getNom());
        typeComboBox.setValue(composante.getType());
        descriptionField.setText(composante.getDescription());
        prixField.setText(String.valueOf(composante.getPrix()));
    }
}
