/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;


public class Product {
    private String name;
    private int purchase_unit;    //Unidade de compra    
    private int selling_unit;   //Unidade de venda
    private int id;
    private float sale_price;   //preço de venda
    private float purchase_price;   //preço de compra 
    private String description;   //descrição do produto
    
    //Dúvida: Existe algum tipo de atributo que pode ser usado para texto
    
    public Product(){
        this.id = 0;
        this.name = "";
        this.purchase_unit = 0;
        this.purchase_price = 0;
        this.selling_unit = 0;
        this.sale_price = 0;
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
    
    public int getPurchase_unit(){
        return this.purchase_unit;
    }
    
    public void setPurchase_unit(int newPurchase_unit){
        this.purchase_unit = newPurchase_unit;
    }
    
    public int getSelling_unit(){
        return this.selling_unit;
    }
    
    public void setSelling_unit(int newSelling_unit){
        this.selling_unit = newSelling_unit;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    
    public float getSale_price(){
        return this.sale_price;
    }
    
    public void setSale_price(float newSale_price){
        this.sale_price = newSale_price;
    }
    
    public float getPurchase_price(){
        return this.purchase_price;
    }
    
    public void setPurchase_price(float newPurchase_price){
        this.purchase_price = newPurchase_price;
    }
}
