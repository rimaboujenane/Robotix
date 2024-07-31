package ift2255.robotix.Modeles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionComposantes {
    private List<Composante> mesComposantes = new ArrayList<>();
    private Fournisseur fournisseur = RegisterFournisseur.getInstance().getFournisseur();
    private static final String CSV_FILE = "src/main/resources/data/composantes.csv";
    private Map<String, List<Composante>> composantesParFournisseur = new HashMap<>();

    public GestionComposantes(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
        chargerToutesComposantes();
    }

    public void ajouterComposante(Composante composante) {
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
            System.out.println("Erreur lors de l'écriture du composant dans le CSV: " + e.getMessage());
        }
    }

    public List<Composante> chargerComposantes(String fournisseurEmail) {
        return composantesParFournisseur.getOrDefault(fournisseurEmail, new ArrayList<>());
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

    public void modifierComposante(Composante composante) {
        List<Composante> composantes = chargerComposantes(composante.getFournisseurEmail());
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            String[] header = {"ID", "Nom", "Type", "Description", "Prix", "FournisseurEmail"};
            writer.writeNext(header);

            for (Composante c : composantes) {
                if (c.getId() == composante.getId()) {
                    writer.writeNext(new String[]{
                            String.valueOf(composante.getId()),
                            composante.getNom(),
                            composante.getType(),
                            composante.getDescription(),
                            String.valueOf(composante.getPrix()),
                            composante.getFournisseurEmail()
                    });
                } else {
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

            composantesParFournisseur.put(composante.getFournisseurEmail(), composantes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void supprimerComposante(int ID, String fournisseurEmail) {
        List<Composante> composantes = chargerComposantes(fournisseurEmail);
        composantes.removeIf(c -> c.getId() == ID);
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            String[] header = {"ID", "Nom", "Type", "Description", "Prix", "FournisseurEmail"};
            writer.writeNext(header);

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

            composantesParFournisseur.put(fournisseurEmail, composantes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
