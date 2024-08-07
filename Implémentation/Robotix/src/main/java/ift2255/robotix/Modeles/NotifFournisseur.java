package ift2255.robotix.Modeles;

/**
 * La classe NotifFournisseur représente une notification destinée à un fournisseur.
 * Elle hérite de la classe Notification et contient une adresse email du fournisseur.
 */
public class NotifFournisseur extends Notification {

    /**
     * L'adresse email du fournisseur.
     */
    private String emailFournisseur;

    /**
     * Constructeur pour créer une notification pour un fournisseur avec un message et une adresse email.
     *
     * @param message le message de la notification
     * @param emailFournisseur l'adresse email du fournisseur
     */
    public NotifFournisseur(String message, String emailFournisseur) {
        super(message);
        this.emailFournisseur = emailFournisseur;
    }

    /**
     * Retourne l'adresse email du fournisseur.
     *
     * @return l'adresse email du fournisseur
     */
    public String getEmailFournisseur() {
        return emailFournisseur;
    }
}
