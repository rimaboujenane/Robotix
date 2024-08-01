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
import javafx.stage.Stage;

import java.util.List;

public class MesComposantsController {
    private Stage stage;
    private MesComposantsView view;
    private GestionComposantes gestionComposantes;
    private Fournisseur fournisseur;
    private ObservableList<Composante> composantsObservableList;

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

    private void initializeButtonActions() {
        view.getModifierButton().setOnAction(e -> modifierComposante());
        view.getSupprimerButton().setOnAction(e -> supprimerComposante());
        view.getRetourButton().setOnAction(e -> retournerMenu());
    }


    public void afficherComposants(List<Composante> composants) {
        composantsObservableList.setAll(composants);
    }

    private void modifierComposante() {
        Composante selected = view.getListView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setNom(view.getNomField().getText());
                selected.setType(view.getTypeComboBox().getValue());
                selected.setDescription(view.getDescriptionField().getText());
                selected.setPrix(Integer.parseInt(view.getPrixField().getText()));
                gestionComposantes.updateComposante(selected);
                afficherComposants(gestionComposantes.chargerComposantes(fournisseur.getEmail())); // Refresh the list
            } catch (NumberFormatException e) {
                System.err.println("Erreur de format pour le prix : " + e.getMessage());
            }
        }
    }

    private void supprimerComposante() {
        Composante selected = view.getListView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            gestionComposantes.supprimerComposante(selected.getId(), selected.getFournisseurEmail());
            composantsObservableList.remove(selected);
        }
    }

    private void retournerMenu() {
        MenuFournisseurView menuFournisseurView = new MenuFournisseurView();
        MenuFournisseurController menuFournisseurController = new MenuFournisseurController(stage, menuFournisseurView);
        stage.setScene(new Scene(menuFournisseurView, 900, 700));
    }

    private void chargerEtAfficherComposants(String fournisseurEmail) {
        List<Composante> composants = gestionComposantes.chargerComposantes(fournisseurEmail);
        afficherComposants(composants);
        printComposantesList(composants);
    }

    private void printComposantesList(List<Composante> composantes) {
        if (composantes.isEmpty()) {
            System.out.println("Aucune composante trouvée pour le fournisseur : " + fournisseur.getEmail());
        } else {
            System.out.println("Liste des composants pour le fournisseur : " + fournisseur.getEmail());
            composantes.forEach(System.out::println);
        }
    }
}
