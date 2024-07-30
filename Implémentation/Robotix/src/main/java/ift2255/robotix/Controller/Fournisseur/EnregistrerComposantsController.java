// EnregistrerComposantsController.java
package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Modeles.*;
import ift2255.robotix.View.Fournisseur.EnregistrerComposantsView;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnregistrerComposantsController {
    private Stage stage;
    private EnregistrerComposantsView view;
    private GestionComposantes gestionComposantes;
    private Fournisseur fournisseur = RegisterFournisseur.getInstance().getFournisseur();

    public EnregistrerComposantsController(Stage stage, EnregistrerComposantsView view, GestionComposantes gestionComposantes) {
        this.stage = stage;
        this.view = view;
        this.gestionComposantes = gestionComposantes;

        this.view.getEnregistrerButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enregistrerComposante();
            }
        });

        this.view.getRetourButton().setOnAction(e -> retournerMenu());
    }

    private void enregistrerComposante() {
        try {
            String nom = view.getNomField().getText();
            String type = view.getTypeComboBox().getValue(); // Retrieve selected value from ComboBox
            String description = view.getDescriptionField().getText();
            int prix = Integer.parseInt(view.getPrixField().getText());
            String fournisseurEmail = fournisseur.getEmail(); // Replace with actual logged-in fournisseur email

            Composante composante = new Composante(0, nom, type, description, prix, fournisseurEmail); // ID set to 0, should be handled properly
            gestionComposantes.ajouterComposante(composante);

            // Print the added composante details to the terminal
            System.out.println("Added Composante:");
            System.out.println("Nom: " + nom);
            System.out.println("Type: " + type);
            System.out.println("Description: " + description);
            System.out.println("Prix: " + prix);
            System.out.println("FournisseurEmail: " + fournisseurEmail);

            // Optionally, clear the fields
            view.getNomField().clear();
            view.getTypeComboBox().getSelectionModel().clearSelection();
            view.getDescriptionField().clear();
            view.getPrixField().clear();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement du composant: " + e.getMessage());
        }
    }

    private void retournerMenu() {
        MenuFournisseurView menuFournisseurView = new MenuFournisseurView();
        MenuFournisseurController menuFournisseurController = new MenuFournisseurController(stage, menuFournisseurView);
        stage.setScene(new Scene(menuFournisseurView, 900, 700));
    }
}
