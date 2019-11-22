/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;


import br.edu.ifnmg.projectEnd.percistence.Database;
import br.edu.ifnmg.projectEnd.percistence.repository_client;
public class ProjectEnd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Client client = new Client();
        
        repository_client repo = new repository_client();
        repo.Delete(4);
//        System.out.println(client.toString());
        
//        client.setCpf("111.222.333-40");
//        client.setEmail("diego.oli");
//        client.setName("diego");
//        client.setNeighborhood("eldorado");
//        client.setNumber("170");
//        client.setStreet("p");
//        repository_client repo = new repository_client();
//        repo.Save(client);
//        
//        System.out.println(client.getId());
    }
    
}
