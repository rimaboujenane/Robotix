package ift2255.robotix.Modeles;

import java.util.HashMap;

public class GestionFournisseurs extends GestionUser{
    private Fournisseur fournisseur;

    public GestionFournisseurs() {
        fournisseurMap = new HashMap<String, Fournisseur>();
        chargerFournisseurDepuisCSV("src/main/resources/data/fournisseur.csv");
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
            writeFournisseursToCSV("src/main/resources/data/fournisseur.csv");
        } else {
            System.err.println("L'utilisateur avec l'email " + email + " n'existe pas dans la liste.");
            // Vous pouvez gérer ce cas comme nécessaire, par exemple, lever une exception ou afficher un message d'erreur
        }
    }

    public void addFournisseur(Fournisseur fournisseur) {
        String email = fournisseur.getEmail();
        fournisseurMap.put(email, fournisseur);
        writeFournisseursToCSV("src/main/resources/data/fournisseur.csv");
    }



}
