package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.RegisterFournisseur;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import ift2255.robotix.View.Fournisseur.ProfilFournisseurView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue ProfilFournisseurView. Gère les interactions de l'utilisateur avec la vue de profil
 * fournisseur, y compris la mise à jour des informations de profil et la navigation vers le menu principal.
 */
public class ProfilFournisseurController {
    private ProfilFournisseurView profilFournisseurView;
    private Stage stage;
    private Fournisseur fournisseur = RegisterFournisseur.getInstance().getFournisseur();
    private Fournisseur fournisseurModifie;

    /**
     * Constructeur pour ProfilFournisseurController.
     * Initialise les gestionnaires d'événements pour les boutons de sauvegarde et de retour.
     *
     * @param stage Le stage principal de l'application.
     * @param profilFournisseurView La vue de profil fournisseur.
     */
    public ProfilFournisseurController(Stage stage, ProfilFournisseurView profilFournisseurView) {
        this.stage = stage;
        this.profilFournisseurView = profilFournisseurView;
        this.fournisseur = fournisseur;

        // Définit l'action pour le bouton de sauvegarde
        this.profilFournisseurView.getSaveButton().setOnAction(e -> updateProfile());

        // Définit l'action pour le bouton de retour
        this.profilFournisseurView.getBackButton().setOnAction(e -> goBackMenu());
    }

    /**
     * Navigue vers le menu principal des fournisseurs.
     * Crée une nouvelle instance de MenuFournisseurView et MenuFournisseurController et
     * met à jour la scène du stage avec la vue du menu fournisseur.
     */
    private void goBackMenu() {
        MenuFournisseurView menuFournisseurView = new MenuFournisseurView();
        MenuFournisseurController menuFournisseurController = new MenuFournisseurController(stage, menuFournisseurView);
        stage.setScene(new Scene(menuFournisseurView, 900, 700));
    }

    /**
     * Met à jour le profil du fournisseur avec les informations saisies dans la vue.
     * Si le mot de passe n'est pas modifié, l'ancien mot de passe est utilisé. Sinon, le mot de passe
     * est mis à jour avec la nouvelle valeur saisie.
     */
    private void updateProfile() {
        // Récupère les informations saisies dans la vue
        String name = profilFournisseurView.getUserName();
        String prenom = profilFournisseurView.getUserPrenom();
        String email = profilFournisseurView.getUserEmail();
        String phone = profilFournisseurView.getUserPhone();
        String password = profilFournisseurView.getUserPassword();
        String companie = profilFournisseurView.getUserCompagnie();

        // Si le mot de passe a été modifié
        if (password.isEmpty()) {
            String existingPassword = fournisseur.getPassword();
            fournisseurModifie = new Fournisseur(name, prenom, existingPassword, email, phone, companie);
        } else {
            fournisseurModifie = new Fournisseur(name, prenom, password, email, phone, companie);
        }

        // Met à jour le fournisseur dans la gestion des fournisseurs
        GestionFournisseurs gestionFournisseurs = new GestionFournisseurs();
        gestionFournisseurs.updateFournisseur(fournisseurModifie, RegisterFournisseur.getInstance().getFournisseur().getEmail() );

        // Met à jour le fournisseur actuel
        this.fournisseur = fournisseurModifie;

        // Met à jour le singleton RegisterFournisseur
        RegisterFournisseur.getInstance().setFournisseur(fournisseur);

        // Affiche une alerte de confirmation de la modification
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification");
        alert.setHeaderText(null);
        alert.setContentText("Modification enregistrée!");
        alert.show();
    }
}
