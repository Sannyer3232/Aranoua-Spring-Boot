package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.dto.PaisInputDTO;
import edu.aranoua.aplicacao_spring01.dto.PaisOutputDTO;
import edu.aranoua.aplicacao_spring01.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaisOutputDTO>> list(){

        List<PaisOutputDTO> paisesOutputDTO = paisService.list();

        if(!paisesOutputDTO.isEmpty()){
            return new ResponseEntity<>(paisesOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisOutputDTO> getById(@PathVariable Long id){

        PaisOutputDTO paisOutputDTO = paisService.getById(id);

        if(paisOutputDTO != null){
            return new ResponseEntity<>(paisOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisOutputDTO> create(@RequestBody PaisInputDTO paisInputDTO){

        PaisOutputDTO paisOutputDTO = paisService.create(paisInputDTO);
        if(paisOutputDTO != null){

            return new ResponseEntity<>(paisOutputDTO, HttpStatus.CREATED);
        }else{

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisOutputDTO> update(@PathVariable Long id, @RequestBody PaisInputDTO paisInputDTO){

        PaisOutputDTO paisAtualizado = paisService.update(id, paisInputDTO);
        if(paisAtualizado != null){
            return new ResponseEntity<>(paisAtualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        PaisOutputDTO paisOutputDTO = paisService.getById(id);
        if(paisOutputDTO != null){
            paisService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
