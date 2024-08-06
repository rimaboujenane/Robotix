package ift2255.robotix.Modeles;

public class NotifFournisseur extends Notification {
    private String emailFournisseur;

    public NotifFournisseur(String message, String emailFournisseur) {
        super(message);
        this.emailFournisseur = emailFournisseur;
    }

    public String getEmailFournisseur() {
        return emailFournisseur;
    }
}


