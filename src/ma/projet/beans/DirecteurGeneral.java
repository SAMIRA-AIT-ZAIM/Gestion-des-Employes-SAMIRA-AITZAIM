package ma.projet.beans;

public class DirecteurGeneral {
    private int id;
    private String nom;
    private double salaire;
    private Manager manager;  // Relation avec la classe Manager
    private Developpeur developpeur;  // Relation avec la classe Developpeur

    // Constructeur
    public DirecteurGeneral(int id, String nom, double salaire, Manager manager, Developpeur developpeur) {
        this.id = id;
        this.nom = nom;
        this.salaire = salaire;
        this.manager = manager;
        this.developpeur = developpeur;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Developpeur getDeveloppeur() {
        return developpeur;
    }

    public void setDeveloppeur(Developpeur developpeur) {
        this.developpeur = developpeur;
    }

    @Override
    public String toString() {
        return "DirecteurGeneral{" + 
               "id=" + id + 
               ", nom='" + nom + '\'' + 
               ", salaire=" + salaire + 
               ", manager=" + manager.getNom() + 
               ", developpeur=" + developpeur.getNom() + '}';
    }
}
