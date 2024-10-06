
package ma.projet.beans;

public class Personne {
    int id;
    private String nom;
    private double salaire;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Personne(int id, String nom, double salaire) {
        this.id = id;
        this.nom = nom;
        this.salaire = salaire;
    }
}
