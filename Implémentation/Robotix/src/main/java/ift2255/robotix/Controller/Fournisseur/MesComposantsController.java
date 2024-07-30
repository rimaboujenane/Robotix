// MesComposantsController.java
package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionComposantes;
import ift2255.robotix.View.Fournisseur.MesComposantsView;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MesComposantsController {
    private Stage stage;
    private MesComposantsView view;
    private GestionComposantes gestionComposantes;
    private Fournisseur fournisseur;

    public MesComposantsController(Stage stage, MesComposantsView view, GestionComposantes gestionComposantes) {
        this.stage = stage;
        this.view = view;
        this.gestionComposantes = gestionComposantes;

        ObservableList<Composante> composantesList = FXCollections.observableArrayList(gestionComposantes.chargerComposantes(fournisseur.getEmail()));
        this.view.setComposantesList(composantesList);

        this.view.getModifierButton().setOnAction(e -> modifierComposante());
        this.view.getSupprimerButton().setOnAction(e -> supprimerComposante());
        this.view.getRetourButton().setOnAction(e -> retournerMenu());
    }

    private void modifierComposante() {
        Composante selected = view.getTableView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setNom(view.getNomField().getText());
            selected.setType(view.getTypeField().getText());
            selected.setDescription(view.getDescriptionField().getText());
            selected.setPrix(Integer.parseInt(view.getPrixField().getText()));
            gestionComposantes.modifierComposante(selected);
            view.getTableView().refresh();
        }
    }


    private void supprimerComposante() {
        Composante selected = view.getTableView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            gestionComposantes.supprimerComposante(selected.getId(), selected.getFournisseurEmail());
            view.getTableView().getItems().remove(selected);
        }
    }

    private void retournerMenu() {
        MenuFournisseurView menuFournisseurView = new MenuFournisseurView();
        MenuFournisseurController menuFournisseurController = new MenuFournisseurController(stage, menuFournisseurView);
        stage.setScene(new Scene(menuFournisseurView, 900, 700));
    }
}
