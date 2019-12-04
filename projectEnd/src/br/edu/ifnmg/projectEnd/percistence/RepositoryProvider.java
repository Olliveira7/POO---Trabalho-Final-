/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.Provider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class RepositoryProvider {
    private Database db;
    
    public RepositoryProvider(){
        db = new Database();
    }
    
    public boolean Save(Provider object){
        try{
            if(object.getId() == 0){
                PreparedStatement sql = db.getConnection().prepareStatement("insert into provider(name,cnpj,reason_social,street,number_provider,neighborhood,status,email) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                sql.setString(1, object.getName());
                sql.setString(2, object.getCnpj());
                sql.setString(3, object.getReason_social());
                sql.setString(4, object.getStreet());
                sql.setString(5, object.getNumber_providor());
                sql.setString(6, object.getNeighborhood());
                sql.setInt(7, object.getStatus());
                sql.setString(8, object.getEmail());
                

                if (sql.executeUpdate() > 0){
                    ResultSet id = sql.getGeneratedKeys();
                    id.next();
                    object.setId(id.getInt(1));
                    return true;
                }else{
                    return false;
                }
                
            }else{
                PreparedStatement sql = db.getConnection().prepareStatement("update provider set name = ?, cnpj = ?, reason_social = ?, street = ?, number_provider = ?, neighborhood = ?, status = ?, email = ? where id = ?");
                sql.setString(1, object.getName());
                sql.setString(2, object.getCnpj());
                sql.setString(3, object.getReason_social());
                sql.setString(4, object.getStreet());
                sql.setString(5, object.getNumber_providor());
                sql.setString(6, object.getNeighborhood());
                sql.setInt(7, object.getStatus());
                sql.setString(8, object.getEmail());
                sql.setInt(9, object.getId());
                
                if(sql.executeUpdate() > 0){
                    return true;
                }else{
                    return false;
                }
                
            }                                                               

        }catch(SQLException ex){
            System.out.println("Repositorio provider " + ex.getMessage());
        }
        return false;
    }
    
    public Provider Open(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from provider where id = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            result.next();
            
            Provider provider = new Provider(); 
            
            try{
                provider.setId(id);
                provider.setCnpj(result.getString("cnpj"));
                provider.setEmail(result.getString("email"));
                provider.setName(result.getString("name"));
                provider.setNeighborhood(result.getString("neighborhood"));
                provider.setNumber_provider(result.getString("number_provider"));
                provider.setReason_social(result.getString("reason_social"));
                provider.setStreet(result.getString("street"));
            }catch(SQLException ex){
                provider = null;
                System.out.println(ex.getMessage());
            }
            return provider;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean Check(String product){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from provider where name = ?");
            sql.setString(1, product);
            ResultSet result = sql.executeQuery();
            if(result.next() == false){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean CheckProvider(String provider, int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from provider where name = ? and id = ?");
            sql.setString(1, provider);
            sql.setInt(2, id);
            ResultSet result = sql.executeQuery();
            if(result.next() == false){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean CheckProviderC(String provider, String cnpj){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from provider where name = ? and cnpj = ?");
            sql.setString(1, provider);
            sql.setString(2, cnpj);
            ResultSet result = sql.executeQuery();
            if(result.next() == false){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public void SaveTelephone(Provider provider){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("delete from client_provider provider_fk = ?");
            
            sql.setInt(1, provider.getId());
            
            String values = "";
            for(String telephone : provider.getTelephones()){
                if(values.length() > 0) 
                    values += ", ";
                
                values += "("+provider.getId()+",'"+telephone+"')";
            }
            
            Statement sql2 = db.getConnection().createStatement();
            
            sql2.executeUpdate("insert into provider_telephone(provider_fk, telephone) values " + values);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean DeleteStatus(String consult){    //Esse método serve para desativar o produto
        try{
            PreparedStatement sql = db.getConnection().prepareStatement(consult);
            if(sql.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean CheckStatus(int id){
    
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select status from provider where id = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            result.next();
            if(result.getInt("status") == 1){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
    
    public List<Provider> OpenList(String consult){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement(consult);
            ResultSet result = sql.executeQuery();
            
            List<Provider> providers = new ArrayList();
            
            while(result.next()){
                Provider provider = new Provider();
                String cnpj = result.getString("cnpj");   
                try{
                    provider.setCnpj(cnpj);
                    provider.setId(result.getInt("id"));
                    provider.setEmail(result.getString("email"));
                    provider.setName(result.getString("name"));
                    provider.setNeighborhood(result.getString("neighborhood"));
                    provider.setNumber_provider(result.getString("number_provider"));
                    provider.setReason_social(result.getString("reason_social"));
                    provider.setStreet(result.getString("street"));
                }catch(SQLException ex){
                    provider = null;
                    System.out.println("Opem List: " + ex.getMessage());
                }
                providers.add(provider);
                
            }
            return providers;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
