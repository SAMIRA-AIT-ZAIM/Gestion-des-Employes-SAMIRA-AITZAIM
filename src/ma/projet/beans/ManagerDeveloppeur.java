package ma.projet.beans;

public class ManagerDeveloppeur {
    private Manager manager;
    private Developpeur developpeur;

    // Constructeur
    public ManagerDeveloppeur(Manager manager, Developpeur developpeur) {
        this.manager = manager;
        this.developpeur = developpeur;
    }

    // Getters et Setters
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
        return "ManagerDeveloppeur{" + 
               "manager=" + manager.getNom() + 
               ", developpeur=" + developpeur.getNom() + '}';
    }
}
