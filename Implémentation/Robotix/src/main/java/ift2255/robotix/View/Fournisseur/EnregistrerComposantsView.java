// EnregistrerComposantsView.java
package ift2255.robotix.View.Fournisseur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class EnregistrerComposantsView extends VBox {
    private TextField nomField;
    private ComboBox<String> typeComboBox;
    private TextField descriptionField;
    private TextField prixField;
    private Button enregistrerButton;
    private Button retourButton;

    public EnregistrerComposantsView() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Enregistrer une Composante");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

        nomField = new TextField();
        nomField.setPromptText("Nom");

        typeComboBox = new ComboBox<>();
        ObservableList<String> types = FXCollections.observableArrayList("CPU", "Roues", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran");
        typeComboBox.setItems(types);
        typeComboBox.setPromptText("Type");
        typeComboBox.setMaxWidth(Double.MAX_VALUE);
        HBox typeBox = new HBox(typeComboBox);
        typeBox.setPadding(new Insets(0, 0, 10, 0));
        HBox.setHgrow(typeComboBox, Priority.ALWAYS);

        descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        prixField = new TextField();
        prixField.setPromptText("Prix");

        enregistrerButton = new Button("Enregistrer");
        enregistrerButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");

        retourButton = new Button("Retour");
        retourButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        getChildren().addAll(titleLabel, nomField, typeComboBox, descriptionField, prixField, enregistrerButton, retourButton);
        setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
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

    public Button getEnregistrerButton() {
        return enregistrerButton;
    }

    public Button getRetourButton() {
        return retourButton;
    }
}
