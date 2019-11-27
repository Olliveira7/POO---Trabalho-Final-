/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositoryClient {
    
    private Database db;
    
    public RepositoryClient(){
        db = new Database();
    }
    
    public boolean Save(Client object){
        try{
            if(object.getId() == 0){
                PreparedStatement sql = db.getConnection().prepareStatement("insert into client(name,cpf,email,street,number_house,neighborhood,sex) value(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                sql.setString(1, object.getName());
                sql.setString(2, object.getCpf().replace("-","").replace(".",""));
                sql.setString(3, object.getEmail());
                sql.setString(4, object.getStreet());
                sql.setString(5, object.getNumber_house());
                sql.setString(6, object.getNeighborhood());
                sql.setString(7, object.getSex().name());

                if (sql.executeUpdate() > 0){
                    ResultSet id = sql.getGeneratedKeys();
                    id.next();
                    object.setId(id.getInt(1));
                    return true;
                }else{
                    return false;
                }
                
            }else{
                PreparedStatement sql = db.getConnection().prepareStatement("update Client set name = ?, cpf = ?, email = ?, street = ?, number_house = ?, neighborhood = ?, sex = ? where id = ?");
                sql.setString(1, object.getName());
                sql.setString(2, object.getCpf().replace("-","").replace(".",""));
                sql.setString(3, object.getEmail());
                sql.setString(4, object.getStreet());
                sql.setString(5, object.getNumber_house());
                sql.setString(6, object.getNeighborhood());
                sql.setString(8, object.getSex().name());
                sql.setInt(7, object.getId());
                
                if(sql.executeUpdate() > 0){
                    return true;
                }else{
                    return false;
                }
                
            }                                                               

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public Client Open(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from client where id = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            result.next();
            
            Client client = new Client();
            
            try{
                client.setCpf(result.getString("cpf"));
                client.setName(result.getString("name"));
                client.setEmail(result.getString("email"));
                client.setId(result.getInt("id"));
                client.setNeighborhood(result.getString("neighborhood"));
                client.setNumber(result.getString("number_house"));
                client.setStreet(result.getString("street"));
            }catch(Exception ex){
                client = null;
                System.out.println("Deu problema nas especifiações do banco: " + ex.getMessage());
            }
            
            return client;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean Delete(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("delete from client where id = ?");
            sql.setInt(1,id);
            
            if(sql.executeUpdate() > 0){
                return true;
            }else return false;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public List<Client> SeacheList(Client filter){
        try{
            String where = "";
            
            if(filter != null){
                if(filter.getName() != null && !filter.getName().isEmpty())
                    where += "name like '%"+filter.getName() + "%'";
                if(filter.getCpf() != null && !filter.getCpf().isEmpty() && !"000.000.000-00".equals(filter.getCpf())){
                    if(where.length() > 0){
                        where += " and ";
                    where += "cpf = '"+filter.getCpf().replace(".", "").replace("-", "") + "'";
                    }
                }
            }
            
            String consulta = "select * from client";
            
            if(where.length() > 0){
                consulta += " where " + where;
            
            PreparedStatement sql = db.getConnection().prepareStatement(consulta);
            
            ResultSet result = sql.executeQuery();
            
            List<Client> clients = new ArrayList<>();
            
            while(result.next()){
                Client client = new Client();
                
                try{
                    client.setCpf(result.getString("cpf"));
                    client.setName(result.getString("name"));
                    client.setEmail(result.getString("email"));
                    client.setId(result.getInt("id"));
                    client.setNeighborhood(result.getString("neighborhood"));
                    client.setNumber(result.getString("number_house"));
                    client.setStreet(result.getString("street")); 
                }catch(Exception ex){
                    client = null;
                }
                clients.add(client);
            }
            return clients;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean SaveTelephoneUni(int id, String telephone){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("insert into client_Telephone(client_fk,telephone) value(?,?)");
            sql.setInt(1,id);
            sql.setString(2,telephone);
            
            if(sql.executeUpdate() > 0){
                return true;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
    public Client CheckClient(Client filtro){
        Client client = new Client();
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from client where name = ? and cpf = ?");
            sql.setString(1, filtro.getName());
            sql.setString(2, filtro.getCpf());
            
            ResultSet result = sql.executeQuery();
            
            if(result.next() == false){
                client = null;
                return client;
            }else{
                client.setCpf(result.getString("cpf"));
                client.setName(result.getString("name"));
                return client;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return client;
    }
    
}

