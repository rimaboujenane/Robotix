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

/**
 * Classe pour gérer les utilisateurs, offrant des fonctionnalités d'ajout, de mise à jour et de validation.
 */
public class GestionUtilisateurs extends GestionUser {
    /**
     * Map pour stocker les utilisateurs.
     */
    protected Map<String, Utilisateur> utilisateursMap = new HashMap<>();

    private Utilisateur utilisateur;

    /**
     * Constructeur pour initialiser la map des utilisateurs et charger les données depuis un fichier CSV.
     */
    public GestionUtilisateurs() {
        utilisateursMap = new HashMap<>();
        chargerDepuisCSV("src/main/resources/data/utilisateur.csv");
    }

    /**
     * Vérifie si un utilisateur avec l'email et le mot de passe fournis existe dans la map.
     *
     * @param email    L'email de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return true si l'utilisateur est valide, sinon false.
     */
    public boolean isValidUser(String email, String password) {
        Utilisateur utilisateur = utilisateursMap.get(email); // Récupère l'utilisateur par email
        if (utilisateur != null && utilisateur.getPassword().equals(password)) {
            this.utilisateur = utilisateur;
            return true;
        }
        return false;
    }

    /**
     * Retourne l'utilisateur actuellement authentifié.
     *
     * @return L'utilisateur actuellement authentifié.
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Met à jour les informations d'un utilisateur dans la map et dans le fichier CSV.
     *
     * @param utilisateurModifie L'utilisateur modifié avec les nouvelles informations.
     */
    public void updateUtilisateur(Utilisateur utilisateurModifie) {
        String email = utilisateurModifie.getEmail(); // Récupérer l'email de l'utilisateur modifié
        if (utilisateursMap.containsKey(email)) {
            // Mettre à jour l'utilisateur dans la map avec les nouvelles informations
            utilisateursMap.put(email, utilisateurModifie);

            // Réécrire tous les utilisateurs dans le fichier CSV après la mise à jour
            writeToCSV("src/main/resources/data/utilisateur.csv");
        } else {
            System.err.println("L'utilisateur avec l'email " + email + " n'existe pas dans la liste.");
            // Vous pouvez gérer ce cas comme nécessaire, par exemple, lever une exception ou afficher un message d'erreur
        }
    }

    /**
     * Ajoute un nouvel utilisateur à la map et met à jour le fichier CSV.
     *
     * @param utilisateur L'utilisateur à ajouter.
     */
    public void addUtilisateur(Utilisateur utilisateur) {
        String email = utilisateur.getEmail();
        utilisateursMap.put(email, utilisateur);
        writeToCSV("src/main/resources/data/utilisateur.csv");
    }

    /**
     * Charge les utilisateurs depuis un fichier CSV et les ajoute à la map.
     *
     * @param csvFileName Le nom du fichier CSV contenant les utilisateurs.
     */
    private void chargerDepuisCSV(String csvFileName) {
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

    /**
     * Écrit les utilisateurs dans un fichier CSV.
     *
     * @param csvFileName Le nom du fichier CSV dans lequel écrire les utilisateurs.
     */
    private void writeToCSV(String csvFileName) {
        List<String[]> lines = new ArrayList<>();
        String[] headers = {"Nom", "Prenom", "Password", "Email", "Telephone"};
        lines.add(headers);
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
}
