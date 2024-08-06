package ift2255.robotix.Modeles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de service singleton responsable de la gestion des notifications.
 */
public class NotifService {
    // L'instance unique de NotifService
    private static NotifService instance = null;
    // Liste pour stocker les notifications
    private List<Notification> notifications = new ArrayList<>();
    private List<NotifFournisseur> notificationsFournisseur = new ArrayList<>();

    /**
     * Constructeur privé pour empêcher l'instanciation depuis l'extérieur.
     */
    private NotifService() {}

    /**
     * Fournit l'accès à l'instance singleton de NotifService.
     *
     * @return l'instance singleton de NotifService
     */
    public static synchronized NotifService getInstance() {
        if (instance == null) {
            instance = new NotifService();
        }
        return instance;
    }

    /**
     * Envoie une notification avec la date et l'heure actuelles.
     *
     * @param text le texte de la notification
     */
    public void sendNotif(String text) {
        // Obtenir la date et l'heure actuelles
        LocalDateTime now = LocalDateTime.now();
        // Définir le format pour la date et l'heure
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        // Formater la date et l'heure actuelles
        String date = now.format(formatter);
        // Créer une nouvelle notification avec la date formatée et le message
        Notification notif = new Notification(date + " | " + text);
        // Ajouter la notification à la liste
        notifications.add(notif);
    }

    /**
     * Envoie une notification spécifique à un fournisseur avec la date et l'heure actuelles.
     *
     * @param fournisseurEmail l'email du fournisseur
     * @param text le texte de la notification
     */
    public void sendNotifFournisseur(String fournisseurEmail, String text) {
        // Obtenir la date et l'heure actuelles
        LocalDateTime now = LocalDateTime.now();
        // Définir le format pour la date et l'heure
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        // Formater la date et l'heure actuelles
        String date = now.format(formatter);
        // Créer une nouvelle notification avec la date formatée, l'email du fournisseur, et le message
        NotifFournisseur notifFournisseur = new NotifFournisseur(date + " | " + text, fournisseurEmail);
        notificationsFournisseur.add(notifFournisseur);
    }


    /**
     * Renvoie la liste des notifications actuelles.
     *
     * @return une liste de notifications
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    public List<NotifFournisseur> getNotifFournisseur() {
        return notificationsFournisseur;
    }

    /**
     * Supprime une notification spécifique de la liste.
     *
     * @param notif la notification à supprimer
     */
    public void suppressNotif(Notification notif) {
        notifications.remove(notif);
    }

    public void suppressNotifFournisseur(NotifFournisseur notifFournisseur) {
        notificationsFournisseur.remove(notifFournisseur);
    }
}
