
package ma.projet.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.Developpeur;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class DeveloppeurService implements IDao<Developpeur>{

    @Override
    public boolean update(Developpeur developpeur) {
        try {
            String sql = "UPDATE personne SET nom = ?, salaire = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, developpeur.getNom());
            ps.setDouble(2, developpeur.getSalaire());
            ps.setInt(3, developpeur.getId());
            if(ps.executeUpdate()==1){
                return true;
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Developpeur developpeur) {
        try{
            String sqlD="delete from developpeur where id=?";
            PreparedStatement psD=Connexion.getConnection().prepareStatement(sqlD);
            psD.setInt(1,developpeur.getId());
            psD.executeUpdate();
           
            String sql="delete from personne where id=?";
            PreparedStatement ps=Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1,developpeur.getId());
            if(ps.executeUpdate()==1){
                 return true;
            }    
        
        }catch(SQLException e){
           e.printStackTrace();
        }
        return false;
    }

    @Override
    public Developpeur getById(int id) {
        try{
        String sql="select * from personne where id=? and type_personne='Developpeur'";
        PreparedStatement ps=Connexion.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            return new Developpeur(rs.getInt("id"),rs.getString("nom"),rs.getDouble("salaire"));
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Developpeur> getAll() {
        List<Developpeur> developpeurs=new ArrayList<>();
        try{
          String sql="select * from personne where type_personne='Developpeur'";
          PreparedStatement ps=Connexion.getConnection().prepareStatement(sql);
          ResultSet rs=ps.executeQuery();
          while(rs.next()){
               developpeurs.add(new Developpeur(rs.getInt("id"),rs.getString("nom"),rs.getDouble("salaire")));
          }
        
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return developpeurs;
    }

    @Override
    public boolean create(Developpeur developpeur) {
        try{
            String sql="insert into personne (nom,salaire,type_personne) values(?,?,'Developpeur')";
            PreparedStatement ps=Connexion.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, developpeur.getNom());
            ps.setDouble(2, developpeur.getSalaire());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                int id=rs.getInt(1);
                developpeur.setId(id);
            }
            String sqlD = "INSERT INTO developpeur (id) VALUES (?)";
            PreparedStatement psD = Connexion.getConnection().prepareStatement(sqlD);
            psD.setInt(1, developpeur.getId());
            if(psD.executeUpdate()==1){
                return true;
            }         
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

