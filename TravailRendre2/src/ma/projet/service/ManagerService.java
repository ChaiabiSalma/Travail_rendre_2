
package ma.projet.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.Manager;
import ma.projet.beans.Personne;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class ManagerService implements IDao<Manager>{

    @Override
    public boolean update(Manager manager) {
        try {
            String sql = "UPDATE personne SET nom = ?, salaire = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            ps.setInt(3, manager.getId());
            if(ps.executeUpdate()==1){
                return true;
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Manager manager) {
        try{
            String sqlM="delete from manager where id=?";
            PreparedStatement psM=Connexion.getConnection().prepareStatement(sqlM);
            psM.setInt(1,manager.getId());
            psM.executeUpdate();
           
            String sql="delete from personne where id=?";
            PreparedStatement ps=Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1,manager.getId());
            if(ps.executeUpdate()==1){
                 return true;
            }    
        
        }catch(SQLException e){
           e.printStackTrace();
        }
        return false;
    }

    @Override
    public Manager getById(int id) {
        try{
        String sql="select * from personne where id=? and type_personne='Manager'";
        PreparedStatement ps=Connexion.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            return new Manager(rs.getInt("id"),rs.getString("nom"),rs.getDouble("salaire"));
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Manager> getAll() {
        List<Manager> managers=new ArrayList<>();
        try{
          String sql="select * from personne where type_personne='Manager'";
          PreparedStatement ps=Connexion.getConnection().prepareStatement(sql);
          ResultSet rs=ps.executeQuery();
          while(rs.next()){
               managers.add(new Manager(rs.getInt("id"),rs.getString("nom"),rs.getDouble("salaire")));
          }
        
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return managers;
    }

    @Override
    public boolean create(Manager manager) {
        try{
            String sql="insert into personne (nom,salaire,type_personne) values(?,?,'Manager')";
            PreparedStatement ps=Connexion.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            // hnaya ghir kan affectiw l'id pour la table manager walakin ga3ma katinserra 7itach 3adna les 2 tables séparés.
            if(rs.next()){
                int id=rs.getInt(1);
                manager.setId(id);
            }
            String sqlM = "INSERT INTO manager (id) VALUES (?)";
            PreparedStatement psM = Connexion.getConnection().prepareStatement(sqlM);
            psM.setInt(1, manager.getId());
            if(psM.executeUpdate()==1){
                return true;
            }         
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
