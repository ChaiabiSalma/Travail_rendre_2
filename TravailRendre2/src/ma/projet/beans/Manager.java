
package ma.projet.beans;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Personne{
    private List<Developpeur> developpeurs = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();

    public Manager(int id, String nom, double salaire) {
        super(id, nom, salaire);
    }
    public void addDeveloppeur(Developpeur developpeur) {
        developpeurs.add(developpeur);
    }

    public List<Developpeur> getDeveloppeurs() {
        return developpeurs;
    }

    public void addDeveloppeur(Manager manager) {
        managers.add(manager);
    }
}
