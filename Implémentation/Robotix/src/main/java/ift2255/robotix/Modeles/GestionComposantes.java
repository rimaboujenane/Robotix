package ift2255.robotix.Modeles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

public class GestionComposantes {
    private static final String CSV_FILE = "src/main/resources/data/composantes.csv";
    private List<Composante> mesComposantes = new ArrayList<>();
    private Fournisseur fournisseur = RegisterFournisseur.getInstance().getFournisseur();
    private Map<String, List<Composante>> composantesParFournisseur = new HashMap<>();
    private int nextId = 1; // Initialisation à 1 ou à une autre valeur appropriée

    public GestionComposantes(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
        chargerToutesComposantes();
    }

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


    public List<Composante> chargerComposantes(String fournisseurEmail) {
        List<Composante> composants = composantesParFournisseur.getOrDefault(fournisseurEmail, new ArrayList<>());
        System.out.println("Composants chargés pour " + fournisseurEmail + ": " + composants);
        return composants;
    }

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
                        int prix = Integer.parseInt(nextLine[4]);
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

    public void updateComposante(Composante composanteModifie) {
        List<Composante> composantes = chargerComposantes(composanteModifie.getFournisseurEmail());
        boolean composanteUpdated = false;

        for (int i = 0; i < composantes.size(); i++) {
            if (composantes.get(i).getId() == composanteModifie.getId()) {
                composantes.set(i, composanteModifie);
                composanteUpdated = true;
                break;
            }
        }

        if (composanteUpdated) {
            writeComposantesToCSV();
        } else {
            System.err.println("Le composant avec l'ID " + composanteModifie.getId() + " n'existe pas dans la liste.");
        }
    }

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

    public void supprimerComposante(int id, String fournisseurEmail) {
        List<Composante> composantes = chargerComposantes(fournisseurEmail);
        composantes.removeIf(c -> c.getId() == id);
        writeComposantesToCSV();
        composantesParFournisseur.put(fournisseurEmail, composantes);
    }
}
