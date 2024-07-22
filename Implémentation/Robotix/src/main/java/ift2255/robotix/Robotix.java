package ift2255.robotix;

import ift2255.robotix.Controller.LoginController;
import ift2255.robotix.View.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Robotix extends Application {
    /**
     * Le point d'entrée principal pour l'application JavaFX.
     * Cette méthode est appelée lorsque l'application est lancée.
     *
     * @param primaryStage La scène principale pour cette application, sur laquelle la scène de l'application est définie.
     */
    @Override
    public void start(Stage primaryStage) {
        // Création de la vue de connexion
        LoginView loginView = new LoginView();

        // Création du contrôleur de connexion avec la scène principale et la vue de connexion
        LoginController loginController = new LoginController(primaryStage, loginView);

        // Définition du titre de la scène principale
        primaryStage.setTitle("Robotix");

        // Définition de la scène avec la vue de connexion et les dimensions spécifiées
        primaryStage.setScene(new Scene(loginView, 900, 700));

        // Affichage de la scène principale
        primaryStage.show();
    }

    /**
     * La méthode principale pour lancer l'application JavaFX.
     *
     * @param args Arguments de ligne de commande passés à l'application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
