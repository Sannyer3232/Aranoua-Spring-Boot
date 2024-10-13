package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.dto.CidadeInputDTO;
import edu.aranoua.aplicacao_spring01.dto.CidadeOutputDTO;
import edu.aranoua.aplicacao_spring01.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<CidadeOutputDTO> create(@RequestBody CidadeInputDTO cidadeInputDTO){

        CidadeOutputDTO cidadeNoBD = cidadeService.create(cidadeInputDTO);

        if(cidadeNoBD != null) {
            return new ResponseEntity<>(cidadeNoBD, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value ="/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDTO> update(@PathVariable Long id, @RequestBody CidadeInputDTO cidadeInputDTO){

        CidadeOutputDTO cidadeAtualizada = cidadeService.update(id, cidadeInputDTO);

        if (cidadeAtualizada != null) {

            return new ResponseEntity<>(cidadeAtualizada, HttpStatus.OK);

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
