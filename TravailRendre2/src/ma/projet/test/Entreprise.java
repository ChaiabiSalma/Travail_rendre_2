
package ma.projet.test;

import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.service.DeveloppeurService;
import ma.projet.service.ManagerService;

public class Entreprise {
    public static void main(String[] args) {
        DeveloppeurService developpeurService = new DeveloppeurService();
        ManagerService managerService = new ManagerService();
        
        //Créer deux développeurs
        Developpeur dev1 = new Developpeur(1,"Développeur 1",3000);
        developpeurService.create(dev1);

        Developpeur dev2 = new Developpeur(2,"Développeur 2",3500);
        developpeurService.create(dev2);

        //Créer un manager qui gère les deux développeurs
        Manager manager = new Manager(3, "Manager", 5000);
        manager.addDeveloppeur(dev1);
        manager.addDeveloppeur(dev2);
        managerService.create(manager);

        //Créer un directeur général qui gère le manager et le 3ème développeur
        Developpeur dev3 = new Developpeur(4, "Développeur 3", 4000);
        developpeurService.create(dev3);
        Manager directeurGeneral = new Manager(5, "Directeur Général", 8000);
        directeurGeneral.addDeveloppeur(manager);
        directeurGeneral.addDeveloppeur(dev3);
        managerService.create(directeurGeneral);
        
        //Afficher les noms et les salaires des employés de haut en bas de la hiérarchie
        afficherHierarchie(directeurGeneral);
    }
    // Méthode récursive pour afficher la hiérarchie des employés
    public static void afficherHierarchie(Manager manager) {
        System.out.println(manager.getNom() + " - Salaire: " + manager.getSalaire() + "€");
        for (Developpeur developpeur : manager.getDeveloppeurs()) {
            System.out.println(developpeur.getNom() + " - Salaire: " + developpeur.getSalaire() + "€");
        }
    }
    
}
