package ift2255.robotix.Modeles;

/**
 * Représente une notification avec un message associé.
 */
public class Notification {
    private String message;

    /**
     * Constructeur pour créer une nouvelle notification.
     *
     * @param message Le message de la notification.
     */
    public Notification(String message) {
        this.message = message;
    }

    /**
     * Renvoie le message de la notification.
     *
     * @return Le message de la notification.
     */
    public String getMessage() {
        return message;
    }
}
