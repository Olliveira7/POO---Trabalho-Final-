/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.Client;
import br.edu.ifnmg.projectEnd.Sex;
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
                PreparedStatement sql = db.getConnection().prepareStatement("insert into client(name,cpf,email,street,number_house,neighborhood,sex,status) value(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                sql.setString(1, object.getName());
                sql.setString(2, object.getCpf().replace("-","").replace(".",""));
                sql.setString(3, object.getEmail());
                sql.setString(4, object.getStreet());
                sql.setString(5, object.getNumber_house());
                sql.setString(6, object.getNeighborhood());
                sql.setString(7, object.getSex().name());
                sql.setInt(8, 1);

                if (sql.executeUpdate() > 0){
                    ResultSet id = sql.getGeneratedKeys();
                    id.next();
                    object.setId(id.getInt(1));
                    return true;
                }else{
                    return false;
                }
                
            }else{
                PreparedStatement sql = db.getConnection().prepareStatement("update Client set name = ?, cpf = ?, email = ?, street = ?, number_house = ?, neighborhood = ?, sex = ?, status = ? where id = ?");
                sql.setString(1, object.getName());
                sql.setString(2, object.getCpf().replace("-","").replace(".",""));
                sql.setString(3, object.getEmail());
                sql.setString(4, object.getStreet());
                sql.setString(5, object.getNumber_house());
                sql.setString(6, object.getNeighborhood());
                sql.setString(7, object.getSex().name());
                sql.setInt(8, 1);
                sql.setInt(9, object.getId());
                
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
    
    public List<String> OpenListTelephone(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from client_telephone where client_fk = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            List<String> telephones = new ArrayList<>();
            while(result.next()){
                String telephone = "";
                telephone = result.getString("telephone");
                telephones.add(telephone);
            }
            return telephones;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public List<Client> OpenList(String consult){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement(consult);
            ResultSet result = sql.executeQuery();
            List<Client> list = new ArrayList<>();
            while(result.next()){
                Client client = new Client();
                client.setCpf(result.getString("cpf"));
                client.setName(result.getString("name"));
                client.setEmail(result.getString("email"));
                client.setId(result.getInt("id"));
                client.setNeighborhood(result.getString("neighborhood"));
                client.setNumber(result.getString("number_house"));
                client.setStreet(result.getString("street"));
                list.add(client);
            }
            return list;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public void SaveTelephone(Client client){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("delete from client_telephone client_fk = ?");
            
            sql.setInt(1, client.getId());
            
            String values = "";
            for(String telephone : client.getTelephones()){
                if(values.length() > 0) 
                    values += ", ";
                
                values += "("+client.getId()+",'"+telephone+"')";
            }
            
            Statement sql2 = db.getConnection().createStatement();
            
            sql2.executeUpdate("insert into client_telephone(client_fk, telephone) VALUES " + values);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
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
                client.setEmail(result.getString("email"));
                client.setNeighborhood(result.getString("neighborhood"));
                client.setNumber(result.getString("number_house"));
                client.setSex(Sex.valueOf(result.getString("sex")));
                client.setStreet(result.getString("street"));
                return client;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return client = null;
    }
    
    public Client CheckClientId(Client filtro){
        Client client = new Client();
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from client where id = ?");
            sql.setInt(1, filtro.getId());
            
            ResultSet result = sql.executeQuery();
            
            
            if(result.next() == false){
                client = null;
                return client;
            }else{
                client.setCpf(result.getString("cpf"));
                client.setName(result.getString("name"));
                client.setEmail(result.getString("email"));
                client.setNeighborhood(result.getString("neighborhood"));
                client.setNumber(result.getString("number_house"));
                client.setSex(Sex.valueOf(result.getString("sex")));
                client.setStreet(result.getString("street"));
                client.setId(result.getInt("id"));
                return client;
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return client;
    }
    
    public boolean UpdatePhone(Client client){
        try{
            String value = "";
            
            for(String telephone: client.getTelephones()){
                if(telephone.length() > 0){
                    value += ", ";
                    value += "("+client.getId()+",'"+telephone+"')";
                }
            }
            
            PreparedStatement sql = db.getConnection().prepareStatement("insert into client_telephone(client_fk, telephone) values " + value);
            
            if(sql.executeUpdate() > 0){
                return true;
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public Client SeacheListTelephone(Client client) throws Exception{
        Client clientTest = new Client();
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select telephone from client_telephone where client_fk = ?");
            sql.setInt(1, client.getId());
            ResultSet result = sql.executeQuery();
            while(result.next()){
                clientTest.addTelephone(result.getString("telephone"));
            }
            return clientTest;
        }catch(SQLException ex){
            System.out.println("repositório client "+ex.getMessage());
        }
        client = null;
        return client;
    }
    
    public boolean CheckStatus(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from client where id = ?");
            sql.setInt(1, id);
            
            ResultSet result = sql.executeQuery();
            result.next();
            if(result.getInt("status") == 1){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println("client " + ex.getMessage());
        }
        return false;
    }
    
    public boolean Status(String consult){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement(consult);
            if(sql.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println("status client " + ex.getMessage());
        }
        return false;
    }
    
}

