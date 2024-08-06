package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Modeles.NotifFournisseur;
import ift2255.robotix.Modeles.NotifService;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import ift2255.robotix.View.Fournisseur.NotifFournisseurView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotifFournisseurController {
    private NotifFournisseurView notifFournisseurView;
    private Stage stage;

    public NotifFournisseurController(Stage stage, NotifFournisseurView notifFournisseurView) {
        this.notifFournisseurView = notifFournisseurView;
        this.stage = stage;

        this.notifFournisseurView.getBackButton().setOnAction(e -> goBackMenu());
        this.notifFournisseurView.setButtonAction(event -> handleDelete((NotifFournisseur) ((Button) event.getSource()).getUserData()));
    }

    private void goBackMenu() {
        MenuFournisseurView menuFournisseurView = new MenuFournisseurView();
        MenuFournisseurController menuFournisseurController = new MenuFournisseurController(stage, menuFournisseurView);
        stage.setScene(new Scene(menuFournisseurView, 900, 700));
    }

    private void handleDelete(NotifFournisseur notifFournisseur) {
        NotifService.getInstance().suppressNotifFournisseur(notifFournisseur);
        notifFournisseurView.afficher();
        this.notifFournisseurView.getBackButton().setOnAction(e -> goBackMenu());
        this.notifFournisseurView.setButtonAction(event -> handleDelete((NotifFournisseur) ((Button) event.getSource()).getUserData()));
    }
}
