/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;

import java.math.BigDecimal;


public class Product {
    private String name;
    private String purchase_unit;    //Unidade de compra    
    private String sale_unit;   //Unidade de venda
    private int id;
    private BigDecimal sale_price;   //preço de venda
    private BigDecimal purchase_price;   //preço de compra 
    private String description;   //descrição do produto
    
    //Dúvida: Existe algum tipo de atributo que pode ser usado para texto
    
    public Product(){
        this.id = 0;
        this.name = "";
        this.purchase_unit = "";
        this.purchase_price = new BigDecimal("0.0");
        this.sale_unit = "";
        this.sale_price = new BigDecimal("0.0");
        this.description = "";
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int newId){
        this.id = newId;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public String getPurchase_unit(){
        return this.purchase_unit;
    }
    
    public void setPurchase_unit(String newPurchase_unit){
        this.purchase_unit = newPurchase_unit;
    }
    
    public String getSelling_unit(){
        return this.sale_unit;
    }
    
    public void setSelling_unit(String newSale_unit){
        this.sale_unit = newSale_unit;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    
    public BigDecimal getSale_price(){
        return this.sale_price;
    }
    
    public void setSale_price(BigDecimal newSale_price){
        this.sale_price = newSale_price;
    }
    
    public BigDecimal getPurchase_price(){
        return this.purchase_price;
    }
    
    public void setPurchase_price(BigDecimal newPurchase_price){
        this.purchase_price = newPurchase_price;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", purchase_unit=" + purchase_unit + ", selling_unit=" + sale_unit + ", id=" + id + ", sale_price=" + sale_price + ", purchase_price=" + purchase_price + ", description=" + description + '}';
    }

    
}
