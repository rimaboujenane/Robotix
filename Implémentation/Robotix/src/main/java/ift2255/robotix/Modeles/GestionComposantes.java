package ift2255.robotix.Modeles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionComposantes {
    private List<Composante> mesComposantes = new ArrayList<>();
    private Fournisseur fournisseur;
    private static final String CSV_FILE = "src/main/resources/data/composantes.csv/";
    private String fournisseurEmail;


    public GestionComposantes(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
        chargerComposantes(fournisseurEmail);
    }


    public void ajouterComposante(Composante composante) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE, true))) {
            String[] data = {
                    String.valueOf(composante.getId()),
                    composante.getType(),
                    composante.getDescription(),
                    String.valueOf(composante.getPrice()),
                    composante.getFournisseurEmail()
            };
            writer.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'écriture du composant dans le CSV: " + e.getMessage());

        }
    }

    public List<Composante> chargerComposantes(String fournisseurEmail) {
        List<Composante> composantes = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            reader.skip(1); // Ignore l'en-tête du fichier CSV
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length >= 6) { // Vérifie que la ligne contient suffisamment de champs
                    if (nextLine[5].equals(fournisseurEmail)) { // Vérifie que l'email du fournisseur correspond
                        try {
                            int id = Integer.parseInt(nextLine[0]);
                            String nom = nextLine[1];
                            String type = nextLine[2];
                            String description = nextLine[3];
                            int prix = Integer.parseInt(nextLine[4]); // Assurez-vous que le type correspond au constructeur
                            fournisseurEmail = nextLine[5];

                            Composante composante = new Composante(id, nom, type, description, prix, fournisseurEmail);
                            composantes.add(composante); // Ajoute le composant à la liste
                        } catch (NumberFormatException e) {
                            System.err.println("Erreur de format dans les données : " + String.join(",", nextLine));
                        }
                    }
                } else {
                    System.err.println("La ligne ne contient pas assez de champs : " + String.join(",", nextLine));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return composantes; // Retourne la liste des composants
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
                            String.valueOf(composante.getPrice()),
                            composante.getFournisseurEmail()
                    });
                } else {
                    writer.writeNext(new String[]{
                            String.valueOf(c.getId()),
                            c.getNom(),
                            c.getType(),
                            c.getDescription(),
                            String.valueOf(c.getPrice()),
                            c.getFournisseurEmail()
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void supprimerComposante(int ID, String fournisseurEmail) {
        List<Composante> composantes = chargerComposantes(fournisseurEmail);
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            String[] header = {"ID","Nom","Type","Description","Prix","FournisseurEmail"};
            writer.writeNext(header);

            for (Composante c : composantes) {
                if (c.getId() != ID) {
                    writer.writeNext(new String[]{
                            String.valueOf(c.getId()),
                            c.getType(),
                            c.getDescription(),
                            String.valueOf(c.getPrice()),
                            c.getFournisseurEmail()
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
