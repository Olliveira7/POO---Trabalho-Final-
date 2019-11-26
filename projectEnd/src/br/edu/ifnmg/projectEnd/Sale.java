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



public class Sale {
    
    private BigDecimal value_total;
    private List<Item> itens;
    private Date date;
    private Client client;
    private User user;
    
    //tem q ver como manipular o Date
    
    public Sale(){
        this.value_total = new BigDecimal("0,0");
        this.client = new Client();
        this.date = new Date();
        this.itens = new ArrayList<Item>();
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User newUser) {
        this.user = newUser;
    }
    
    public BigDecimal getValue_total(){
        return this.value_total;
    }
    
    public void setValue_total(BigDecimal newValue){
        this.value_total = newValue;
    }
    
    public Client getClient(){
        return this.client;
    }
    
    public void setClient(Client newClient){
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

    @Override
    public String toString() {
        return "Sale{" + "value_total=" + value_total + ", itens=" + itens + ", date=" + date + ", client=" + client + ", user=" + user + '}';
    }

    
    
}
