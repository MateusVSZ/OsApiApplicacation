/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.eti.mts.OSApiApplication.domain.repository;


import br.eti.mts.OSApiApplication.domain.model.Comentario;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digma
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
   
}
