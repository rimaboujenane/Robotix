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
import javafx.scene.text.Text;

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
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");

        // Création du titre
        Text title = new Text("Mes Composants");
        title.setFont(Font.font("Arial", 24));
        title.setFill(Color.WHITE);

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
        nomField.setMaxWidth(600);

        typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("CPU", "Roues", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran");
        typeComboBox.setPromptText("Type");
        typeComboBox.setMaxWidth(600);
        typeComboBox.setStyle("-fx-text-fill: white; -fx-border-color: #1F2A38; -fx-border-width: 1px;");

        descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        descriptionField.setMaxWidth(600);

        prixField = new TextField();
        prixField.setPromptText("Prix");
        prixField.setMaxWidth(600);

        modifierButton = new Button("Modifier");
        modifierButton.setMaxWidth(300);
        styleButton(modifierButton, "#0466C8", "#ffffff");

        supprimerButton = new Button("Supprimer");
        supprimerButton.setMaxWidth(300);
        styleButton(supprimerButton, "#C8102E", "#ffffff");

        retourButton = new Button("Retour");
        retourButton.setMaxWidth(300);
        styleButton(retourButton, "#1B263B", "#ffffff");

        // Organisation des éléments
        VBox formLayout = new VBox(10, nomField, typeComboBox, descriptionField, prixField);
        formLayout.setPadding(new Insets(10));
        formLayout.setAlignment(Pos.CENTER);

        VBox buttonLayout = new VBox(10, modifierButton, supprimerButton, retourButton);
        buttonLayout.setPadding(new Insets(10));
        buttonLayout.setAlignment(Pos.CENTER);

        getChildren().addAll(title, listView, formLayout, buttonLayout);
    }

    private void styleButton(Button button, String bgColor, String textColor) {
        button.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;", bgColor, textColor));
        button.setPrefHeight(40);
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
