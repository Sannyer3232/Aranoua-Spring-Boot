package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.dto.CidadeInputDTO;
import edu.aranoua.aplicacao_spring01.dto.CidadeOutputDTO;
import edu.aranoua.aplicacao_spring01.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CidadeOutputDTO>> List(){

        List<CidadeOutputDTO> cidadesOutputDTO = cidadeService.List();

        if(!cidadesOutputDTO.isEmpty()) {
            return new ResponseEntity<>(cidadesOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }

    @GetMapping(value = "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDTO> getById(@PathVariable Long id){

       CidadeOutputDTO cidadeOutputDTO = cidadeService.findById(id);

        if(cidadeOutputDTO != null) {
            return new ResponseEntity<>(cidadeOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CidadeOutputDTO>> create(@RequestBody CidadeInputDTO cidadeInputDTO, UriComponentsBuilder uriBuilder){

        CidadeOutputDTO cidadeNoBD = cidadeService.create(cidadeInputDTO);

        if(cidadeNoBD != null) {
            //Através do uriComponentesBuilder pode-se conseguir o contexto da apliacação e adicionar a rota do método
            UriComponents uriComponents = uriBuilder.path("/api/cidade/{id}").buildAndExpand(cidadeNoBD.getId());
            //Construir a uri em si
            URI uri = uriComponents.toUri();
            System.out.println(uri);


            /* Aplicando o HEATOS*/
            //Criar o link para a propria cidade (auto link)
            Link selflink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).getById(cidadeNoBD.getId())
            ).withSelfRel();

            Link allCidadesLink =  WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).List()
            ).withRel("all-cidades");

            Link deleteLink =  WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).delete(cidadeNoBD.getId())
            ).withRel("delete-cidades");

            EntityModel<CidadeOutputDTO> resource = EntityModel.of(cidadeNoBD, selflink, allCidadesLink, deleteLink);

            /*
            * Aqui, assim como o outro return, retorna um ResponseEntity com o código da resposta,
            * porém, dessa vez, a resposta é um método, que passa a uri para o header das response,
            * define o tipo da midia do conteudo e qual o conteudo que vai ser retornado no body
            * */
            return  ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);

        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value ="/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CidadeOutputDTO>> update(@PathVariable Long id, @RequestBody CidadeInputDTO cidadeInputDTO, UriComponentsBuilder uriBuilder){

        CidadeOutputDTO cidadeAtualizada = cidadeService.update(id, cidadeInputDTO);

        if (cidadeAtualizada != null) {
            //Através do uriComponentesBuilder pode-se conseguir o contexto da apliacação e adicionar a rota do método
            UriComponents uriComponents = uriBuilder.path("/api/cidade/{id}").buildAndExpand(cidadeAtualizada.getId());
            //Construir a uri em si
            URI uri = uriComponents.toUri();
            System.out.println(uri);



            /* Aplicando o HEATOS*/
            //Criar o link para a propria cidade (auto link)
            Link selflink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).getById(cidadeAtualizada.getId())
            ).withSelfRel();

            Link allCidadesLink =  WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).List()
            ).withRel("all-cidades");

            Link deleteLink =  WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CidadeController.class).delete(cidadeAtualizada.getId())
            ).withRel("delete-cidades");

            EntityModel<CidadeOutputDTO> resource = EntityModel.of(cidadeAtualizada, selflink, allCidadesLink, deleteLink);


            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        CidadeOutputDTO possivelCidade = cidadeService.findById(id);

        if(possivelCidade != null) {

            cidadeService.delete(possivelCidade);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
