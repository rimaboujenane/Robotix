package ift2255.robotix.View.Fournisseur;

import ift2255.robotix.Modeles.Composante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MesComposantsView extends VBox {
    private TableView<Composante> tableView;
    private TextField nomField;
    private TextField typeField;
    private TextField descriptionField;
    private TextField prixField;
    private Button modifierButton;
    private Button supprimerButton;
    private Button retourButton;

    public MesComposantsView() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        tableView = new TableView<>();
        TableColumn<Composante, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Composante, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Composante, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Composante, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Composante, Integer> prixColumn = new TableColumn<>("Prix");
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix")); // Assurez-vous que ce nom correspond à getPrix()

        tableView.getColumns().addAll(idColumn, nomColumn, typeColumn, descriptionColumn, prixColumn);

        nomField = new TextField();
        nomField.setPromptText("Nom");

        typeField = new TextField();
        typeField.setPromptText("Type");

        descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        prixField = new TextField();
        prixField.setPromptText("Prix");

        modifierButton = new Button("Modifier");
        modifierButton.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");

        supprimerButton = new Button("Supprimer");
        supprimerButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        retourButton = new Button("Retour");
        retourButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        getChildren().addAll(tableView, nomField, typeField, descriptionField, prixField, modifierButton, supprimerButton, retourButton);
        setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    public TableView<Composante> getTableView() {
        return tableView;
    }

    public TextField getNomField() {
        return nomField;
    }

    public TextField getTypeField() {
        return typeField;
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
        tableView.setItems(composantesList);
    }
}
