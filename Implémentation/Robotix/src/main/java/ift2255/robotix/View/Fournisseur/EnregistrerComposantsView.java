package ift2255.robotix.View.Fournisseur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

/**
 * Vue pour l'enregistrement d'une composante. Permet à l'utilisateur de saisir les détails d'une composante
 * et de l'enregistrer.
 */
public class EnregistrerComposantsView extends VBox {
    private TextField nomField;
    private ComboBox<String> typeComboBox;
    private TextField descriptionField;
    private TextField prixField;
    private Button enregistrerButton;
    private Button retourButton;
    private Label errorMessage;

    /**
     * Constructeur pour initialiser la vue d'enregistrement des composantes.
     */
    public EnregistrerComposantsView() {
        // Code d'initialisation
    }

    /**
     * Obtient le champ de texte pour le nom de la composante.
     *
     * @return Le champ de texte du nom.
     */
    public TextField getNomField() {
        return nomField;
    }

    /**
     * Obtient la liste déroulante pour le type de composante.
     *
     * @return La liste déroulante du type.
     */
    public ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    /**
     * Obtient le champ de texte pour la description de la composante.
     *
     * @return Le champ de texte de la description.
     */
    public TextField getDescriptionField() {
        return descriptionField;
    }

    /**
     * Obtient le champ de texte pour le prix de la composante.
     *
     * @return Le champ de texte du prix.
     */
    public TextField getPrixField() {
        return prixField;
    }

    /**
     * Obtient le bouton pour enregistrer la composante.
     *
     * @return Le bouton d'enregistrement.
     */
    public Button getEnregistrerButton() {
        return enregistrerButton;
    }

    /**
     * Obtient le bouton pour retourner au menu précédent.
     *
     * @return Le bouton de retour.
     */
    public Button getRetourButton() {
        return retourButton;
    }
}
