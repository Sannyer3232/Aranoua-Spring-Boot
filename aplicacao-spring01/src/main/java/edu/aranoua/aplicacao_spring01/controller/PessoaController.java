package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.dto.*;
import edu.aranoua.aplicacao_spring01.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;
    @CrossOrigin(origins = "http://192.168.43.209:5500")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaOutputDTO>> list(){

        List<PessoaOutputDTO> pessoasOutputDTO = pessoaService.list();


        if(!pessoasOutputDTO.isEmpty()){
            return new ResponseEntity<>(pessoasOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDTO> getById(@PathVariable Long id){

        PessoaOutputDTO possivelPessoaOutputDTO = pessoaService.getById(id);

        if (possivelPessoaOutputDTO != null){
            return new ResponseEntity<>(possivelPessoaOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDTO> create(@RequestBody PessoaInputDTO pessoaInputDTO){

        PessoaOutputDTO pessoaSalvaOutputDTO = pessoaService.create(pessoaInputDTO);
        if(pessoaSalvaOutputDTO != null){
            return new ResponseEntity<>(pessoaSalvaOutputDTO, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value ="/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDTO> update(@PathVariable Long id, @RequestBody PessoaInputDTO pessoaInputDTO){

        PessoaOutputDTO pessoaAtualizadoOutDTO = pessoaService.update(id,pessoaInputDTO);

        if(pessoaAtualizadoOutDTO != null){
            return new ResponseEntity<>(pessoaAtualizadoOutDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        PessoaOutputDTO possivelPessoaOutputDTO = pessoaService.getById(id);

        if(possivelPessoaOutputDTO != null) {

            pessoaService.delete(possivelPessoaOutputDTO.getId());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
