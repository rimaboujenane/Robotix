package ift2255.robotix.Modeles;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Gère les activités dans le système, y compris l'inscription et la désinscription des utilisateurs.
 */
public class GestionActivites {

    // Liste des toutes les activités
    private List<Activite> activites = new ArrayList<>();

    // Liste des activités auxquelles l'utilisateur est inscrit
    private List<Activite> mesActivites = new ArrayList<>();

    // Liste des activités auxquelles l'utilisateur n'est pas inscrit
    private List<Activite> activitesNonInscrites = new ArrayList<>();

    // Utilisateur pour lequel les activités sont gérées
    private Utilisateur utilisateur;

    /**
     * Constructeur pour initialiser la gestion des activités avec un utilisateur spécifique.
     *
     * @param utilisateur L'utilisateur dont les activités sont gérées
     */
    public GestionActivites(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        chargerActivite("src/main/resources/data/activites.csv");
        mesActivites(utilisateur.getEmail());
    }

    /**
     * Charge les activités à partir d'un fichier CSV.
     *
     * @param csvFileName Chemin du fichier CSV contenant les activités
     */
    private void chargerActivite(String csvFileName) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFileName))) {
            reader.skip(1); // Ignore l'en-tête du fichier CSV
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Vérifie que la ligne contient suffisamment de champs
                if (nextLine.length >= 5) {
                    int ID = Integer.parseInt(nextLine[0]);
                    String nom = nextLine[1];
                    LocalDate dateDebut = LocalDate.parse(nextLine[2]);
                    LocalDate dateFin = LocalDate.parse(nextLine[3]);
                    List<String> utilisateursInscrits = Arrays.asList(nextLine[4].trim().split(";"));

                    Activite activite = new Activite(ID, nom, dateDebut, dateFin, utilisateursInscrits);
                    activites.add(activite);
                } else {
                    System.err.println("La ligne ne contient pas assez de champs : " + String.join(",", nextLine));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour les listes d'activités en fonction des inscriptions de l'utilisateur.
     *
     * @param email L'email de l'utilisateur pour vérifier les inscriptions
     */
    private void mesActivites(String email) {
        // Listes temporaires pour éviter les doublons
        List<Activite> activitesInscritesTemp = new ArrayList<>();
        List<Activite> activitesNonInscritesTemp = new ArrayList<>();

        for (Activite activite : activites) {
            boolean estInscrit = false;
            for (String emailInscrit : activite.getUtilisateursInscrits()) {
                if (email.equals(emailInscrit)) {
                    activitesInscritesTemp.add(activite);
                    estInscrit = true;
                    break;
                }
            }
            if (!estInscrit) {
                activitesNonInscritesTemp.add(activite);
            }
        }

        // Mise à jour des listes finales
        mesActivites.addAll(activitesInscritesTemp);
        activitesNonInscrites.addAll(activitesNonInscritesTemp);
    }

    /**
     * Inscrit l'utilisateur à une activité et met à jour le fichier CSV.
     *
     * @param activite L'activité à laquelle l'utilisateur s'inscrit
     * @param utilisateurInscrit L'utilisateur qui s'inscrit à l'activité
     */
    public void inscription(Activite activite, Utilisateur utilisateurInscrit) {
        List<String> utilisateursInscrits = new ArrayList<>(activite.getUtilisateursInscrits());
        boolean add = utilisateursInscrits.add(utilisateur.getEmail());

        if (add) {
            activite.setUtilisateursInscrits(utilisateursInscrits);
            for (Activite act : activites) {
                if (act.getId() == activite.getId()) {
                    act.setUtilisateursInscrits(utilisateursInscrits);
                    break;
                }
            }
            sauvegarderActivitesDansCSV("src/main/resources/data/activites.csv");
            System.out.println("Inscription réussie pour l'utilisateur : " + utilisateur.getEmail());
        } else {
            System.out.println("L'utilisateur est déjà inscrit à cette activité.");
        }
    }

    /**
     * Désinscrit l'utilisateur d'une activité et met à jour le fichier CSV.
     *
     * @param activite L'activité de laquelle l'utilisateur se désinscrit
     */
    public void desinscription(Activite activite) {
        List<String> utilisateursInscrits = new ArrayList<>(activite.getUtilisateursInscrits());
        boolean removed = utilisateursInscrits.remove(utilisateur.getEmail());

        if (removed) {
            activite.setUtilisateursInscrits(utilisateursInscrits);
            for (Activite act : activites) {
                if (act.getId() == activite.getId()) {
                    act.setUtilisateursInscrits(utilisateursInscrits);
                    break;
                }
            }
            sauvegarderActivitesDansCSV("src/main/resources/data/activites.csv");
            System.out.println("Désinscription réussie pour l'utilisateur : " + utilisateur.getEmail());
        } else {
            System.out.println("L'utilisateur n'est pas inscrit à cette activité.");
        }
    }

    /**
     * Sauvegarde les activités dans un fichier CSV.
     *
     * @param csvFileName Chemin du fichier CSV où les activités seront sauvegardées
     */
    public void sauvegarderActivitesDansCSV(String csvFileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            String[] headers = {"ID", "Nom", "DateDebut", "DateFin", "UtilisateursInscrits"};
            writer.writeNext(headers);

            for (Activite activite : activites) {
                String[] data = {
                        String.valueOf(activite.getId()),
                        activite.getNom(),
                        activite.getDateDebut().toString(),
                        activite.getDateFin().toString(),
                        String.join(";", activite.getUtilisateursInscrits())
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public List<Activite> getMesActivites() {
        return mesActivites;
    }

    public List<Activite> getActivitesNonInscrites() {
        return activitesNonInscrites;
    }
}
