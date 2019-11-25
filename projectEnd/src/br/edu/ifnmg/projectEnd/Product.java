/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;


public class Product {
    private String name;
    private String purchase_unit;    //Unidade de compra    
    private String selling_unit;   //Unidade de venda
    private int id;
    private float sale_price;   //preço de venda
    private float purchase_price;   //preço de compra 
    private String description;   //descrição do produto
    
    //Dúvida: Existe algum tipo de atributo que pode ser usado para texto
    
    public Product(){
        this.id = 0;
        this.name = "";
        this.purchase_unit = "";
        this.purchase_price = 0;
        this.selling_unit = "";
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
    
    public String getPurchase_unit(){
        return this.purchase_unit;
    }
    
    public void setPurchase_unit(String newPurchase_unit){
        this.purchase_unit = newPurchase_unit;
    }
    
    public String getSelling_unit(){
        return this.selling_unit;
    }
    
    public void setSelling_unit(String newSelling_unit){
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

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", purchase_unit=" + purchase_unit + ", selling_unit=" + selling_unit + ", id=" + id + ", sale_price=" + sale_price + ", purchase_price=" + purchase_price + ", description=" + description + '}';
    }

    
}
