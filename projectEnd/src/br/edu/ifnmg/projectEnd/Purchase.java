/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Purchase { //compra
    
    private int id;
    private BigDecimal value_total;
    private List<ItemProvider> itens;
    private Date date;
    private User user;
    private Provider provider;
    
    //tem q ver como manipular o Date
    
    public Purchase(){
        this.id = 0;
        this.value_total = new BigDecimal("0.0");
        this.user = new User();
        this.date = new Date();
        this.provider = new Provider();
        this.itens = new ArrayList<ItemProvider>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public BigDecimal getValue_total(){
        return this.value_total;
    }
    
    public void setValue_total(BigDecimal newValue){
        this.value_total = newValue;
    }
    
    public User getUser(){
        return this.user;
    }
    
    public void setUser(User newUser){
        this.user = newUser;
    }
    
    public Date getDate(){
        return this.date;
    }

    public List<ItemProvider> getItens() {
        return itens;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    public void setDate(Date newDate){
        this.date = newDate;
    }
    
    public void addItem(ItemProvider newItem){
        if(newItem != null){
            this.itens.add(newItem);
        }
    }
    
    public void removeItem(ItemProvider newItem){
        if(this.itens.contains(newItem))
            this.itens.remove(newItem);
    }
   
}
