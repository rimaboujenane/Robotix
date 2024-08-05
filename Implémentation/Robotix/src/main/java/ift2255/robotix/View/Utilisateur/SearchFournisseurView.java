package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFournisseurView extends VBox {
    private Button backButton;
    private ComboBox<String> typeComboBox;
    private Label typeField;
    private ComboBox<String> fournComboBox;
    private Label fournField;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;
    ScrollPane scroll;

    public SearchFournisseurView() {afficherVue(new HashMap<Fournisseur, List<Composante>>()); }

    public void afficherVue(HashMap<Fournisseur, List<Composante>> i) {

        this.getChildren().clear();

        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();

        VBox dataLayout = buildCatalog(i);
        scroll = new ScrollPane(dataLayout);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #0D1B2A;");

        Label label = new Label("Recherche Fournisseur");
        label.setStyle("-fx-text-fill: white;");
        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        typeField = new Label("Rechercher fournisseur par type de composante offerte:");

        ObservableList<String> types = FXCollections.observableArrayList("CPU", "Roues", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran");
        typeComboBox = new ComboBox<>(types);
        typeComboBox.setPromptText("Type");
        typeComboBox.setMaxWidth(400);

        VBox formLayout = new VBox(10, typeField, typeComboBox, scroll);
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
    public VBox buildCatalog(List<Fournisseur> fourns) {

        VBox cat = new VBox(new Text(getType()));
        for (Fournisseur f : fourns) {
            String n = f.getPrenom() + " " + f.getNom();
            Text name = new Text(n);
            Button b = new Button("Acheter");
            b.setOnMouseClicked(e -> {
                NotifService.getInstance().sendNotif(
                        "La composante " + getType() + ", vendu par " + n + " a été ajoutée à ton inventaire."
                );
                Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Succès!", ButtonType.OK);
                success.showAndWait();
            });
            HBox subCat = new HBox();
            subCat.setPadding(new Insets(10));
            subCat.setSpacing(10);
            subCat.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            subCat.getChildren().addAll(name, b);
            cat.getChildren().add(subCat);
        }
        return cat;
    }
    public VBox buildCatalog(HashMap<Fournisseur, List<Composante>> i) {

        VBox cat = new VBox();
        for (Map.Entry<Fournisseur, List<Composante>> entry : i.entrySet()) {
            String n = entry.getKey().getPrenom() + " " + entry.getKey().getNom();
            Text name = new Text(n);
            cat.getChildren().add(name);
            for (Composante c : entry.getValue()) {
                HBox subCat = new HBox();
                subCat.setPadding(new Insets(10));
                subCat.setSpacing(10);
                subCat.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                Text comp = new Text("Composante: " + c.getNom() + "\tType: " + c.getType());
                Button b = new Button("Acheter");
                b.setOnMouseClicked(e -> {
                    NotifService.getInstance().sendNotif(
                            "La composante " + c.getNom() + ", vendu par " + n + " a été ajoutée à ton inventaire."
                    );
                    Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Succès!", ButtonType.OK);
                    success.showAndWait();

                });
                subCat.getChildren().addAll(comp, b);
                cat.getChildren().add(subCat);
            }
        }
        return cat;
    }
    public Button getBackButton() { return backButton; }
    public ComboBox<String> getTypeComboBox() { return typeComboBox; }
    public String getType() { return typeComboBox.getValue(); }
    public Fournisseur getFournisseur() { return fournisseurs.getFournisseurByName(fournComboBox.getValue()); }
}