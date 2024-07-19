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

public class GestionActivites {
    private List<Activite> activites = new ArrayList<>();
    private List<Activite> mesActivites = new ArrayList<>();
    private List<Activite> activitesNonInscrites = new ArrayList<>();
    private Utilisateur utilisateur;

    public GestionActivites(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        chargerActivite("src/main/resources/data/activites.csv");
        mesActivites(utilisateur.getEmail());
    }

    private void mesActivites(String email) {
        // Listes temporaires pour éviter des doublons
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


    private void chargerActivite(String csvFileName){
        try (CSVReader reader = new CSVReader(new FileReader(csvFileName))) {
            reader.skip(1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Vérifie que la ligne a au moins 4 champs (nom, prénom, mot de passe, email)
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

    public void desinscription(Activite activite) {
        // Vérifier si l'utilisateur est inscrit à l'activité
        List<String> utilisateursInscrits = new ArrayList<>(activite.getUtilisateursInscrits());
        boolean removed = utilisateursInscrits.remove(utilisateur.getEmail());

        if (removed) {
            activite.setUtilisateursInscrits(utilisateursInscrits); // Mettre à jour la liste dans l'activité
            // Mettre à jour la liste activites si nécessaire
            for (Activite act : activites) {
                if (act.getId() == activite.getId()) { // Supposant qu'ID est un identifiant unique pour chaque activité
                    act.setUtilisateursInscrits(utilisateursInscrits); // Mettre à jour dans la liste activites
                    break;
                }
            }
            // Mettre à jour le fichier CSV si nécessaire
            sauvegarderActivitesDansCSV("src/main/resources/data/activites.csv");

            // Afficher un message ou effectuer d'autres actions nécessaires
            System.out.println("Désinscription réussie pour l'utilisateur : " + utilisateur.getEmail());
        } else {
            System.out.println("L'utilisateur n'est pas inscrit à cette activité.");
            // Afficher un message à l'utilisateur ou gérer l'erreur selon vos besoins
        }
    }

    public void inscription(Activite activite, Utilisateur utilisateurInscrit) {
        // Vérifier si l'utilisateur est inscrit à l'activité
        List<String> utilisateursInscrits = new ArrayList<>(activite.getUtilisateursInscrits());
        boolean add = utilisateursInscrits.add(utilisateur.getEmail());

        if (add) {
            activite.setUtilisateursInscrits(utilisateursInscrits); // Mettre à jour la liste dans l'activité
            // Mettre à jour la liste activites si nécessaire
            for (Activite act : activites) {
                if (act.getId() == activite.getId()) { // Supposant qu'ID est un identifiant unique pour chaque activité
                    act.setUtilisateursInscrits(utilisateursInscrits); // Mettre à jour dans la liste activites
                    break;
                }
            }
            // Mettre à jour le fichier CSV si nécessaire
            sauvegarderActivitesDansCSV("src/main/resources/data/activites.csv");

            // Afficher un message ou effectuer d'autres actions nécessaires
            System.out.println("Inscription réussie pour l'utilisateur : " + utilisateur.getEmail());
        } else {
            System.out.println("L'utilisateur n'est pas inscrit à cette activité.");
            // Afficher un message à l'utilisateur ou gérer l'erreur selon vos besoins
        }
    }
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
