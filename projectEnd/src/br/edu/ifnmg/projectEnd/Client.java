/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private int id;
    private String name;
    private String cpf;
    private List<String> email;
    private Sex sex;
    private List<String> telephones;
    private String neighborhood;
    private String street;
    private String number_house;
    private int status;

    private Pattern regex_cpf = Pattern.compile("\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}");
    
    public Client(){
        this.id = 0;
        this.name = "";
        this.cpf = "";
        this.email = new ArrayList<String>();
        this.sex = Sex.F;
        this.neighborhood = "";
        this.number_house = "";
        this.street = "";
        this.telephones = new ArrayList<String>();
        this.status = 1;
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
    
    public String getCpf(){
        return  cpf.substring(0, 3)+"."+
                cpf.substring(3, 6)+"."+
                cpf.substring(6, 9)+"-"+
                cpf.substring(9, 11);
    }
    
    public void setCpf(String newCpf) throws ExceptionValidationError{
        Matcher m = regex_cpf.matcher(newCpf);
        if(m.matches())
            this.cpf = newCpf.replace(".", "").replace("-", "");
        else
            throw new ExceptionValidationError("CPF Inválido!");
    }
    
    public List<String> getEmail(){
        return this.email;
    }
    
    
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
    
    public String getNeighborhood(){
       return this.neighborhood;
    }
    
    public void setNeighborhood(String newNeighborhood){
        this.neighborhood = newNeighborhood;
    }
    
    public String getStreet(){
        return this.street;
    }
    
    public void setStreet(String newStreet){
        this.street = newStreet;
    }
    
    public String getNumber_house(){
        return this.number_house;
    }
    
    public void setNumber(String newNumber){
        this.number_house = newNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void addEmail(String newEmail){
        if(newEmail != null){
            this.email.add(newEmail);
        }
    }
    
    public void removeEmail(String newEmail){
        if(this.email.contains(newEmail)){
            this.email.remove(newEmail);
        }
    }
    
    public List<String> getTelephones() {
        return telephones;
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
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "name=" + name + ", cpf=" + cpf + ", email=" + email + ", telephones=" + telephones + ", neighborhood=" + neighborhood + ", street=" + street + ", number_house=" + number_house + '}';
    }
    
    
    
}
