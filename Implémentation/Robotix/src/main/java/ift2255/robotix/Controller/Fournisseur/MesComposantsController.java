package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionComposantes;
import ift2255.robotix.Modeles.RegisterFournisseur;
import ift2255.robotix.View.Fournisseur.MesComposantsView;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;

/**
 * Contrôleur pour gérer les actions dans la vue des composants d'un fournisseur.
 * Permet d'afficher, modifier et supprimer les composants associés au fournisseur courant.
 */
public class MesComposantsController {
    private Stage stage;
    private MesComposantsView view;
    private GestionComposantes gestionComposantes;
    private Fournisseur fournisseur;
    private ObservableList<Composante> composantsObservableList;

    /**
     * Constructeur pour initialiser le contrôleur des composants du fournisseur.
     *
     * @param stage La scène principale de l'application.
     * @param view La vue des composants du fournisseur.
     * @param gestionComposantes La gestion des composants.
     */
    public MesComposantsController(Stage stage, MesComposantsView view, GestionComposantes gestionComposantes) {
        this.stage = stage;
        this.view = view;
        this.gestionComposantes = gestionComposantes;
        this.fournisseur = RegisterFournisseur.getInstance().getFournisseur();

        // Initialize the ListView and ObservableList
        composantsObservableList = FXCollections.observableArrayList();
        view.setComposantesList(composantsObservableList);

        // Set up button actions
        initializeButtonActions();

        // Set up ListView selection listener
        view.getListView().getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                view.remplirChamps(newItem);
            }
        });

        // Display components for the current supplier
        chargerEtAfficherComposants(fournisseur.getEmail());
    }

    /**
     * Initialise les actions des boutons dans la vue des composants.
     */
    private void initializeButtonActions() {
        view.getModifierButton().setOnAction(e -> modifierComposante());
        view.getSupprimerButton().setOnAction(e -> supprimerComposante());
        view.getRetourButton().setOnAction(e -> retournerMenu());
    }

    /**
     * Affiche les composants dans la liste observable.
     *
     * @param composants Liste des composants à afficher.
     */
    public void afficherComposants(List<Composante> composants) {
        composantsObservableList.setAll(composants);
    }

    /**
     * Modifie la composante sélectionnée dans la vue.
     */
    private void modifierComposante() {
        Composante selected = view.getListView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                // Récupérer les nouvelles valeurs des champs de texte
                String nouveauNom = view.getNomField().getText();
                String nouveauType = view.getTypeComboBox().getValue();
                String nouvelleDescription = view.getDescriptionField().getText();

                // Convertir le prix avec gestion des virgules et points
                String prixText = view.getPrixField().getText().replace(',', '.');
                double nouveauPrix = Double.parseDouble(prixText);

                // Vérifier la longueur de la description
                if (nouvelleDescription.length() > 30) {
                    showError("La description ne peut pas dépasser 30 caractères.");
                    return;
                }

                // Vérifier si le prix est négatif
                if (nouveauPrix < 0) {
                    showError("Le prix ne peut pas être négatif.");
                    return;
                }

                // Mettre à jour les valeurs de la composante sélectionnée
                selected.setNom(nouveauNom);
                selected.setType(nouveauType);
                selected.setDescription(nouvelleDescription);
                selected.setPrix(nouveauPrix);

                // Mettre à jour la composante dans la gestion des composantes
                gestionComposantes.updateComposante(selected);

                // Mettre à jour la liste affichée
                afficherComposants(gestionComposantes.chargerComposantes(fournisseur.getEmail())); // Refresh the list

                // Afficher une confirmation de la modification
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification");
                alert.setHeaderText(null);
                alert.setContentText("Modification enregistrée!");
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
                showError("Erreur lors de la modification du composant: " + e.getMessage());
            }
        }
    }

    /**
     * Affiche un message d'erreur dans une alerte.
     *
     * @param message Le message d'erreur à afficher.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Supprime la composante sélectionnée dans la vue.
     */
    private void supprimerComposante() {
        Composante selected = view.getListView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            gestionComposantes.supprimerComposante(selected.getId(), selected.getFournisseurEmail());
            composantsObservableList.remove(selected);
        }
        // Afficher une confirmation de la suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Suppression effectuée!");
        alert.show();
        view.getNomField().clear();
        view.getTypeComboBox().getSelectionModel().clearSelection();
        view.getDescriptionField().clear();
        view.getPrixField().clear();
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
     * Charge et affiche les composants pour le fournisseur donné.
     *
     * @param fournisseurEmail L'email du fournisseur dont les composants doivent être chargés.
     */
    private void chargerEtAfficherComposants(String fournisseurEmail) {
        List<Composante> composants = gestionComposantes.chargerComposantes(fournisseurEmail);
        afficherComposants(composants);
        printComposantesList(composants);
    }

    /**
     * Imprime la liste des composants dans la console.
     *
     * @param composantes La liste des composants à imprimer.
     */
    private void printComposantesList(List<Composante> composantes) {
        if (composantes.isEmpty()) {
            System.out.println("Aucune composante trouvée pour le fournisseur : " + fournisseur.getEmail());
        } else {
            System.out.println("Liste des composants pour le fournisseur : " + fournisseur.getEmail());
            composantes.forEach(System.out::println);
        }
    }
}
