package ift2255.robotix.Modeles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NotifService {
    // The instance should be static
    private static NotifService instance = null;
    private List<Notification> notifications = new ArrayList<Notification>();

    // Private constructor to prevent instantiation
    private NotifService() {}

    // Public method to provide access to the instance
    public static synchronized NotifService getInstance() {
        if (instance == null) {
            instance = new NotifService();
        }
        return instance;
    }

   public void sendNotif(String text) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        String date = now.format(formatter);
        Notification notif = new Notification(date + " | " +text);
        notifications.add(notif);
   }
   public List<Notification> getNotifications() {
        return notifications;
   }

   public void suppressNotif(Notification notif) {
        notifications.remove(notif);
   }
}
