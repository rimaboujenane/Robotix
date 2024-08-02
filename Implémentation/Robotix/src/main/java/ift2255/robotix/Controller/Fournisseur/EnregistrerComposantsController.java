package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.GestionComposantes;
import ift2255.robotix.Modeles.RegisterFournisseur;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.View.Fournisseur.EnregistrerComposantsView;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Contrôleur pour la vue d'enregistrement des composantes. Gère les interactions de l'utilisateur
 * avec la vue d'enregistrement.
 */
public class EnregistrerComposantsController {
    private Stage stage;
    private EnregistrerComposantsView view;
    private GestionComposantes gestionComposantes;
    private Fournisseur fournisseur = RegisterFournisseur.getInstance().getFournisseur();

    /**
     * Constructeur pour initialiser le contrôleur.
     *
     * @param stage Le stage principal de l'application.
     * @param view La vue d'enregistrement des composantes.
     * @param gestionComposantes La gestion des composantes.
     */
    public EnregistrerComposantsController(Stage stage, EnregistrerComposantsView view, GestionComposantes gestionComposantes) {
        this.stage = stage;
        this.view = view;
        this.gestionComposantes = gestionComposantes;

        // Ajoute un gestionnaire d'événements pour le bouton d'enregistrement
        this.view.getEnregistrerButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enregistrerComposante();
            }
        });

        // Ajoute un gestionnaire d'événements pour le bouton de retour
        this.view.getRetourButton().setOnAction(e -> retournerMenu());
    }

    /**
     * Enregistre une composante en fonction des informations saisies par l'utilisateur.
     */
    private void enregistrerComposante() {
        String nom = view.getNomField().getText();
        String type = view.getTypeComboBox().getValue(); // Récupère la valeur sélectionnée dans ComboBox
        String description = view.getDescriptionField().getText();
        String prixText = view.getPrixField().getText();

        // Vérification de la longueur de la description
        if (description.length() > 30) {
            showError("La description ne peut pas dépasser 30 caractères.");
            return;
        }

        try {
            double prix = Double.parseDouble(prixText);
            // Vérifier si le prix est négatif
            if (prix < 0) {
                showError("Le prix ne peut pas être négatif.");
                return;
            }

            String fournisseurEmail = fournisseur.getEmail();

            Composante composante = new Composante(0, nom, type, description, prix, fournisseurEmail);
            gestionComposantes.ajouterComposante(composante);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Enregistrement");
            alert.setHeaderText(null);
            alert.setContentText("Composante enregistrée!");
            alert.show();

            // Optionnellement, vider les champs
            view.getNomField().clear();
            view.getTypeComboBox().getSelectionModel().clearSelection();
            view.getDescriptionField().clear();
            view.getPrixField().clear();
        } catch (NumberFormatException e) {
            showError("Le prix doit être un nombre valide. Assurez-vous d'utiliser un point (.) pour les décimales.");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Erreur lors de l'enregistrement du composant: " + e.getMessage());
        }
    }

    /**
     * Retourne au menu principal du fournisseur.
     */
    private void retournerMenu() {
        MenuFournisseurView menuFournisseurView = new MenuFournisseurView();
        MenuFournisseurController menuFournisseurController = new MenuFournisseurController(stage, menuFournisseurView);
        stage.setScene(new Scene(menuFournisseurView, 900, 700));
    }

    /**
     * Affiche un message d'erreur dans une boîte de dialogue.
     *
     * @param message Le message d'erreur à afficher.
     */
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
