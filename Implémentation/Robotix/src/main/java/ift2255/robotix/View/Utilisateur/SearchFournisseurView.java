package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Controller.Utilisateur.SearchFournisseurController;
import ift2255.robotix.Modeles.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vue pour rechercher les fournisseurs et afficher leurs composantes disponibles.
 * Permet de filtrer les fournisseurs par type de composante et de visualiser les détails.
 */
public class SearchFournisseurView extends VBox {
    private Button backButton;
    private ComboBox<String> typeComboBox;
    private Label typeField;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;
    private SearchFournisseurController controller;
    private ScrollPane scroll;

    /**
     * Constructeur par défaut qui initialise la vue en appelant la méthode {@link #afficherVue(HashMap)}.
     */
    public SearchFournisseurView() {
        afficherVue(new HashMap<Fournisseur, List<Composante>>());
    }

    /**
     * Définit le contrôleur associé à cette vue.
     *
     * @param controller Le contrôleur à associer.
     */
    public void setController(SearchFournisseurController controller) {
        this.controller = controller;
    }

    /**
     * Affiche la vue avec les données fournies.
     * Configure les éléments de l'interface utilisateur, y compris le bouton de retour, le champ de sélection du type de composante,
     * et les fournisseurs.
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

    /**
     * Construit et retourne la vue du catalogue de composantes pour les fournisseurs donnés.
     *
     * @param i Une map associant chaque fournisseur à une liste de composantes.
     * @return Un {@link VBox} contenant la vue du catalogue des fournisseurs et leurs composantes.
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
                Text comp = new Text(c.getNom() + "\tType: " + c.getType() + " Prix:" + Double.toString(c.getPrix()));
                Button b = new Button("Acheter");
                b.setOnMouseClicked(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirmer l'achat de la composante ?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            if (controller != null) {
                                controller.handlePurchase(c, entry.getKey());
                            }
                            NotifService.getInstance().sendNotif(
                                    "La composante " + c.getNom() + ", vendue par " + n + " a été ajoutée à ton inventaire."
                            );
                            Alert success = new Alert(Alert.AlertType.INFORMATION, "Composante achetée avec succès!", ButtonType.OK);
                            success.showAndWait();
                        }
                    });
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
     * Retourne le type de composante sélectionné dans la boîte de sélection.
     *
     * @return Le type de composante sélectionné.
     */
    public String getType() {
        return typeComboBox.getValue();
    }
}
