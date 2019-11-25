/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class RepositoryProduct {
    private Database db;
    
    public RepositoryProduct(){
        db = new Database();
    }
    
    public boolean Save(Product object){
        try{
            if(object.getId() == 0){
                PreparedStatement sql = db.getConnection().prepareStatement("insert into product(name,purchase_unit,selling_unit,sale_price,purchase_price,description) value (?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
                sql.setString(1, object.getName());
                sql.setString(2, object.getPurchase_unit());
                sql.setString(3, object.getSelling_unit());
                sql.setFloat(4, object.getSale_price());
                sql.setFloat(5, object.getPurchase_price());
                sql.setString(6, object.getDescription());

                if(sql.executeUpdate() > 0){
                    ResultSet result = sql.getGeneratedKeys();
                    result.next();
                    object.setId(result.getInt(1));
                    return true;
                }else{
                    return false;
                }
            }else{
                PreparedStatement sql = db.getConnection().prepareStatement("update product set name = ?, purchase_unit = ?, selling_unit = ?, sale_price = ?, purchase_price = ?, description = ? where id = ?");
                sql.setString(1, object.getName());
                sql.setString(2, object.getPurchase_unit());
                sql.setString(3, object.getSelling_unit());
                sql.setFloat(4, object.getSale_price());
                sql.setFloat(5, object.getPurchase_price());
                sql.setString(6, object.getDescription());
                sql.setInt(7, object.getId());
                System.out.println("oiii");
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean Delete(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("delete from product where id = ?");
            sql.setInt(1,id);
            
            if(sql.executeUpdate() > 0){
                return true;
            }else return false;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public Product Open(int id){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from product");
            ResultSet result = sql.executeQuery();
            result.next();
            
            Product product = new Product();
            
            try{
                product.setDescription(result.getString("description"));
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setPurchase_price(result.getFloat("purchase_price"));
                product.setPurchase_unit(result.getString("purchase_unit"));
                product.setSale_price(result.getFloat("sale_price"));
                product.setSelling_unit(result.getString("selling_unit"));
                
            }catch(SQLException ex){
                product = null;
                System.out.println(ex.getMessage());
            }
            return product;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
