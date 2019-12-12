/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.ItemProvider;
import br.edu.ifnmg.projectEnd.Provider;
import br.edu.ifnmg.projectEnd.Purchase;
import br.edu.ifnmg.projectEnd.Sale;
import br.edu.ifnmg.projectEnd.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Diego
 */
public class RepositoryPurchase {
    private Database db;
    
    public RepositoryPurchase(){
        db = new Database();
    }
    
    public boolean SavePurchase(Purchase purchase){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("insert into purchase(value_total,user_fk,provider_fk,date) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            sql.setBigDecimal(1, purchase.getValue_total());
            sql.setInt(2, purchase.getUser().getId());
            sql.setInt(3, purchase.getProvider().getId());
            sql.setDate(4, new java.sql.Date(purchase.getDate().getTime()));
            
            if(sql.executeUpdate() > 0){
                ResultSet result = sql.getGeneratedKeys();
                result.next();
                purchase.setId(result.getInt(1));
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public void addItem(Purchase purchase){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("delete from purchase_item purchase_fk = ?");
            
            sql.setInt(1, purchase.getId());
            
            String values = "";
            for(ItemProvider item : purchase.getItens()){
                if(values.length() > 0) 
                    values += ", ";
                
                values += "("+purchase.getId()+","+item.getProduct().getId()+","+item.getAmount()+","+item.getValue()+")";
            }
            
            Statement sql2 = db.getConnection().createStatement();
            
            sql2.executeUpdate("insert into purchase_item(purchase_fk, product_fk, amount, value) VALUES " + values); 
        }catch(Exception ex){
            System.out.println("item " + ex.getMessage());
        }
    }
    
    public List<Purchase> OpenList(){
        try{
            RepositoryProduct repositoryProduct = new RepositoryProduct();
            RepositoryProvider repositoryProvider = new RepositoryProvider();
            RepositoryUser repositoryUser = new RepositoryUser();
            PreparedStatement sql = db.getConnection().prepareStatement("select * from purchase");
            ResultSet result = sql.executeQuery();
            List<Purchase> purchases = new ArrayList<>();
            while(result.next()){
                Purchase purchase = new Purchase();
                purchase.setDate(result.getDate("date"));
                purchase.setId(result.getInt("id"));
                purchase.setProvider(repositoryProvider.Open(result.getInt("provider_fk")));
                purchase.setUser(repositoryUser.Open(result.getInt("user_fk")));
                purchase.setValue_total(result.getBigDecimal("value_total"));
                purchases.add(purchase);
            }
            return purchases;
        }catch(Exception ex){
            System.out.println("problemas com o purchase: " + ex.getMessage());
        }
        return null;
    }
    
    public List<Purchase> OpenListId(int id, int check){
        try{
            RepositoryProduct repositoryProduct = new RepositoryProduct();
            RepositoryProvider repositoryProvider = new RepositoryProvider();
            RepositoryUser repositoryUser = new RepositoryUser();
            String consult = new String();
            if(check == 1){
                consult = "select * from purchase where provider_fk = ?";
            }
            if(check == 2){
                consult = "select * from purchase where user_fk = ?";
            }
            PreparedStatement sql = db.getConnection().prepareStatement(consult);
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            List<Purchase> purchases = new ArrayList<>();
            while(result.next()){
                Purchase purchase = new Purchase();
                purchase.setDate(result.getDate("date"));
                purchase.setId(result.getInt("id"));
                purchase.setProvider(repositoryProvider.Open(result.getInt("provider_fk")));
                purchase.setUser(repositoryUser.Open(result.getInt("user_fk")));
                purchase.setValue_total(result.getBigDecimal("value_total"));
                purchases.add(purchase);
            }
            return purchases;
        }catch(Exception ex){
            System.out.println("problemas com o purchase: " + ex.getMessage());
        }
        return null;
    }
    
    
}
