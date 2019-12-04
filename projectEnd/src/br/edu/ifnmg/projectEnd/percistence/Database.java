/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd.percistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private Connection connection;
    
    public Database(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/poo_job","teste","victoriagabriella1999");
        }catch(ClassNotFoundException ex){
            System.out.println("Driver não conectou com o banco de dados " + ex.getMessage());
        }catch(SQLException ex){
            System.out.println("Os dados com a conexão com o banco de dados estão erradas");
            System.out.println(ex.getMessage());
        }
    }
    //Poque com a variável connection não se usa o this
    public Connection getConnection(){
        return connection;
    }
    
    
}
