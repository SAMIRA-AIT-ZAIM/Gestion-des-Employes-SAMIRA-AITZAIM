package ma.projet.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.DirecteurGeneral;
import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class DirecteurGeneralService implements IDao<DirecteurGeneral> {

    @Override
    public boolean create(DirecteurGeneral dg) {
        try {
            String req = "insert into directeur_general (nom, salaire, manager_id, developpeur_id) values(?, ?, ?, ?)";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setString(1, dg.getNom());
            ps.setDouble(2, dg.getSalaire());
            ps.setInt(3, dg.getManager().getId());
            ps.setInt(4, dg.getDeveloppeur().getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DirecteurGeneralService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(DirecteurGeneral dg) {
        try {
            String req = "update directeur_general set nom = ?, salaire = ?, manager_id = ?, developpeur_id = ? where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setString(1, dg.getNom());
            ps.setDouble(2, dg.getSalaire());
            ps.setInt(3, dg.getManager().getId());
            ps.setInt(4, dg.getDeveloppeur().getId());
            ps.setInt(5, dg.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DirecteurGeneralService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(DirecteurGeneral dg) {
        try {
            String req = "delete from directeur_general where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setInt(1, dg.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DirecteurGeneralService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public DirecteurGeneral getById(int id) {
        DirecteurGeneral dg = null;
        try {
            String req = "select * from directeur_general where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Récupérer les objets Manager et Developpeur associés
                Manager manager = new ManagerService().getById(rs.getInt("manager_id"));
                Developpeur developpeur = new DeveloppeurService().getById(rs.getInt("developpeur_id"));

                dg = new DirecteurGeneral(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"), manager, developpeur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DirecteurGeneralService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dg;
    }

    @Override
    public List<DirecteurGeneral> getAll() {
        List<DirecteurGeneral> directeursGeneraux = new ArrayList<>();
        try {
            String req = "select * from directeur_general";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Récupérer les objets Manager et Developpeur associés
                Manager manager = new ManagerService().getById(rs.getInt("manager_id"));
                Developpeur developpeur = new DeveloppeurService().getById(rs.getInt("developpeur_id"));

                DirecteurGeneral dg = new DirecteurGeneral(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"), manager, developpeur);
                directeursGeneraux.add(dg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DirecteurGeneralService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return directeursGeneraux;
    }
}
