package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.GestionComposantes;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.SearchComposanteView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchComposanteController {
    private SearchComposanteView searchCompMenuView;
    private Stage stage;

     public SearchComposanteController(Stage stage, SearchComposanteView searchCompMenuView) {

         this.searchCompMenuView = searchCompMenuView;
         this.stage = stage;

         this.searchCompMenuView.getBackButton().setOnAction(e -> goBackMenu());
     }

    private void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}

