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
     * Charge les utilisateurs à partir d'un fichier CSV dans la HashMap.
     *
     * @param csvFileName Le chemin vers le fichier CSV.
     */
    protected Map<String, Utilisateur> utilisateursMap = new HashMap<>();
    protected Map<String, Fournisseur> fournisseurMap = new HashMap<>();

    protected void chargerUserDepuisCSV(String csvFileName) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFileName))) {
            reader.skip(1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Vérifie que la ligne a au moins 4 champs (nom, prénom, mot de passe, email)
                if (nextLine.length >= 5) {
                    String nom = nextLine[0];
                    String prenom = nextLine[1];
                    String password = nextLine[2];
                    String email = nextLine[3];
                    String phone = nextLine[4];

                    Utilisateur utilisateur = new Utilisateur(nom, prenom, password, email, phone);
                    utilisateursMap.put(email, utilisateur); // Utilisation de l'email comme clé
                } else {
                    System.err.println("La ligne ne contient pas assez de champs : " + String.join(",", nextLine));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    protected void chargerFournisseurDepuisCSV(String csvFileName) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFileName))) {
            reader.skip(1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Vérifie que la ligne a au moins 4 champs (nom, prénom, mot de passe, email)
                if (nextLine.length >= 5) {
                    String nom = nextLine[0];
                    String prenom = nextLine[1];
                    String password = nextLine[2];
                    String email = nextLine[3];
                    String phone = nextLine[4];

                    Fournisseur fournisseur = new Fournisseur(nom, prenom, password, email, phone);
                    fournisseurMap.put(email, fournisseur); // Utilisation de l'email comme clé
                } else {
                    System.err.println("La ligne ne contient pas assez de champs : " + String.join(",", nextLine));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    protected void writeUtilisateursToCSV(String csvFileName) {
        // Créer une liste pour stocker les lignes de données à écrire dans le fichier CSV
        List<String[]> lines = new ArrayList<>();

        // Ajouter les entêtes si nécessaire
        String[] headers = {"Nom", "Prenom", "Password", "Email", "Telephone"};
        lines.add(headers);

        // Ajouter chaque utilisateur à la liste des lignes
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

        // Écrire les lignes dans le fichier CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            writer.writeAll(lines);
            System.out.println("Ecriture dans le fichier CSV réussie : " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeFournisseursToCSV(String csvFileName) {
        // Créer une liste pour stocker les lignes de données à écrire dans le fichier CSV
        List<String[]> lines = new ArrayList<>();

        // Ajouter les entêtes si nécessaire
        String[] headers = {"Nom", "Prenom", "Password", "Email", "Telephone"};
        lines.add(headers);

        // Ajouter chaque utilisateur à la liste des lignes
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

        // Écrire les lignes dans le fichier CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            writer.writeAll(lines);
            System.out.println("Ecriture dans le fichier CSV réussie : " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Utilisateur> getUtilisateursMap() {
        return utilisateursMap;
    }

    public Map<String, Fournisseur> getFournisseurMap() {
        return fournisseurMap;
    }

}
