package ift2255.robotix.Modeles;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.HashMap;

public class GestionFournisseurs extends GestionUser{
    private Fournisseur fournisseur;

    public GestionFournisseurs() {
        fournisseurMap = new HashMap<String, Fournisseur>();
        chargerDepuisCSV("src/main/resources/data/fournisseur.csv", false);
    }

    public boolean isValidUser(String email, String password) {
        Fournisseur fournisseur = fournisseurMap.get(email); // Récupère l'utilisateur par email
        if (fournisseur != null && fournisseur.getPassword().equals(password)) {
            this.fournisseur = fournisseur;
            return true;
        }
        return false;
    }
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void updateFournisseur(Fournisseur fournisseurModifie) {
        String email = fournisseurModifie.getEmail(); // Récupérer l'email de l'utilisateur modifié
        if (fournisseurMap.containsKey(email)) {
            // Mettre à jour l'utilisateur dans la HashMap avec les nouvelles informations
            fournisseurMap.put(email, fournisseurModifie);

            // Réécrire tous les utilisateurs dans le fichier CSV après la mise à jour
            writeToCSV("src/main/resources/data/fournisseur.csv", false);
        } else {
            System.err.println("L'utilisateur avec l'email " + email + " n'existe pas dans la liste.");
            // Vous pouvez gérer ce cas comme nécessaire, par exemple, lever une exception ou afficher un message d'erreur
        }
    }

    public void addFournisseur(Fournisseur fournisseur) {
        String email = fournisseur.getEmail();
        fournisseurMap.put(email, fournisseur);
        writeToCSV("src/main/resources/data/fournisseur.csv", false);
    }
    public ObservableList<String> getFournisseurs() {

        ObservableList<String> fourns = FXCollections.observableArrayList();
        for (Fournisseur f : fournisseurMap.values()) {
            fourns.add(f.getNom());
        }
        return fourns;
    }
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
