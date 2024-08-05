package ift2255.robotix.Modeles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

/**
 * Gestion des composantes pour les fournisseurs. Cette classe permet d'ajouter, de modifier,
 * de supprimer et de charger des composantes à partir d'un fichier CSV.
 */
public class GestionComposantes {
    private static final String CSV_FILE = "src/main/resources/data/composantes.csv";
    private Map<String, List<Composante>> composantesParFournisseur = new HashMap<>();
    private int nextId = 1; // Initialisation à 1 ou à une autre valeur appropriée

    /**
     * Constructeur pour initialiser la gestion des composantes avec un fournisseur donné.
     *
     */
    public GestionComposantes() {
        chargerToutesComposantes();
    }

    /**
     * Ajoute une nouvelle composante au système et l'enregistre dans le fichier CSV.
     *
     * @param composante La composante à ajouter.
     */
    public void ajouterComposante(Composante composante) {
        // Générer un ID unique pour le nouveau composant
        int id = getNextId();
        composante.setId(id);

        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE, true))) {
            String[] data = {
                    String.valueOf(composante.getId()),
                    composante.getNom(),
                    composante.getType(),
                    composante.getDescription(),
                    String.valueOf(composante.getPrix()),
                    composante.getFournisseurEmail()
            };
            writer.writeNext(data);
            composantesParFournisseur
                    .computeIfAbsent(composante.getFournisseurEmail(), k -> new ArrayList<>())
                    .add(composante);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'écriture du composant dans le CSV: " + e.getMessage());
        }
    }

    /**
     * Génère un nouvel identifiant unique pour une composante.
     *
     * @return Le prochain identifiant disponible.
     */
    private int getNextId() {
        int maxId = 0;
        for (List<Composante> composants : composantesParFournisseur.values()) {
            for (Composante composante : composants) {
                if (composante.getId() > maxId) {
                    maxId = composante.getId();
                }
            }
        }
        return maxId + 1;
    }

    /**
     * Charge les composantes pour un fournisseur donné à partir du fichier CSV.
     *
     * @param fournisseurEmail L'email du fournisseur dont les composantes doivent être chargées.
     * @return Une liste de composantes pour le fournisseur spécifié.
     */
    public List<Composante> chargerComposantes(String fournisseurEmail) {
        List<Composante> composants = composantesParFournisseur.getOrDefault(fournisseurEmail, new ArrayList<>());
        System.out.println("Composants chargés pour " + fournisseurEmail + ": " + composants);
        return composants;
    }

    /**
     * Charge toutes les composantes à partir du fichier CSV et les stocke dans une carte par fournisseur.
     */
    private void chargerToutesComposantes() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            reader.skip(1); // Ignore l'en-tête du fichier CSV
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length >= 6) { // Vérifie que la ligne contient suffisamment de champs
                    try {
                        int id = Integer.parseInt(nextLine[0]);
                        String nom = nextLine[1];
                        String type = nextLine[2];
                        String description = nextLine[3];
                        double prix = Double.parseDouble(nextLine[4]);
                        String fournisseurEmail = nextLine[5];

                        Composante composante = new Composante(id, nom, type, description, prix, fournisseurEmail);
                        composantesParFournisseur
                                .computeIfAbsent(fournisseurEmail, k -> new ArrayList<>())
                                .add(composante);

                        // Mettre à jour le prochain ID disponible
                        if (id >= nextId) {
                            nextId = id + 1;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format dans les données : " + String.join(",", nextLine));
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
     * Met à jour une composante existante dans le système et enregistre les modifications dans le fichier CSV.
     *
     * @param composanteModifie La composante modifiée.
     */
    public void updateComposante(Composante composanteModifie) {
        boolean composanteUpdated = false;

        for (List<Composante> composantes : composantesParFournisseur.values()) {
            for (int i = 0; i < composantes.size(); i++) {
                if (composantes.get(i).getId() == composanteModifie.getId()) {
                    composantes.set(i, composanteModifie);
                    composanteUpdated = true;
                    break;
                }
            }
        }

        if (composanteUpdated) {
            writeComposantesToCSV();
        } else {
            System.err.println("Le composant avec l'ID " + composanteModifie.getId() + " n'existe pas.");
        }
    }

    /**
     * Écrit toutes les composantes dans le fichier CSV.
     */
    private void writeComposantesToCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            String[] header = {"ID", "Nom", "Type", "Description", "Prix", "FournisseurEmail"};
            writer.writeNext(header);

            for (List<Composante> composantes : composantesParFournisseur.values()) {
                for (Composante c : composantes) {
                    writer.writeNext(new String[]{
                            String.valueOf(c.getId()),
                            c.getNom(),
                            c.getType(),
                            c.getDescription(),
                            String.valueOf(c.getPrix()),
                            c.getFournisseurEmail()
                    });
                }
            }

            System.out.println("Écriture dans le fichier CSV réussie : " + CSV_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime une composante du système et du fichier CSV.
     *
     * @param id               L'identifiant de la composante à supprimer.
     * @param fournisseurEmail L'email du fournisseur de la composante.
     */
    public void supprimerComposante(int id, String fournisseurEmail) {
        List<Composante> composantes = chargerComposantes(fournisseurEmail);
        composantes.removeIf(c -> c.getId() == id);
        writeComposantesToCSV();
        composantesParFournisseur.put(fournisseurEmail, composantes);
    }
    public Map<String, List<Composante>> getComposantesParFournisseur() {
        return composantesParFournisseur;
    }
}
