/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.mts.OSApiApplication.exceptionhandler;

import br.eti.mts.OSApiApplication.domain.exception.DomainException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author digma
 */
@ControllerAdvice //Avisa o Spring boot que essa classe é controller
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    
    /** "protected" é um modificador de acesso, usado para controlar a visibilidade do método
     *  dentro do mesmo pacote, o "extends" msm estando fora da classe.
     *  @Override Serve para sobrescrever uma classe "PAI"
     *  Esse é o um método existente pelo @override method, o MethodArgumentNotValidException, hanleMethod...é do tipo de retorno ResponseEntity<object>
     */
    @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid( 
            MethodArgumentNotValidException ex, //ex exceção
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ProblemaException problema = new ProblemaException(); // Instanciando Objeto de erro de todos os erros customizado
        problema.setStatus(status.value()); //Coloca no meu objeto o codigo de status
        problema.setTitulo("Um ou mais campos inválidos! Tente novamente.");
        problema.setDataHora(LocalDateTime.now()); //registra a data e o horário exato do momento do erro

        
        // Aqui eu estou criando uma lista de objetos da classe interna CampoProblema que vem de ProblemaException 
        //Obs: não é uma sub classe CampoProblema, pq apenas é subclasse quando tem extends, herança. 
         
        List<ProblemaException.CampoProblema> camposComErro = new ArrayList<ProblemaException.CampoProblema>(); //lista customizada de erro

        
        /** for (error = variavel do tipo ObjectError que recebe uma lista de erros "getAllErrors().
         * O ex vem la de cima do MethodArgumentNotValidException, ele recebe todos aqueles parametros do ResponseEntityException
         * getBindingResult: ele armazena toda a validação do Spring e joga no erro "error", e o getAllErros traz todos os erros que aconteceram.
         * ////
         * String 'nomeCampo' recebe fieldError(subclasse). Que esta dentro de errror.getField(), traz o nome do campo específico que deu erro.
         * String 'mensagemCampo' recebe error.traz uma mensagem padrão "não deve estar em branco".
         * ////
         * camposComErro é uma lista personalizada que guarda os erros de validação. Para cada erro, é criado um objeto CampoProblema,
         * que recebe o nomeCampo (nome do campo que falhou) e a mensagemCampo (mensagem do erro),
         * e esse objeto é adicionado na lista camposComErro.
         **/
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nomeCampo = ((FieldError) error).getField();
            String mensagemCampo = error.getDefaultMessage();

            
            camposComErro.add(new ProblemaException.CampoProblema(nomeCampo, mensagemCampo));

        }
        problema.setCampos(camposComErro); //No objeto problema(contem todos os erros) adicione o (camposComErro) usando o método setCampos que você criou.
        return super.handleExceptionInternal(ex, problema, headers, status, request); //manda pra classe pai os detalhes dos erros e envia a resposta via HTTP.

     }
     @ExceptionHandler(DomainException.class)
     public ResponseEntity<Object> HandleDomainException(DomainException ex, WebRequest request){
     var status = HttpStatus.BAD_REQUEST;
     ProblemaException problema = new ProblemaException();
     problema.setStatus(status.value());
     problema.setTitulo(ex.getMessage());
     problema.setDataHora(LocalDateTime.now());
     
     return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
         
     
     
     }

}
