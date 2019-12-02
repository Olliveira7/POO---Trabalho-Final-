/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;

import java.math.BigDecimal;



public class Item {
    private Product product;
    //A minha unidade deve ter uma enumeração
    private int amount;
    private BigDecimal value;
    
    public Item(){
        this.product = new Product();
        // Inicializar a unidade
        this.amount = 0;
        this.value = new BigDecimal("0.0");
    }
    
    public Product getProduct(){
        return this.product;
    }
    
    public void setProduct(Product newProduct){
        this.product = newProduct;
    }
    
    public int getAmount(){
        return this.amount;
    }
    
    public void setAmount(int newAmount){
        this.amount = newAmount;
    }
    
    public BigDecimal getValue(){
        return this.value;
    }
    
    public void setValue(BigDecimal newValue){
        this.value = newValue;
    }
}
