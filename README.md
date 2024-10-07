# Gestion des Employes-SAMIRA-AITZAIM
Gestion des employés
Présentation
Cette application Java permet de gérer la hiérarchie et les informations des employés d'une petite entreprise. Elle offre la possibilité de :

Stocker les données des employés (nom, salaire, rôle) dans une base de données MySQL.
Gérer la hiérarchie entre les employés (directeur, manager, développeurs).
Afficher les informations des employés de manière hiérarchisée.
Architecture technique
Langage : Java
Base de données : MySQL
Framework/Bibliothèque : JDBC pour l'accès à la base de données
Structure
ma.projet.connexion : Classe de connexion à la base de données.
ma.projet.beans : Classes représentant les entités (Personne, Manager, Développeur).
ma.projet.dao : Interface et implémentations pour les opérations de base de données (CRUD).
ma.projet.service : Classes de service pour les opérations métier.
ma.projet.test : Classe de test pour créer et afficher les employés.
Fonctionnalités principales
Création d'employés de différents types (directeur, manager, développeur).
Gestion des relations hiérarchiques entre les employés.
Affichage des informations des employés, ordonnées selon la hiérarchie.
Installation et utilisation
Configurer le fichier base.properties avec les informations de connexion à votre base de données MySQL.
Importer le projet dans votre IDE.
Exécuter la classe Entreprise pour créer les employés et afficher les informations.
https://github.com/user-attachments/assets/0b541c58-3f42-4ec0-8381-9921365b56c5
https://github.com/user-attachments/assets/510e07f7-8189-45ee-a6ef-0805dce9a6b7



 
