/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.api.controller;

import br.eti.mts.OSApiApplication.domain.model.Cliente;
import br.eti.mts.OSApiApplication.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List; //interface, a interface ela tem que fazer mas nao implementa, quem implementa é a classe
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    List<Cliente> listaClientes; //criei um objeto

    @GetMapping("/clientes")
    public List<Cliente> listas() { //aqui criei um método que retorna lista<Cliente>

        return clienteRepository.findAll(); // é tudo

    }

    @GetMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {// Long com L maiusculo é que clienteId não pode receber valor nulo
        Optional<Cliente> cliente = clienteRepository.findById(clienteID); //pode ou não estar nulo

        if (cliente.isPresent()) {

            return ResponseEntity.ok(cliente.get());

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);

    }

    @PutMapping("clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID, @RequestBody Cliente cliente) {

        if (!clienteRepository.existsById(clienteID)) { //verifica se clienteID é diferente do id e retorna notfound

            return ResponseEntity.notFound().build();

        }
        cliente.setId(clienteID); // set altera o id do parametro(clienteID)
        cliente = clienteRepository.save(cliente);//aqui salva
        return ResponseEntity.ok(cliente);//aqui retorna
    }
}
