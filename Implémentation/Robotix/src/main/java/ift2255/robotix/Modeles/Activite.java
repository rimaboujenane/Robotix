package ift2255.robotix.Modeles;

import java.time.LocalDate;
import java.util.List;

/**
 * Représente une activité dans le système.
 */
public class Activite {
    private int id; // Identifiant unique de l'activité
    private LocalDate dateDebut; // Date de début de l'activité
    private LocalDate dateFin; // Date de fin de l'activité
    private String nom; // Nom de l'activité
    private List<String> utilisateursInscrits; // Liste des emails des utilisateurs inscrits à l'activité

    /**
     * Constructeur pour créer une nouvelle activité avec les informations spécifiées.
     *
     * @param id Identifiant de l'activité
     * @param nom Nom de l'activité
     * @param dateDebut Date de début de l'activité
     * @param dateFin Date de fin de l'activité
     * @param utilisateursInscrits Liste des utilisateurs inscrits à l'activité
     */
    public Activite(int id, String nom, LocalDate dateDebut, LocalDate dateFin, List<String> utilisateursInscrits) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.utilisateursInscrits = utilisateursInscrits;
    }

    /**
     * Obtient l'identifiant de l'activité.
     *
     * @return Identifiant de l'activité
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient la date de début de l'activité.
     *
     * @return Date de début de l'activité
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Obtient la date de fin de l'activité.
     *
     * @return Date de fin de l'activité
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Obtient le nom de l'activité.
     *
     * @return Nom de l'activité
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient la liste des utilisateurs inscrits à l'activité.
     *
     * @return Liste des utilisateurs inscrits
     */
    public List<String> getUtilisateursInscrits() {
        return utilisateursInscrits;
    }

    /**
     * Définit la liste des utilisateurs inscrits à l'activité.
     *
     * @param utilisateursInscrits Liste des utilisateurs inscrits
     */
    public void setUtilisateursInscrits(List<String> utilisateursInscrits) {
        this.utilisateursInscrits = utilisateursInscrits;
    }
}
