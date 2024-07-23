package ift2255.robotix.Modeles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GestionUser {
    /**
     * Map pour stocker les utilisateurs.
     */
    protected Map<String, Utilisateur> utilisateursMap = new HashMap<>();

    /**
     * Map pour stocker les fournisseurs.
     */
    protected Map<String, Fournisseur> fournisseurMap = new HashMap<>();

    /**
     * Charge les utilisateurs ou les fournisseurs à partir d'un fichier CSV dans les HashMaps appropriées.
     *
     * @param csvFileName Le chemin vers le fichier CSV.
     * @param isUtilisateur Indique si le chargement concerne les utilisateurs (true) ou les fournisseurs (false).
     */
    protected void chargerDepuisCSV(String csvFileName, boolean isUtilisateur) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFileName))) {
            reader.skip(1); // Ignore l'entête du fichier CSV
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Vérifie que la ligne a au moins 5 champs (nom, prénom, mot de passe, email, téléphone)
                if (nextLine.length >= 5) {
                    String nom = nextLine[0];
                    String prenom = nextLine[1];
                    String password = nextLine[2];
                    String email = nextLine[3];
                    String phone = nextLine[4];

                    User user;
                    if (isUtilisateur) {
                        user = new Utilisateur(nom, prenom, password, email, phone);
                        utilisateursMap.put(email, (Utilisateur) user); // Utilisation de l'email comme clé
                    } else {
                        user = new Fournisseur(nom, prenom, password, email, phone);
                        fournisseurMap.put(email, (Fournisseur) user); // Utilisation de l'email comme clé
                    }
                } else {
                    System.err.println("La ligne ne contient pas assez de champs : " + String.join(",", nextLine));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Écrit les utilisateurs ou les fournisseurs dans un fichier CSV.
     *
     * @param csvFileName Le chemin vers le fichier CSV.
     * @param isUtilisateur Indique si l'écriture concerne les utilisateurs (true) ou les fournisseurs (false).
     */
    protected void writeToCSV(String csvFileName, boolean isUtilisateur) {
        List<String[]> lines = new ArrayList<>();
        String[] headers = {"Nom", "Prenom", "Password", "Email", "Telephone"};
        lines.add(headers);

        if (isUtilisateur) {
            // Ajoute chaque utilisateur à la liste des lignes
            utilisateursMap.values().forEach(utilisateur -> {
                String[] userData = {
                        utilisateur.getNom(),
                        utilisateur.getPrenom(),
                        utilisateur.getPassword(),
                        utilisateur.getEmail(),
                        utilisateur.getTelephone()
                };
                lines.add(userData);
            });
        } else {
            // Ajoute chaque fournisseur à la liste des lignes
            fournisseurMap.values().forEach(fournisseur -> {
                String[] userData = {
                        fournisseur.getNom(),
                        fournisseur.getPrenom(),
                        fournisseur.getPassword(),
                        fournisseur.getEmail(),
                        fournisseur.getTelephone()
                };
                lines.add(userData);
            });
        }

        // Écrit les lignes dans le fichier CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            writer.writeAll(lines);
            System.out.println("Ecriture dans le fichier CSV réussie : " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne la map des utilisateurs.
     *
     * @return La map des utilisateurs.
     */
    public Map<String, Utilisateur> getUtilisateursMap() {
        return utilisateursMap;
    }

    /**
     * Retourne la map des fournisseurs.
     *
     * @return La map des fournisseurs.
     */
    public Map<String, Fournisseur> getFournisseurMap() {
        return fournisseurMap;
    }
}
