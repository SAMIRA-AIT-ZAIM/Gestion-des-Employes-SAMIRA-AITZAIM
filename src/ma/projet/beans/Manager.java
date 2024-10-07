package ma.projet.beans;

import java.util.List;

public class Manager extends Personne {
    private List<Developpeur> developpeurs;  // Ajout de la liste des développeurs

    // Constructeur avec tous les paramètres
    public Manager(int id, String nom, double salaire, List<Developpeur> developpeurs) {
        super(id, nom, salaire);
        this.developpeurs = developpeurs;
    }

    // Constructeur sans liste de développeurs
    public Manager(int id, String nom, double salaire) {
        super(id, nom, salaire);
        this.developpeurs = null; // Ou vous pouvez initialiser une liste vide
    }

    // Getter pour la liste de développeurs
    public List<Developpeur> getDeveloppeurs() {
        return developpeurs;
    }

    // Setter pour la liste de développeurs (si nécessaire)
    public void setDeveloppeurs(List<Developpeur> developpeurs) {
        this.developpeurs = developpeurs;
    }
}
