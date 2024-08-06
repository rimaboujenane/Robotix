package ift2255.robotix.Modeles;

import ift2255.robotix.Modeles.RegisterUtilisateur;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

/**
 * Gestion des robots par les utilisateurs. Cette classe permet d'ajouter, de modifier,
 * de voir l'état des robots à partir d'un fichier CSV.
 */
public class GestionRobots {

    private static final String CSV_FILE = "src/main/resources/data/robots.csv";
    private Map<String, List<Robot>> flotteParUtilisateur = new HashMap<>();
    private int nextId = 1; // Initialisation à 1.
    private Utilisateur user;

    /**
     * Constructeur pour initialiser la classe et charger les robots depuis le fichier CSV.
     */
    public GestionRobots() {
        this.user = RegisterUtilisateur.getInstance().getUtilisateur();
        chargerTousLesRobots();
    }

    /**
     * Ajoute un nouveau robot à la flotte de l'utilisateur courant et l'enregistre dans le fichier CSV.
     *
     * @param robot Le robot à ajouter.
     */
    public void ajouterRobot(Robot robot) {
        // On génère un ID unique pour le nouveau robot
        int id = getNextId();
        robot.setId(id);

        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE, true))) {
            String[] data = {
                    String.valueOf(robot.getId()),
                    robot.getNumeroSerie(),
                    robot.getNom(),
                    robot.getType(),
                    robot.getPosition(),
                    String.valueOf(robot.getVitesse()),
                    String.valueOf(robot.getNiveauBatterie()),
                    String.valueOf(robot.getConsommationCPU()),
                    String.valueOf(robot.getConsommationMemoire()),
                    user.getEmail()
            };
            writer.writeNext(data);
            flotteParUtilisateur
                    .computeIfAbsent(user.getEmail(), k -> new ArrayList<>())
                    .add(robot);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'ajout du robot dans le fichier CSV: " + e.getMessage());
        }
    }

    /**
     * Génère un nouvel identifiant unique pour un robot.
     *
     * @return Le prochain identifiant disponible.
     */
    public synchronized int getNextId() {
        int maxId = 0;
        for (List<Robot> robots : flotteParUtilisateur.values()) {
            for (Robot robot : robots) {
                if (robot.getId() > maxId) {
                    maxId = robot.getId();
                }
            }
        }
        return maxId + 1;
    }

    /**
     * Charge tous les robots à partir du fichier CSV et les stocke dans une carte par utilisateur.
     */
    private void chargerTousLesRobots() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            reader.skip(1); // Ignore l'entête du fichier CSV
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length >= 10) { // Vérifie que la ligne contient assez de champs
                    try {
                        int id = Integer.parseInt(nextLine[0]);
                        String numeroDeSerie = nextLine[1];
                        String nom = nextLine[2];
                        String type = nextLine[3];
                        String position = nextLine[4];
                        double vitesse = Double.parseDouble(nextLine[5]);
                        int niveauBatterie = Integer.parseInt(nextLine[6]);
                        double consommationCPU = Double.parseDouble(nextLine[7]);
                        double consommationMemoire = Double.parseDouble(nextLine[8]);
                        String utilisateurEmail = user.getEmail();

                        Robot robot = new Robot(id, numeroDeSerie, nom, type, position, vitesse, niveauBatterie, consommationCPU, consommationMemoire);
                        flotteParUtilisateur
                                .computeIfAbsent(utilisateurEmail, k -> new ArrayList<>())
                                .add(robot);

                        // Met à jour le prochain ID disponible
                        if (id >= nextId) {
                            nextId = id + 1;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format dans les données : " + String.join(",", nextLine));
                    }
                } else {
                    System.err.println("La ligne ne contient pas assez de champs: " + String.join(",", nextLine));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Écrit tous les robots dans le fichier CSV.
     */
    private void writeRobotsToCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            String[] header = {"ID", "NumeroDeserie", "Nom", "Type", "Position", "Vitesse", "NiveauBatterie", "ConsommationCPU", "ConsommationMemoire", "UtilisateurEmail"};
            writer.writeNext(header);

            for (List<Robot> robots : flotteParUtilisateur.values()) {
                for (Robot robot : robots) {
                    writer.writeNext(new String[]{
                            String.valueOf(robot.getId()),
                            robot.getNumeroSerie(),
                            robot.getNom(),
                            robot.getType(),
                            robot.getPosition(),
                            String.valueOf(robot.getVitesse()),
                            String.valueOf(robot.getNiveauBatterie()),
                            String.valueOf(robot.getConsommationCPU()),
                            String.valueOf(robot.getConsommationMemoire()),
                            user.getEmail()
                    });
                }
            }

            System.out.println("Écriture dans le fichier CSV réussie : " + CSV_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge les robots pour l'utilisateur courant à partir de la carte des robots.
     *
     * @return Une liste de robots pour l'utilisateur spécifié.
     */
    public List<Robot> chargerRobots() {
        List<Robot> robots = flotteParUtilisateur.getOrDefault(user.getEmail(), new ArrayList<>());
        System.out.println("Robots appartenant à " + user.getEmail() + ": " + robots);
        return robots;
    }

    /**
     * Supprime un robot du système et du fichier CSV.
     *
     * @param id               L'identifiant du robot à supprimer.
     * @param utilisateurEmail L'email de l'utilisateur auquel appartient le robot.
     */
    public void supprimerRobot(int id, String utilisateurEmail) {
        List<Robot> robots = chargerRobots();
        robots.removeIf(c -> c.getId() == id);
        writeRobotsToCSV();
        flotteParUtilisateur.put(user.getEmail(), robots);
    }

    /**
     * Affiche l'état complet d'un robot.
     *
     * @param id L'identifiant du robot.
     * @return Les informations du robot sous forme de chaîne de caractères.
     */
    public String afficherEtatRobotComplet(int id) {
        List<Robot> robots = chargerRobots();
        for (Robot robot : robots) {
            if (robot.getId() == id) {
                return robot.toString();
            }
        }
        return "Robot non trouvé.";
    }

    /**
     * Affiche un état général d'un robot.
     *
     * @param id L'identifiant du robot.
     * @return Les informations générales du robot sous forme de chaîne de caractères.
     */
    public String afficherEtatRobotGeneral(int id) {
        List<Robot> robots = chargerRobots();
        for (Robot robot : robots) {
            if (robot.getId() == id) {
                return "Nom: " + robot.getNom() + ", Type: " + robot.getType() + ", Position: " + robot.getPosition(); // Affichage général des informations du robot
            }
        }
        return "Robot non trouvé.";
    }

    /**
     * Récupère un robot par son nom et l'email de l'utilisateur.
     *
     * @param nom              Le nom du robot.
     * @param utilisateurEmail L'email de l'utilisateur.
     * @return Le robot correspondant ou null s'il n'est pas trouvé.
     */
    public Robot getRobotByName(String nom, String utilisateurEmail) {
        List<Robot> robots = chargerRobots();
        for (Robot robot : robots) {
            if (robot.getNom().equals(nom)) {
                return robot;
            }
        }
        return null;
    }
}
