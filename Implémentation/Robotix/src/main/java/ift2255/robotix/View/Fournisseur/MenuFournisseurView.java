package ift2255.robotix.View.Fournisseur;

import ift2255.robotix.Modeles.RegisterFournisseur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * La classe MenuFournisseurView représente la vue du menu principal pour les fournisseurs.
 * Elle affiche un message de bienvenue et propose plusieurs boutons de navigation vers
 * différentes sections de l'application.
 */
public class MenuFournisseurView extends VBox {
    private Button profilMenuButton;
    private Button notifFournisseurButton;
    private Button mesComposantsMenuButton;
    private Button enregistrerComposantsMenuButton;
    private Button logoutMenuButton;
    private Button exitMenuButton;

    /**
     * Constructeur de la classe MenuFournisseurView.
     * Initialise la vue avec un message de bienvenue et les boutons de navigation.
     */
    public MenuFournisseurView() {
        // Code d'initialisation
    }

    /**
     * Retourne le bouton de menu pour accéder au profil.
     *
     * @return le bouton profilMenuButton
     */
    public Button getProfilMenuButton() {
        return profilMenuButton;
    }

    /**
     * Retourne le bouton de menu pour accéder à la section Mes Notifications.
     *
     * @return le bouton notifFournisseurButton
     */
    public Button getNotifFournisseurButton() {
        return notifFournisseurButton;
    }

    /**
     * Retourne le bouton de menu pour accéder à la section Mes Composants.
     *
     * @return le bouton mesComposantsMenuButton
     */
    public Button getMesComposantsMenuButton() {
        return mesComposantsMenuButton;
    }

    /**
     * Retourne le bouton de menu pour accéder à la section Enregistrer Composants.
     *
     * @return le bouton enregistrerComposantsMenuButton
     */
    public Button getEnregistrerComposantsMenuButton() {
        return enregistrerComposantsMenuButton;
    }

    /**
     * Retourne le bouton de menu pour se déconnecter.
     *
     * @return le bouton logoutMenuButton
     */
    public Button getLogoutMenuButton() {
        return logoutMenuButton;
    }

    /**
     * Retourne le bouton de menu pour quitter l'application.
     *
     * @return le bouton exitMenuButton
     */
    public Button getExitMenuButton() {
        return exitMenuButton;
    }
}
