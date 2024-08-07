package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Controller.Utilisateur.SearchComposanteController;
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
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vue pour rechercher les composantes et afficher les détails disponibles chez les fournisseurs.
 * Permet de filtrer les composantes par type et par fournisseur.
 */
public class SearchComposanteView extends VBox {
    private Button backButton;
    private ComboBox<String> typeComboBox;
    private Label typeField;
    private ComboBox<String> fournComboBox;
    private Label fournField;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;
    private SearchComposanteController controller;
    private ScrollPane scroll;

    /**
     * Constructeur par défaut qui initialise la vue en appelant la méthode {@link #afficherVue(HashMap)}.
     */
    public SearchComposanteView() {
        afficherVue(new HashMap<Fournisseur, List<Composante>>());
    }

    /**
     * Définit le contrôleur associé à cette vue.
     *
     * @param controller Le contrôleur à associer.
     */
    public void setController(SearchComposanteController controller) {
        this.controller = controller;
    }

    /**
     * Affiche la vue avec les données fournies.
     * Configure les éléments de l'interface utilisateur, y compris le bouton de retour, les champs de sélection des types de composantes
     * et des fournisseurs, et les composantes disponibles.
     *
     * @param i Une map associant chaque fournisseur à une liste de composantes.
     */
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

    /**
     * Met à jour les données affichées dans la vue avec le texte fourni.
     *
     * @param data Le texte à afficher.
     */
    public void updateData(Text data) {
        this.getChildren().remove(scroll);
        VBox dataLayout = new VBox(10, data);
        scroll = new ScrollPane(dataLayout);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #0D1B2A;");
        this.getChildren().add(scroll);
    }

    /**
     * Construit et retourne la vue du catalogue des composantes disponibles chez les fournisseurs donnés.
     *
     * @param i Une map associant chaque fournisseur à une liste de composantes.
     * @return Un {@link VBox} contenant la vue du catalogue des composantes.
     */
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
                Text comp = new Text(c.getNom() + "\tType: " + c.getType() + " Prix: " + Double.toString(c.getPrix()) + "$");
                Button b = new Button("Acheter");
                b.setOnMouseClicked(e -> {
                    if (controller != null) {
                        controller.acheterComposante(entry.getKey(), c);
                    }
                    NotifService.getInstance().sendNotif(
                            "La composante " + c.getNom() + ", vendue par " + n + " a été ajoutée à ton inventaire."
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

    /**
     * Retourne le bouton de retour de la vue.
     *
     * @return Le bouton de retour.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Retourne la boîte de sélection pour les types de composantes.
     *
     * @return La boîte de sélection des types de composantes.
     */
    public ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    /**
     * Retourne la boîte de sélection pour les fournisseurs.
     *
     * @return La boîte de sélection des fournisseurs.
     */
    public ComboBox<String> getFournComboBox() {
        return fournComboBox;
    }

    /**
     * Retourne le type de composante sélectionné dans la boîte de sélection.
     *
     * @return Le type de composante sélectionné.
     */
    public String getType() {
        return typeComboBox.getValue();
    }

    /**
     * Retourne le fournisseur sélectionné dans la boîte de sélection.
     *
     * @return Le fournisseur sélectionné, ou null si aucun fournisseur n'est sélectionné.
     */
    public Fournisseur getFournisseur() {
        return fournisseurs.getFournisseurByName(fournComboBox.getValue());
    }
}
