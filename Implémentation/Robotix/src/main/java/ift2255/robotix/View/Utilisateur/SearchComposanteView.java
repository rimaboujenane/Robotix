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
    private HashMap<Fournisseur, List<Composante>> inv;
    private Text data;

    public SearchComposanteView() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();
        loadComposantes();
        data = new Text(printComposantes());

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
        typeComboBox.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {loadComposantes(fournComboBox.getValue()); data = new Text(printComposantes());}});

        ObservableList<String> fourns = fournisseurs.getFournisseurs();
        fournComboBox = new ComboBox<>(fourns);
        fournComboBox.setPromptText("Fournisseur");
        fournComboBox.setMaxWidth(400);
        fournComboBox.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {loadComposantes(fournisseurs.getFournisseurByName(fournComboBox.getValue())); data = new Text(printComposantes());}});

        VBox formLayout = new VBox(10, typeField, typeComboBox, fournField, fournComboBox, data);
        formLayout.setStyle("-fx-background-color: #1B263B; -fx-border-radius: 5; -fx-background-radius: 5;");
        formLayout.setPadding(new Insets(10));
        formLayout.setPrefWidth(420);
        formLayout.setMaxWidth(420);
        formLayout.setAlignment(Pos.CENTER);

        VBox dataLayout = new VBox(10, data);
        ScrollPane scroll = new ScrollPane(dataLayout);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #0D1B2A;");

        this.getChildren().addAll(label, formLayout, scroll, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }
    public HashMap<Fournisseur, List<Composante>> loadComposantes() {

        inv = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            inv.put(f, composantes.chargerComposantes(f.getEmail()));
        }
        return inv;
    }
    public HashMap<Fournisseur, List<Composante>> loadComposantes( String c) {

        inv = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            List<Composante> comps = composantes.chargerComposantes(f.getEmail());
            for (Composante cc : comps) {
                if (cc.getType() == c) {
                    inv.put(f, composantes.chargerComposantes(f.getEmail()));
                    break;
                }
            }
        }
        return inv;
    }
    public HashMap<Fournisseur, List<Composante>> loadComposantes( Fournisseur f) {

        inv = new HashMap<>();
        inv.put(f, composantes.chargerComposantes(f.getEmail()));
        return inv;
    }
    public String printComposantes() {

        String name = "";
        for (Map.Entry<Fournisseur, List<Composante>> entry : inv.entrySet()) {
            name += entry.getKey().getNom() + "\n";
            for (Composante c : entry.getValue()) {
                name += "\t" + c.getNom() + " -> Type: " + c.getType() + "\n";
            }
            name += "\n";
        }
        return name;
    }

    public Button getBackButton() {
        return backButton;
    }
    public String getType() { return typeComboBox.getValue(); }
    public String getFournisseur() { return fournComboBox.getValue(); }
}