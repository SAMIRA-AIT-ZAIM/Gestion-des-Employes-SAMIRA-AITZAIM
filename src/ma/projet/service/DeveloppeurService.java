package ma.projet.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Developpeur;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class DeveloppeurService implements IDao<Developpeur> {

    @Override
    public boolean create(Developpeur o) {
        try {
            String req = "insert into developpeur (nom, salaire) values(?, ?)";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req); // Correction ici
            ps.setString(1, o.getNom());
            ps.setDouble(2, o.getSalaire());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Developpeur o) {
        try {
            String req = "update developpeur set nom = ?, salaire = ? where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req); // Correction ici
            ps.setString(1, o.getNom());
            ps.setDouble(2, o.getSalaire());
            ps.setInt(3, o.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Developpeur o) {
        try {
            String req = "delete from developpeur where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req); // Correction ici
            ps.setInt(1, o.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Developpeur getById(int id) {
        Developpeur developpeur = null;
        try {
            String req = "select * from developpeur where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req); // Correction ici
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                developpeur = new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return developpeur;
    }

    @Override
    public List<Developpeur> getAll() {
        List<Developpeur> developpeurs = new ArrayList<>();
        try {
            String req = "select * from developpeur";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req); // Correction ici
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Developpeur emp = new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
                developpeurs.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return developpeurs;
    }

    boolean exists(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
