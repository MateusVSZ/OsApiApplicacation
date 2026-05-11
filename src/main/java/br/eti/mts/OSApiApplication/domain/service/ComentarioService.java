/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.domain.service;

import br.eti.mts.OSApiApplication.domain.model.Cliente;
import br.eti.mts.OSApiApplication.domain.model.Comentario;
import br.eti.mts.OSApiApplication.domain.model.OrdemServico;
import br.eti.mts.OSApiApplication.domain.model.StatusOrdemServico;
import br.eti.mts.OSApiApplication.domain.repository.ComentarioRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> findAll() {
        List<Comentario> result = comentarioRepository.findAll();

        return result;
    }

    public Comentario criar(Comentario comentario) {

        comentario.setData_envio(LocalDateTime.now()); //Data e Hora atual, do pedido da OrdemServico 
        return comentarioRepository.save(comentario); //Aqui ele ta salvando no repositorio meu objeto ordemServico

    }

    public void excluir(Long comentarioId) {
        comentarioRepository.deleteById(comentarioId);

    }

         
        
    
}
