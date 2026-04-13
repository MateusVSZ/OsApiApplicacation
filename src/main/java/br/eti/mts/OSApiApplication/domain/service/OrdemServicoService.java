/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.domain.service;

import br.eti.mts.OSApiApplication.domain.model.OrdemServico;
import br.eti.mts.OSApiApplication.domain.model.StatusOrdemServico;
import br.eti.mts.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class OrdemServicoService {
    
    @Autowired 
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar(OrdemServico ordemServico){
        ordemServico.setStatus(StatusOrdemServico.ABERTA); //Aqui ele guarda o status ABERTA no meu objeto ordemServico
        ordemServico.setDataAbertura(LocalDateTime.now()); //Data e Hora atual, do pedido da OrdemServico 
        return ordemServicoRepository.save(ordemServico); //Aqui ele ta salvando no repositorio meu objeto ordemServico
        
    }
    
    public List<OrdemServico> findAll(){
        
        List<OrdemServico> result = ordemServicoRepository.findAll();
        return result;
    }
}
