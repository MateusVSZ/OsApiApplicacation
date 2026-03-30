/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.api.controller;

import br.eti.mts.OSApiApplication.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List; //interface, a interface ela tem que fazer mas nao implementa, quem implementa é a classe
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */

@RestController
public class ClienteController {
    
    @PersistenceContext
    private EntityManager manager;   //Entity é o CRUD do banco a diferença que
    List<Cliente> listaClientes; //criei um objeto
    
    @GetMapping("/clientes")
    public List<Cliente> listas(){ //aqui criei um método que retorna lista<Cliente>
     
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
                                              
        
 
    }
   }

    

