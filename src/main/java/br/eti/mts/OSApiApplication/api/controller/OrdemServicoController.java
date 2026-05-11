/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.api.controller;

import br.eti.mts.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.eti.mts.OSApiApplication.domain.model.Cliente;
import br.eti.mts.OSApiApplication.domain.model.Comentario;
import br.eti.mts.OSApiApplication.domain.model.OrdemServico;
import br.eti.mts.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.mts.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.mts.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.persistence.Id;

import br.eti.mts.OSApiApplication.domain.model.OrdemServico;
import br.eti.mts.OSApiApplication.domain.repository.ComentarioRepository;
import br.eti.mts.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.mts.OSApiApplication.domain.service.ComentarioService;
import br.eti.mts.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
 
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Autowired
    private ComentarioService comentarioService;
  
    /**
     * Retorna todas as OS
     * Com Cliente
     * Com Comentarios
     * @return 
     */
    @GetMapping
    public List<OrdemServico> findAll() {
        return ordemServicoService.findAll();
    }

    
    /**
     * Retorna todas as OS POR Cliente
     * 
     * @param clienteId
     * @return 
     */
    @GetMapping("/{clienteId}") //ordem de serviço por Id
    public ResponseEntity<OrdemServico> busca(@PathVariable Long clienteId) {

        Optional<OrdemServico> cliente = ordemServicoRepository.findById(clienteId);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/clientes/{clienteId}") //busca todas as ordens de servico por clienteId
    public List<OrdemServico> buscaOS(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        List<OrdemServico> listaOs = ordemServicoRepository.findByCliente(cliente.get());
        return listaOs;

    }

   
    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);

    }
    

    @PutMapping("/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizar(@RequestBody OrdemServico ordemServico, @PathVariable Long ordemServicoID) {
        if (!ordemServicoRepository.existsById(ordemServicoID)) {
            return ResponseEntity.notFound().build();

        }
        ordemServico.setId(ordemServicoID);
        ordemServico = ordemServicoService.criar(ordemServico);
        return ResponseEntity.ok(ordemServico);

    }

    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
            @PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO) {

        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(ordemServicoID, statusDTO.status());

        if (optOS.isPresent()) {
            return ResponseEntity.ok(optOS.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{ordemServicoID}")
    public void delete(@RequestBody OrdemServico ordemServico, @PathVariable Long ordemServicoID, Long comentarioId) {
        if (ordemServicoRepository.existsById(ordemServicoID)) {
            comentarioService.excluir(comentarioId);
            ordemServicoService.delete(ordemServicoID);
            ResponseEntity.ok();

        } else {

            ResponseEntity.notFound();
        }
    }

}
