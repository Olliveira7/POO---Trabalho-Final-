
package br.edu.ifnmg.projectEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String cpf;
    private String name;
    private List<String> telephones;
    private Sex sex;
    private String user;
    private String password;
    private int status;
    
    public User(){
        this.id = 0;
        this.cpf = "";
        this.name = "";
        this.password = "";
        this.sex = Sex.F;
        this.telephones = new ArrayList<String>();
        this.user = "";
        this.status = 1;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int newId){
        this.id = newId;
    }
    
    public String getCpf(){
        return this.cpf;
    }
    
    public void setCpf(String newCpf){
        this.cpf = newCpf;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    
    public Sex getSex(){
        return this.sex;
    }
    
    public void setSex(Sex newSex){
        this.sex = newSex;
    }
    
    public String getUser(){
        return this.user;
    }
    
    public void setUser(String newUser){
        this.user = newUser;
    }
    
    public void addTelephone(String newTelephone){
        if(newTelephone != null && newTelephone.length() >= 11)
            this.telephones.add(newTelephone);
    }
    
    public void removeTelephone(String newTelephone){
        if(this.telephones.contains(newTelephone))
            this.telephones.remove(newTelephone);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.cpf);
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.user);
        hash = 31 * hash + Objects.hashCode(this.password);
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
        final User other = (User) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "User{" + "cpf=" + cpf + ", name=" + name + ", telefones=" + telephones + ", sex=" + sex + ", user=" + user + ", password=" + password + '}';
    }
    
    
}
