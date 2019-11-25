/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.projectEnd;


import br.edu.ifnmg.projectEnd.percistence.Database;
import br.edu.ifnmg.projectEnd.percistence.RepositoryProduct;
import br.edu.ifnmg.projectEnd.percistence.repository_client;
import br.edu.ifnmg.projectEnd.percistence.repository_user;
import java.util.ArrayList;
import java.util.List;
public class ProjectEnd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Product product = new Product();
        
        RepositoryProduct repo = new RepositoryProduct();
        product = repo.Open(2);
        System.out.println(product.toString());
        
//        user.setCpf("123.456.789-99");
//        user.setName("Diego");
//        user.setPassword("vic");
//        user.setSexo("M");
//        user.setUser("Admin1");
        
        
//        Client client = new Client();
//        client.setCpf("111.222.333-40");
//        client.setEmail("diego.oli");
//        client.setName("diego");
//        client.setNeighborhood("eldorado");
//        client.setNumber("170");
//        client.setStreet("I");
//        List<Client> clients = new ArrayList<>();
//          repository_client repo = new repository_client();
//          repo.SalvaTelephoneUni(2,"(38)99918777");
//        clients = repo.SeacheList(client);
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

//        for (Client u : clients){
//            System.out.println(u.toString());
//        }
    }
    
}
