package ma.projet.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class ManagerService implements IDao<Manager> {

    @Override
    public boolean create(Manager m) {
        try {
            String req = "INSERT INTO manager (nom, salaire) VALUES (?, ?)";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNom());
            ps.setDouble(2, m.getSalaire());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    m.setId(generatedKeys.getInt(1));  // Met à jour l'ID du manager

                    // Vérifie si la liste des développeurs n'est pas nulle ou vide avant de les ajouter
                    if (m.getDeveloppeurs() != null && !m.getDeveloppeurs().isEmpty()) {
                        addDeveloppeursToManager(m, m.getDeveloppeurs());
                    }
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Manager o) {
        try {
            String req = "UPDATE manager SET nom = ?, salaire = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setDouble(2, o.getSalaire());
            ps.setInt(3, o.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Manager o) {
        try {
            String req = "DELETE FROM manager WHERE id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Manager getById(int id) {
        Manager manager = null;
        try {
            String req = "SELECT * FROM manager WHERE id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                manager = new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manager;
    }

    @Override
    public List<Manager> getAll() {
        List<Manager> managers = new ArrayList<>();
        try {
            String req = "SELECT * FROM manager";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Manager emp = new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
                managers.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return managers;
    }

    public boolean addDeveloppeursToManager(Manager manager, List<Developpeur> developpeurs) {
        if (developpeurs == null || developpeurs.isEmpty()) {
            return false;  // Ne rien faire si la liste est vide ou nulle
        }

        DeveloppeurService developpeurService = new DeveloppeurService(); // Créez une instance de votre service

        try {
            String req = "INSERT INTO manager_developpeur (manager_id, developpeur_id) VALUES (?, ?)";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);

            for (Developpeur dev : developpeurs) {
                // Vérifiez si le développeur existe avant d'ajouter
                if (!developpeurService.exists(dev.getId())) {
                    System.out.println("Développeur avec ID " + dev.getId() + " n'existe pas.");
                } else {
                    ps.setInt(1, manager.getId());
                    ps.setInt(2, dev.getId());
                    ps.executeUpdate();
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, "Erreur lors de l'ajout du développeur au manager", ex);
        }
        return false;
    }
}
