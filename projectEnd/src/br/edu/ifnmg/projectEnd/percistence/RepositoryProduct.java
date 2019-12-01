/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import br.edu.ifnmg.projectEnd.Product;
import br.edu.ifnmg.projectEnd.UnitPurchase;
import br.edu.ifnmg.projectEnd.UnitSale;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RepositoryProduct {
    private Database db;
    
    public RepositoryProduct(){
        db = new Database();
    }
    
    public boolean Save(Product object){
        try{
            if(object.getId() == 0){
                PreparedStatement sql = db.getConnection().prepareStatement("insert into product(name,purchase_unit,sale_unit,sale_price,purchase_price,description,status) value (?,?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
                sql.setString(1, object.getName());
                sql.setString(2, object.getPurchase_unit().name());
                sql.setString(3, object.getSale_unit().name());
                sql.setBigDecimal(4, object.getSale_price());
                sql.setBigDecimal(5, object.getPurchase_price());
                sql.setString(6, object.getDescription());
                sql.setInt(7, object.getStatus());

                if(sql.executeUpdate() > 0){
                    ResultSet result = sql.getGeneratedKeys();
                    result.next();
                    object.setId(result.getInt(1));
                    RepositoryStock repository = new RepositoryStock();
                    repository.InicializarProduct(object.getId());
                    return true;
                }else{
                    return false;
                }
            }else{
                PreparedStatement sql = db.getConnection().prepareStatement("update product set name = ?, purchase_unit = ?, sale_unit = ?, sale_price = ?, purchase_price = ?, description = ?, status = ? where id = ?");
                sql.setString(1, object.getName());
                sql.setString(2, object.getPurchase_unit().name());
                sql.setString(3, object.getSale_unit().name());
                sql.setBigDecimal(4, object.getSale_price());
                sql.setBigDecimal(5, object.getPurchase_price());
                sql.setString(6, object.getDescription());
                sql.setInt(7, object.getStatus());
                sql.setInt(8, object.getId());
                
                if(sql.executeUpdate() > 0){
                    return true;
                }
                
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
            PreparedStatement sql = db.getConnection().prepareStatement("select * from product where id = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            result.next();
            
            Product product = new Product();
            
            try{
                product.setDescription(result.getString("description"));
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setPurchase_price(result.getBigDecimal("purchase_price"));
                product.setPurchase_unit(UnitPurchase.valueOf(result.getString("purchase_unit")));
                product.setSale_price(result.getBigDecimal("sale_price"));
                product.setSale_unit(UnitSale.valueOf(result.getString("sale_unit")));
                
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
    
    public List<Product> OpenList(String consult){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement(consult);
            ResultSet result = sql.executeQuery();
            
            List<Product> products = new ArrayList();
            
            while(result.next()){
                Product product = new Product();
                
                try{
                    product.setDescription(result.getString("description"));
                    product.setId(result.getInt("id"));
                    product.setName(result.getString("name"));
                    product.setPurchase_price(result.getBigDecimal("purchase_price"));
                    product.setPurchase_unit(UnitPurchase.valueOf(result.getString("purchase_unit")));
                    product.setSale_price(result.getBigDecimal("sale_price"));
                    product.setSale_unit(UnitSale.valueOf(result.getString("sale_unit")));
                }catch(SQLException ex){
                    product = null;
                }
                products.add(product);
                
            }
            return products;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    public boolean CheckProduct(String product){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from product where name = ?");
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
    
    public boolean CheckProductIdName(int id, String product){
        try{
            PreparedStatement sql = db.getConnection().prepareStatement("select * from product where name = ? and id = ?");
            sql.setString(1, product);
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
    
    public boolean Status(int id, String consult){    //Esse método serve para desativar o produto
        try{
            PreparedStatement sql = db.getConnection().prepareStatement(consult +"?");
            sql.setInt(1, id);
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
            PreparedStatement sql = db.getConnection().prepareStatement("select status from product where id = ?");
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
}
