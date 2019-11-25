/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class repository_user {
    private Database db;
    
    public repository_user(){
        db = new Database();
    }
    
    public boolean Save(User objeto){
        try{
            if(objeto.getId() == 0){
                PreparedStatement sql = db.getConnection().prepareStatement("insert into user(name,cpf,password,sexo,user) value(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                sql.setString(1, objeto.getName());
                sql.setString(2, objeto.getCpf().replace("-","").replace(".",""));
                sql.setString(3, objeto.getPassword());
                sql.setString(4, objeto.getSexo());
                sql.setString(5, objeto.getUser());

                if (sql.executeUpdate() > 0){
                    ResultSet id = sql.getGeneratedKeys();
                    id.next();
                    objeto.setId(id.getInt(1));
                    return true;
                }else{
                    return false;
                }
                
            }else{
                PreparedStatement sql = db.getConnection().prepareStatement("update Client set name = ?, cpf = ?, password = ?, sexo = ?, user = ?");
                sql.setString(1, objeto.getName());
                sql.setString(2, objeto.getCpf().replace("-","").replace(".",""));
                sql.setString(3, objeto.getPassword());
                sql.setString(4, objeto.getSexo());
                sql.setString(5, objeto.getUser());
                
            }                                                               

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public User Open(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from user where id = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            result.next();
            
            User user = new User();
            
            try{
                user.setCpf(result.getString("cpf"));
                user.setName(result.getString("name"));
                user.setId(result.getInt("id"));
                user.setPassword(result.getString("password"));
                user.setSexo(result.getString("sexo"));
                user.setUser(result.getString("user"));
            }catch(Exception ex){
                user = null;
                System.out.println("Deu problema nas especifiações do banco: " + ex.getMessage());
            }
            
            return user;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean Delete(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("delete from user where id = ?");
            sql.setInt(1,id);
            
            if(sql.executeUpdate() > 0){
                return true;
            }else return false;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean SaveTelephoneUni(int id, String telephone){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("insert into user_telephone(user_fk,telephone) value(?,?)");
            sql.setInt(1, id);
            sql.setString(2, telephone);
            
            if(sql.executeUpdate() > 0){
                return true;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public void OpenTelephone(User user){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select telephone from user_telephone where id = ?");
            sql.setInt(1, user.getId());
            
            ResultSet result = sql.executeQuery();
            
            while(result.next()){
                user.addTelephone(result.getString("telephone"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
