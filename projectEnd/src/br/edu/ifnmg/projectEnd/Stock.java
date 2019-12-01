/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;



public class Stock {
    
    private int amount;
    private Product product;
    
    public Stock(){
        this.amount = 0;
        this.product = new Product();
    }
    
    public int getAmount(){
        return this.amount;
    }
    
    public void setAmount(int newAmount){
        this.amount = newAmount;
    }
    
    public Product getProduct(){
        return this.product;
    }
    
    public void setProduct(Product newProduct){
        this.product = newProduct;
    }

    @Override
    public String toString() {
        return "Stock{" + "amount=" + amount + ", product=" + product + '}';
    }
    
    
}
