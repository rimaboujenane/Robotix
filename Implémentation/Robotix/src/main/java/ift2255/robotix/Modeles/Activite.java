package ift2255.robotix.Modeles;

import java.time.LocalDate;
import java.util.List;

public class Activite {
    private int id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String nom;
    private List<String> utilisateursInscrits;

    public Activite(int id, String nom, LocalDate dateDebut, LocalDate dateFin, List<String> utilisateursInscrits) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nom = nom;
        this.utilisateursInscrits = utilisateursInscrits;
    }

    public int getId() {
        return id;
    }
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }
    public String getNom() {
        return nom;
    }
    public List<String> getUtilisateursInscrits() {
        return utilisateursInscrits;
    }
    public void setUtilisateursInscrits(List<String> utilisateursInscrits) {
        this.utilisateursInscrits = utilisateursInscrits;
    }


}
