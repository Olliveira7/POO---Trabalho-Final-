/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;

import java.util.ArrayList;
import java.util.List;


public class Provider {

    private String Cnpj;
    private String name;
    private String reason_social;
    private List<String> telefones;
    private String neighborhood;
    private String street;
    private String number_provider;
    private String email;
    
    public Provider(){
        this.Cnpj = "";
        this.email = "";
        this.name = "";
        this.telefones = new ArrayList<String>();
        this.reason_social = "";
        this.neighborhood = "";
        this.number_provider = "";
        this.street = "";
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public String getCnpj(){
        return this.Cnpj;
    }
    
    public void setCpf(String newCnpj){
        this.Cnpj = newCnpj;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    
    public String getNeighborhood(){
       return this.neighborhood;
    }
    
    public void setNeighborhood(String newNeighborhood){
        this.neighborhood = newNeighborhood;
    }
    
    public String getReason_social(){
        return this.reason_social;
    }
    
    public void setReason_social(String newReason_social){
        this.reason_social = newReason_social;
    }
     
    public String getStreet(){
        return this.street;
    }
    
    public void setStreet(String newStreet){
        this.street = newStreet;
    }
    
    public String getNumber_providor(){
        return this.number_provider;
    }
    
    public void setNumber_provider(String newNumber){
        this.number_provider = newNumber;
    }
    
    public void addTelefone(String telefone){
        if(telefone.length() == 11 && telefone != null)
            this.telefones.add(telefone);
    }
    
    public void removeTelefone(String telefone){
        if(this.telefones.contains(telefone))
            this.telefones.remove(telefone);
    }
    
    
}
