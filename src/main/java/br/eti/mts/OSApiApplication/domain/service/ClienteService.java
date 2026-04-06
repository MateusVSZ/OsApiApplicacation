/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.domain.service;

import br.eti.mts.OSApiApplication.domain.exception.DomainException;
import br.eti.mts.OSApiApplication.domain.model.Cliente;
import br.eti.mts.OSApiApplication.domain.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {

        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        //Lembre-se que o método SAVE pode ser usado para atualizar um cliente também!!
        //ID vazio ---> Novo registro
        //ID Preenchido ---> Alterar existente
        //Verifica se o cliente existe 
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            //lançar exception

            throw new DomainException("Já existe um cliente cadastrado com esse email!!");
            
        }
        return clienteRepository.save(cliente);

    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);

    }

    public List<Cliente> findAll() {
        List<Cliente> result = clienteRepository.findAll();

        return result;

    }

  
}
