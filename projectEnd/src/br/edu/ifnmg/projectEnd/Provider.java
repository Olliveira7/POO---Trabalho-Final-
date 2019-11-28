/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;

import java.util.ArrayList;
import java.util.List;


public class Provider {
    
    private int id;
    private String cnpj;
    private String name;
    private String reason_social;
    private List<String> telephones;
    private String neighborhood;
    private String street;
    private String number_provider;
    private String email;
    private int status;
    
    public Provider(){
        this.id = 0;
        this.cnpj = "";
        this.email = "";
        this.name = "";
        this.status = 1;
        this.telephones = new ArrayList<String>();
        this.reason_social = "";
        this.neighborhood = "";
        this.number_provider = "";
        this.street = "";
    }

    public int getId() {
        return id;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setTelephones(List<String> newTelephones) {
        this.telephones = newTelephones;
    }

    public void setStatus(int newStatus) {
        this.status = newStatus;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public String getCnpj(){
        return this.cnpj;
    }
    
    public void setCnpj(String newCnpj){
        this.cnpj = newCnpj;
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
    
    public void addTelephone(String telephone)throws Exception{
        if(telephone.length() >= 11 && telephone != null){
            this.telephones.add(telephone);
        }else{
            throw new Exception("Lower than allowed number of phones");
        }
    }    
    public void removeTelephone(String telephone){
        if(this.telephones.contains(telephone))
            this.telephones.remove(telephone);
    }

    @Override
    public String toString() {
        return "Provider{" + "id=" + id + ", cnpj=" + cnpj + ", name=" + name + ", reason_social=" + reason_social + ", telephones=" + telephones + ", neighborhood=" + neighborhood + ", street=" + street + ", number_provider=" + number_provider + ", email=" + email + ", status=" + status + '}';
    }

    
    
    
}
