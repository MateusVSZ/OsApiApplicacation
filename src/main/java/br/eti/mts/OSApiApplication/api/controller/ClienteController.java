/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.api.controller;

import br.eti.mts.OSApiApplication.domain.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */

@RestController
public class ClienteController {
    List<Cliente> listaClientes;
    
    @GetMapping("/clientes")
    public List<Cliente> listas(){
    
        
    //lista em formato Json
    listaClientes = new ArrayList<Cliente>();
    listaClientes.add(new Cliente(1, "MTS", "mateus@teste.com",  "11-99999-9999"));
    listaClientes.add(new Cliente(1, "Maria", "maria@teste.com", "11-88888-8888"));
    listaClientes.add(new Cliente(1, "João", "joao@teste.com", "11-77777-7777"));
    return listaClientes;
    
    
    }
}
    

