package ma.projet.test;

import ma.projet.beans.Developpeur;
import ma.projet.beans.DirecteurGeneral;
import ma.projet.beans.Manager;
import ma.projet.service.DeveloppeurService;
import ma.projet.service.DirecteurGeneralService;
import ma.projet.service.ManagerService;

import java.util.ArrayList;
import java.util.List;

public class Entreprise {
    public static void main(String[] args) {
        // Tester DeveloppeurService
        DeveloppeurService developpeurService = new DeveloppeurService();
        Developpeur dev1 = new Developpeur(0, "John Doe", 4500.0); // ID=0, la base de données le générera

        // Créer un développeur
        if (developpeurService.create(dev1)) {
            System.out.println("Développeur créé avec succès !");
        } else {
            System.out.println("Erreur lors de la création du développeur.");
        }

        // Lire le développeur
        Developpeur fetchedDev = developpeurService.getById(dev1.getId());
        if (fetchedDev != null) {
            System.out.println("Développeur récupéré : " + fetchedDev.getNom());
        } else {
            System.out.println("Développeur non trouvé.");
        }

        // Mettre à jour le développeur
        dev1.setSalaire(5000.0);
        if (developpeurService.update(dev1)) {
            System.out.println("Salaire du développeur mis à jour !");
        } else {
            System.out.println("Erreur lors de la mise à jour du développeur.");
        }

        // Supprimer le développeur
        if (developpeurService.delete(dev1)) {
            System.out.println("Développeur supprimé avec succès.");
        } else {
            System.out.println("Erreur lors de la suppression du développeur.");
        }

        // Tester ManagerService
        ManagerService managerService = new ManagerService();
        List<Developpeur> developpeurs = new ArrayList<>(); // Créer une liste de développeurs
        developpeurs.add(dev1); // Ajouter le développeur à la liste
        
        // Créer le Manager avec une liste vide d'abord pour éviter les erreurs de clé étrangère
        Manager manager = new Manager(0, "Jane Smith", 8000.0, new ArrayList<>()); // ID=0

        // Créer un manager
        if (managerService.create(manager)) {
            System.out.println("Manager créé avec succès !");
        } else {
            System.out.println("Erreur lors de la création du manager.");
        }

        // Ajouter les développeurs au manager après qu'il a été créé
        managerService.addDeveloppeursToManager(manager, developpeurs); // Utilisez l'objet Manager au lieu de l'ID

        // Lire le manager
        Manager fetchedManager = managerService.getById(manager.getId());
        if (fetchedManager != null) {
            System.out.println("Manager récupéré : " + fetchedManager.getNom());
        } else {
            System.out.println("Manager non trouvé.");
        }

        // Mettre à jour le manager
        manager.setSalaire(8500.0);
        if (managerService.update(manager)) {
            System.out.println("Salaire du manager mis à jour !");
        } else {
            System.out.println("Erreur lors de la mise à jour du manager.");
        }

        // Supprimer le manager
        if (managerService.delete(manager)) {
            System.out.println("Manager supprimé avec succès.");
        } else {
            System.out.println("Erreur lors de la suppression du manager.");
        }

        // Tester DirecteurGeneralService
        DirecteurGeneralService dgService = new DirecteurGeneralService();
        DirecteurGeneral dg = new DirecteurGeneral(0, "Alice Brown", 15000.0, manager, dev1); // ID=0

        // Créer un directeur général
        if (dgService.create(dg)) {
            System.out.println("Directeur Général créé avec succès !");
        } else {
            System.out.println("Erreur lors de la création du directeur général.");
        }

        // Lire le directeur général
        DirecteurGeneral fetchedDG = dgService.getById(dg.getId());
        if (fetchedDG != null) {
            System.out.println("Directeur Général récupéré : " + fetchedDG.getNom());
        } else {
            System.out.println("Directeur Général non trouvé.");
        }

        // Mettre à jour le directeur général
        dg.setSalaire(16000.0);
        if (dgService.update(dg)) {
            System.out.println("Salaire du directeur général mis à jour !");
        } else {
            System.out.println("Erreur lors de la mise à jour du directeur général.");
        }

        // Supprimer le directeur général
        if (dgService.delete(dg)) {
            System.out.println("Directeur Général supprimé avec succès.");
        } else {
            System.out.println("Erreur lors de la suppression du directeur général.");
        }
    }
}
