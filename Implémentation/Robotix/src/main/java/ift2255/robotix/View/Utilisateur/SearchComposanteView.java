package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.GestionComposantes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.control.ScrollPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchComposanteView extends VBox {
    private Button backButton;
    private ComboBox<String> typeComboBox;
    private Label typeField;
    private ComboBox<String> fournComboBox;
    private Label fournField;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;
    ScrollPane scroll;

    public SearchComposanteView() {afficherVue(new Text()); }

    public void afficherVue(Text data) {

        this.getChildren().clear();

        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();

        VBox dataLayout = new VBox(10, data);
        scroll = new ScrollPane(dataLayout);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #0D1B2A;");

        Label label = new Label("Recherche Composante");
        label.setStyle("-fx-text-fill: white;");
        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        typeField = new Label("Rechercher par type de composante:");
        fournField = new Label("Rechercher par fournisseur:");

        ObservableList<String> types = FXCollections.observableArrayList("CPU", "Roues", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran");
        typeComboBox = new ComboBox<>(types);
        typeComboBox.setPromptText("Type");
        typeComboBox.setMaxWidth(400);

        ObservableList<String> fourns = fournisseurs.getFournisseurs();
        fournComboBox = new ComboBox<>(fourns);
        fournComboBox.setPromptText("Fournisseur");
        fournComboBox.setMaxWidth(400);


        VBox formLayout = new VBox(10, typeField, typeComboBox, fournField, fournComboBox, scroll);
        formLayout.setStyle("-fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        formLayout.setPadding(new Insets(10));
        formLayout.setPrefWidth(420);
        formLayout.setMaxWidth(420);
        formLayout.setAlignment(Pos.CENTER);

        this.getChildren().addAll(label, formLayout, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }
    public void updateData(Text data) {

        this.getChildren().remove(scroll);
        VBox dataLayout = new VBox(10, data);
        scroll = new ScrollPane(dataLayout);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #0D1B2A;");
        this.getChildren().add(scroll);

    }

    public Button getBackButton() { return backButton; }
    public ComboBox<String> getTypeComboBox() { return typeComboBox; }
    public ComboBox<String> getFournComboBox() { return fournComboBox; }
    public String getType() { return typeComboBox.getValue(); }
    public Fournisseur getFournisseur() { return fournisseurs.getFournisseurByName(fournComboBox.getValue()); }
}