/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Purchase { //compra
    
    private float value_total;
    private List<ItemProvider> itens;
    private Date date;
    private User user;
    private Provider provider;
    
    //tem q ver como manipular o Date
    
    public Purchase(){
        this.value_total = 0;
        this.user = new User();
        this.date = new Date();
        this.itens = new ArrayList<ItemProvider>();
    }
    
    public float getValue_total(){
        return this.value_total;
    }
    
    public void setValue_total(float newValue){
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
