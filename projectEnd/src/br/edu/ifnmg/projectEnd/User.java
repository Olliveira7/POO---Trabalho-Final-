
package br.edu.ifnmg.projectEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String cpf;
    private String name;
    private List<String> telephones;
    private String sexo;
    private String user;
    private String password;
    
    public User(){
        this.id = 0;
        this.cpf = "";
        this.name = "";
        this.password = "";
        this.sexo = "";
        this.telephones = new ArrayList<String>();
        this.user = "";
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
    
    public String getSexo(){
        return this.sexo;
    }
    
    public void setSexo(String newSexo){
        this.sexo = newSexo;
    }
    
    public String getUser(){
        return this.user;
    }
    
    public void setUser(String newUser){
        this.user = newUser;
    }
    
    public void addTelephone(String newTelephone){
        if(newTelephone != null && newTelephone.length() == 11)
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
        return "User{" + "cpf=" + cpf + ", name=" + name + ", telefones=" + telephones + ", sexo=" + sexo + ", user=" + user + ", password=" + password + '}';
    }
    
    
}
