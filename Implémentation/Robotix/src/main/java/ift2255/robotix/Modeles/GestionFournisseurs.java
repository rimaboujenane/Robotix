package ift2255.robotix.Modeles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe pour gérer les fournisseurs, offrant des fonctionnalités d'ajout, de mise à jour, et de validation des fournisseurs.
 * Cette classe étend {@link GestionUser} pour hériter des fonctionnalités de gestion des utilisateurs.
 */
public class GestionFournisseurs extends GestionUser {

    /**
     * Map pour stocker les fournisseurs avec leur email comme clé.
     */
    protected Map<String, Fournisseur> fournisseurMap = new HashMap<>();

    private Fournisseur fournisseur;

    /**
     * Constructeur pour initialiser la map des fournisseurs et charger les données depuis un fichier CSV.
     * Le fichier CSV doit être situé à l'emplacement "src/main/resources/data/fournisseur.csv".
     */
    public GestionFournisseurs() {
        fournisseurMap = new HashMap<>();
        chargerDepuisCSV("src/main/resources/data/fournisseur.csv");
    }

    /**
     * Vérifie si un fournisseur avec l'email et le mot de passe fournis existe dans la map.
     *
     * @param email    L'email du fournisseur.
     * @param password Le mot de passe du fournisseur.
     * @return true si le fournisseur est valide, sinon false.
     */
    public boolean isValidUser(String email, String password) {
        Fournisseur fournisseur = fournisseurMap.get(email); // Récupère le fournisseur par email
        if (fournisseur != null && fournisseur.getPassword().equals(password)) {
            this.fournisseur = fournisseur;
            return true;
        }
        return false;
    }

    /**
     * Retourne le fournisseur actuellement authentifié.
     *
     * @return Le fournisseur actuellement authentifié.
     */
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    /**
     * Met à jour les informations d'un fournisseur dans la map et dans le fichier CSV.
     *
     * @param fournisseurModifie Le fournisseur modifié avec les nouvelles informations.
     * @param email              L'email du fournisseur à mettre à jour.
     */
    public void updateFournisseur(Fournisseur fournisseurModifie, String email) {
        if (fournisseurMap.containsKey(email)) {
            // Mettre à jour le fournisseur dans la map avec les nouvelles informations
            fournisseurMap.put(email, fournisseurModifie);

            // Réécrire tous les fournisseurs dans le fichier CSV après la mise à jour
            writeToCSV("src/main/resources/data/fournisseur.csv");
        }
    }

    /**
     * Ajoute un nouveau fournisseur à la map et met à jour le fichier CSV.
     *
     * @param fournisseur Le fournisseur à ajouter.
     */
    public void addFournisseur(Fournisseur fournisseur) {
        String email = fournisseur.getEmail();
        fournisseurMap.put(email, fournisseur);
        writeToCSV("src/main/resources/data/fournisseur.csv");
    }

    /**
     * Charge les fournisseurs depuis un fichier CSV et les ajoute à la map.
     *
     * @param csvFileName Le nom du fichier CSV contenant les fournisseurs.
     */
    private void chargerDepuisCSV(String csvFileName) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFileName))) {
            reader.skip(1); // Ignore l'entête du fichier CSV
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Vérifie que la ligne a au moins 6 champs (nom, prénom, mot de passe, email, téléphone, compagnie)
                if (nextLine.length >= 6) {
                    String nom = nextLine[0];
                    String prenom = nextLine[1];
                    String password = nextLine[2];
                    String email = nextLine[3];
                    String phone = nextLine[4];
                    String compagnie = nextLine[5];

                    Fournisseur fournisseur = new Fournisseur(nom, prenom, password, email, phone, compagnie);
                    fournisseurMap.put(email, fournisseur); // Utilisation de l'email comme clé

                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Écrit les fournisseurs dans un fichier CSV.
     *
     * @param csvFileName Le nom du fichier CSV dans lequel écrire les fournisseurs.
     */
    private void writeToCSV(String csvFileName) {
        List<String[]> lines = new ArrayList<>();
        String[] headers = {"Nom", "Prenom", "Password", "Email", "Telephone", "Compagnie"};
        lines.add(headers);
        // Ajoute chaque fournisseur à la liste des lignes
        fournisseurMap.values().forEach(fournisseur -> {
            String[] userData = {
                    fournisseur.getNom(),
                    fournisseur.getPrenom(),
                    fournisseur.getPassword(),
                    fournisseur.getEmail(),
                    fournisseur.getTelephone(),
                    fournisseur.getCompagnie()
            };
            lines.add(userData);
        });
        // Écrit les lignes dans le fichier CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            writer.writeAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne la map des fournisseurs.
     *
     * @return La map des fournisseurs.
     */
    public Map<String, Fournisseur> getFournisseurMap() {
        return fournisseurMap;
    }

    /**
     * Vérifie si l'email fourni est valide (c'est-à-dire qu'il n'est pas déjà utilisé par un fournisseur existant).
     *
     * @param email L'email à vérifier.
     * @return true si l'email est valide (non utilisé), sinon false.
     */
    public boolean emailValide(String email) {
        return !fournisseurMap.keySet().contains(email);
    }

    /**
     * Retourne une liste observable des noms de tous les fournisseurs.
     *
     * @return Une liste observable des noms des fournisseurs.
     */
    public ObservableList<String> getFournisseurs() {
        ObservableList<String> fourns = FXCollections.observableArrayList();
        for (Fournisseur f : fournisseurMap.values()) {
            fourns.add(f.getNom());
        }
        return fourns;
    }

    /**
     * Recherche un fournisseur par son nom.
     *
     * @param nom Le nom du fournisseur à rechercher.
     * @return Le fournisseur correspondant au nom, ou null si aucun fournisseur n'est trouvé.
     */
    public Fournisseur getFournisseurByName(String nom) {
        Fournisseur fourn = null;
        for (Fournisseur f : fournisseurMap.values()) {
            if (f.getNom().equals(nom)) {
                fourn = f; break;
            }
        }
        return fourn;
    }

}
