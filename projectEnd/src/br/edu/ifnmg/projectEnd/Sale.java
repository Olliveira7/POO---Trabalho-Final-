/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Sale {
    
    private float value_total;
    private List<Item> itens;
    private Date date;
    private Client client;
    
    //tem q ver como manipular o Date
    
    public Sale(){
        this.value_total = 0;
        this.client = new Client();
        this.date = new Date();
        this.itens = new ArrayList<Item>();
    }
    
    public float getValue_total(){
        return this.value_total;
    }
    
    public void setValue_total(float newValue){
        this.value_total = newValue;
    }
    
    public Client getClient(){
        return this.client;
    }
    
    public void setUser(Client newClient){
        this.client = newClient;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public void setDate(Date newDate){
        this.date = newDate;
    }
    
    public void addItem(Item newItem){
        if(newItem != null){
            this.itens.add(newItem);
        }
    }
    
    public void removeItem(Item newItem){
        if(this.itens.contains(newItem))
            this.itens.remove(newItem);
    }
   
}
