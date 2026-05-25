/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.api.controller;

import br.eti.mts.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.eti.mts.OSApiApplication.domain.model.Cliente;
import br.eti.mts.OSApiApplication.domain.model.Comentario;
import br.eti.mts.OSApiApplication.domain.model.OrdemServico;
import br.eti.mts.OSApiApplication.domain.repository.ComentarioRepository;
import br.eti.mts.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.mts.OSApiApplication.domain.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RestController
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    //Aqui posta o comentario de acordo com a Ordem de serviço
    //comentario é amarrada com ordem de serviço.
    
    @Operation(summary = "Busca o comentário por ID da ordem de serviço")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "400", description = "Not found 404 - Não foi encontrado o ID da ordem de servico")
    })
    @PostMapping("/comentario/{osId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrdemServico> adicionarComentario(@PathVariable Long osId, @RequestBody Comentario comentario) {
        Comentario comentarioOS = comentarioService.criar(comentario);
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(osId);

        if (ordemServico.isPresent()) {
            ordemServico.get().setComentario(comentarioOS);

            return ResponseEntity.ok(ordemServicoRepository.save(ordemServico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //Deletando um comentario pela id da ordem de serviço
    @Operation(summary = "Apaga um comentário por ID da ordem de serviço")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "400", description = "Not found 404 - Não foi encontrado o ID da ordem de servico")
    })
    @DeleteMapping("/comentario/{osId}")
    public ResponseEntity<Void> delete(@PathVariable Long osId
    ) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(osId);

        if (ordemServico.isPresent()) {
            Long comentarioID = ordemServico.get().getComentario().getId();
            ordemServico.get().setComentario(null);
            ordemServicoRepository.save(ordemServico.get());
            comentarioService.excluir(comentarioID);

            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();

    }
    @Operation(summary = "Atualiza um comentário por ID da ordem de serviço")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "400", description = "Not found 404 - Não foi encontrado o ID da ordem de servico")
    })
    @PutMapping("/comentario/{ordemServicoId}")
    public ResponseEntity<Comentario> atualizar(
            @PathVariable Long ordemServicoId, @RequestBody Comentario comentario) {

        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
        if (ordemServico.isPresent()) {
            Long comentarioId = ordemServico.get().getComentario().getId();
            comentario.setId(comentarioId);
            return ResponseEntity.ok(comentarioService.criar(comentario));

        }
        return ResponseEntity.notFound().build();   

    }
}
