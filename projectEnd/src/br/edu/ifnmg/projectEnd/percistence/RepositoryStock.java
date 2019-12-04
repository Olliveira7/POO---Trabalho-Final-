/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.Product;
import br.edu.ifnmg.projectEnd.Stock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class RepositoryStock {
    private Database db;
    
    public RepositoryStock(){
        db = new Database();
    }
    
    public boolean Save(Stock stock, int check){//a variável check serve para mostrar se vai adcionar ou retirar
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from stock where product_fk = ?");
            sql.setInt(1, stock.getProduct().getId());
            ResultSet result = sql.executeQuery();
            result.next();
            int amount = result.getInt("amount");
            Statement sql2 = db.getConnection().createStatement();
            if(check == 1){
                if(sql2.executeUpdate("update stock set amount = "+(stock.getAmount() + amount)+" where product_fk = "+ stock.getProduct().getId()) > 0){
                    return true;
                }else{
                    return false;
                }
            }
            if(check == 0){
                if(sql2.executeUpdate("update stock set amount = "+(amount - stock.getAmount())+" where product_fk = "+ stock.getProduct().getId()) > 0){
                    return true;
                }else{
                    return false;
                }
            }
        }catch(Exception ex){
            System.out.println("save "+ ex.getMessage());
        }
        return false;
    }
    
    public boolean InicializarProduct(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("insert into stock(product_fk,amount) value(?,?)");
            sql.setInt(1, id);
            sql.setInt(2, 0);
            if(sql.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println("stock " + ex.getMessage());
        }
        return false;
    }
    
    public int CheckAmount(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from stock where product_fk = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            result.next();
            int amount = result.getInt("amount");
            return amount;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public List<Stock> OpenList(){
        try{
            RepositoryProduct repositoryProduct = new RepositoryProduct();
            PreparedStatement sql = db.getConnection().prepareStatement("select * from stock");
            ResultSet result = sql.executeQuery();
            List<Stock> stocks = new ArrayList<>();
            while(result.next()){
                Stock stock = new Stock();
                stock.setAmount(result.getInt("amount"));
                Product product = repositoryProduct.Open(result.getInt("product_fk"));
                stock.setProduct(product);
                stocks.add(stock);
            }
            return stocks;
        }catch(Exception ex){
            System.out.println("problemas com o stock" + ex.getMessage());
        }
        return null;
    }
    
}
