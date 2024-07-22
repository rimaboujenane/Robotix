package ift2255.robotix.Modeles;

import java.util.HashMap;

public class GestionUtilisateurs extends GestionUser{
    private Utilisateur utilisateur;

    public GestionUtilisateurs() {
        utilisateursMap = new HashMap<>();
        chargerUserDepuisCSV("src/main/resources/data/utilisateur.csv");
    }

    /**
     * Vérifie si un utilisateur avec l'email et le mot de passe fournis existe dans la HashMap.
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

    public void updateUtilisateur(Utilisateur utilisateurModifie) {
        String email = utilisateurModifie.getEmail(); // Récupérer l'email de l'utilisateur modifié
        if (utilisateursMap.containsKey(email)) {
            // Mettre à jour l'utilisateur dans la HashMap avec les nouvelles informations
            utilisateursMap.put(email, utilisateurModifie);

            // Réécrire tous les utilisateurs dans le fichier CSV après la mise à jour
            writeUtilisateursToCSV("src/main/resources/data/utilisateur.csv");
        } else {
            System.err.println("L'utilisateur avec l'email " + email + " n'existe pas dans la liste.");
            // Vous pouvez gérer ce cas comme nécessaire, par exemple, lever une exception ou afficher un message d'erreur
        }
    }
    public void addUtilisateur(Utilisateur utilisateur) {
        String email = utilisateur.getEmail();
        utilisateursMap.put(email, utilisateur);
        writeUtilisateursToCSV("src/main/resources/data/utilisateur.csv");
    }
}
