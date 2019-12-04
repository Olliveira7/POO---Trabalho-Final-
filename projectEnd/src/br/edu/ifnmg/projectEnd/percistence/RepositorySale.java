/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.Item;
import br.edu.ifnmg.projectEnd.Sale;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class RepositorySale {
    private Database db;
    
    public RepositorySale(){
        this.db = new Database();
    }
    
    public boolean Save(Sale sale){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("insert into sale(value_total,user_fk,client_fk,date) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            sql.setBigDecimal(1, sale.getValue_total());
            sql.setInt(2, sale.getUser().getId());
            sql.setInt(3, sale.getClient().getId());
            sql.setDate(4, new java.sql.Date(sale.getDate().getTime()));
            
            if(sql.executeUpdate() > 0){
                ResultSet result = sql.getGeneratedKeys();
                result.next();
                sale.setId(result.getInt(1));
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println("save sale" + ex.getMessage());
        }
        return false;
    }
    
    public void addItem(Sale sale){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("delete from sale_item sale_fk = ?");
            
            sql.setInt(1, sale.getId());
            
            String values = "";
            for(Item item : sale.getItens()){
                if(values.length() > 0) 
                    values += ", ";
                
                values += "("+sale.getId()+","+item.getProduct().getId()+","+item.getAmount()+","+item.getValue()+")";
            }
            
            Statement sql2 = db.getConnection().createStatement();
            
            sql2.executeUpdate("insert into sale_item(sale_fk, product_fk, amount, value) VALUES " + values); 
        }catch(Exception ex){
            System.out.println("item " + ex.getMessage());
        }
    }
    
    public List<Sale> OpenList(){
        try{
            RepositoryProduct repositoryProduct = new RepositoryProduct();
            RepositoryClient repositoryClient = new RepositoryClient();
            RepositoryUser repositoryUser = new RepositoryUser();
            PreparedStatement sql = db.getConnection().prepareStatement("select * from sale");
            ResultSet result = sql.executeQuery();
            List<Sale> sales = new ArrayList<>();
            while(result.next()){
                Sale sale = new Sale();
                sale.setDate(result.getDate("date"));
                sale.setId(result.getInt("id"));
                sale.setClient(repositoryClient.Open(result.getInt("client_fk")));
                sale.setUser(repositoryUser.Open(result.getInt("user_fk")));
                sale.setValue_total(result.getBigDecimal("value_total"));
                sales.add(sale);
            }
            return sales;
        }catch(Exception ex){
            System.out.println("problemas com o purchase: " + ex.getMessage());
        }
        return null;
    }
    
}
