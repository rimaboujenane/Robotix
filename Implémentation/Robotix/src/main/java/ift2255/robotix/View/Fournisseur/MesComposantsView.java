package ift2255.robotix.View.Fournisseur;

import ift2255.robotix.Modeles.Composante;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MesComposantsView extends VBox {
    private ListView<Composante> listView;
    private TextField nomField;
    private ComboBox<String> typeComboBox;
    private TextField descriptionField;
    private TextField prixField;
    private Button modifierButton;
    private Button supprimerButton;
    private Button retourButton;

    public MesComposantsView() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);

        // Initialisation de la ListView
        listView = new ListView<>();
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

                    Label idLabel = new Label("ID: " + item.getId());
                    idLabel.setFont(new Font("Arial", 14));
                    Label nomLabel = new Label("Nom: " + item.getNom());
                    Label typeLabel = new Label("Type: " + item.getType());
                    Label descriptionLabel = new Label("Description: " + item.getDescription());
                    Label prixLabel = new Label("Prix: " + item.getPrix());

                    cellContent.getChildren().addAll(idLabel, nomLabel, typeLabel, descriptionLabel, prixLabel);
                    setGraphic(cellContent);
                }
            }
        });

        // Création des champs de texte et boutons
        nomField = new TextField();
        nomField.setPromptText("Nom");

        typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("CPU", "Roues", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran");
        typeComboBox.setPromptText("Type");
        typeComboBox.setMaxWidth(Double.MAX_VALUE);
        HBox typeBox = new HBox(typeComboBox);
        typeBox.setPadding(new Insets(0, 0, 10, 0));
        HBox.setHgrow(typeComboBox, Priority.ALWAYS);

        descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        prixField = new TextField();
        prixField.setPromptText("Prix");

        modifierButton = new Button("Modifier");
        modifierButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");

        supprimerButton = new Button("Supprimer");
        supprimerButton.setStyle("-fx-background-color: #C8102E; -fx-text-fill: white;");

        retourButton = new Button("Retour");
        retourButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        // Ajout des éléments à la VBox
        getChildren().addAll(listView, nomField, typeComboBox, descriptionField, prixField, modifierButton, supprimerButton, retourButton);
        setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    public ListView<Composante> getListView() {
        return listView;
    }

    public TextField getNomField() {
        return nomField;
    }

    public ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public TextField getDescriptionField() {
        return descriptionField;
    }

    public TextField getPrixField() {
        return prixField;
    }

    public Button getModifierButton() {
        return modifierButton;
    }

    public Button getSupprimerButton() {
        return supprimerButton;
    }

    public Button getRetourButton() {
        return retourButton;
    }

    public void setComposantesList(ObservableList<Composante> composantesList) {
        listView.setItems(composantesList);
    }

    public void remplirChamps(Composante composante) {
        nomField.setText(composante.getNom());
        typeComboBox.setValue(composante.getType());
        descriptionField.setText(composante.getDescription());
        prixField.setText(String.valueOf(composante.getPrix()));
    }
}


