package ma.projet.fenetre;

import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import ma.projet.beans.Developpeur;
import ma.projet.beans.DirecteurGeneral;
import ma.projet.beans.Manager;

public class GestionEmployesUI extends JFrame {

    private JTree tree;

    public GestionEmployesUI(DirecteurGeneral dg) {
        setTitle("Gestion des Employés");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Vérifier que le Directeur Général n'est pas null
        if (dg == null || dg.getManager() == null) {
            JOptionPane.showMessageDialog(this, "Données du Directeur Général ou du Manager manquantes.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Créer le modèle d'arbre
        DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Employés");

        // Créer les nœuds pour la hiérarchie
        DefaultMutableTreeNode directeurGeneralNode = new DefaultMutableTreeNode("Directeur Général : " + dg.getNom());
        Manager manager = dg.getManager();
        DefaultMutableTreeNode managerNode = new DefaultMutableTreeNode("Manager : " + manager.getNom());

        // Ajouter les développeurs au manager
        List<Developpeur> developpeurs = manager.getDeveloppeurs();
        if (developpeurs != null && !developpeurs.isEmpty()) {
            for (Developpeur dev : developpeurs) {
                DefaultMutableTreeNode devNode = new DefaultMutableTreeNode("Développeur : " + dev.getNom());
                managerNode.add(devNode);
            }
        } else {
            managerNode.add(new DefaultMutableTreeNode("Aucun développeur"));
        }

        // Ajouter le manager et le troisième développeur au directeur général
        directeurGeneralNode.add(managerNode);
        if (dg.getDeveloppeur() != null) {
            DefaultMutableTreeNode dev3Node = new DefaultMutableTreeNode("Développeur : " + dg.getDeveloppeur().getNom());
            directeurGeneralNode.add(dev3Node);
        }

        // Ajouter le nœud racine
        racine.add(directeurGeneralNode);

        // Créer le JTree avec le modèle
        tree = new JTree(racine);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Personnaliser l'apparence du JTree
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        tree.setCellRenderer(renderer);

        // Ajouter le JTree à un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tree);
        getContentPane().add(scrollPane);
    }

    public static void main(String[] args) {
        // Créer les employés comme dans la classe Entreprise
        Developpeur dev1 = new Developpeur(1, "Dev1", 3000);
        Developpeur dev2 = new Developpeur(2, "Dev2", 3500);
        Developpeur dev3 = new Developpeur(3, "Dev3", 4000);
        Manager manager = new Manager(1, "Manager1", 5000, Arrays.asList(dev1, dev2));
        DirecteurGeneral dg = new DirecteurGeneral(1, "Directeur Général", 8000, manager, dev3);

        // Lancer l'interface utilisateur
        SwingUtilities.invokeLater(() -> {
            GestionEmployesUI frame = new GestionEmployesUI(dg);
            frame.setVisible(true);
        });
    }
}
