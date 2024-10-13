package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.dto.CidadeOutputDTO;
import edu.aranoua.aplicacao_spring01.dto.EstadoInputDTO;
import edu.aranoua.aplicacao_spring01.dto.EstadoOutputDTO;
import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;
import edu.aranoua.aplicacao_spring01.repository.PaisRepository;
import edu.aranoua.aplicacao_spring01.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstadoOutputDTO>> list(){

        List<EstadoOutputDTO> estadosOutputDTO = estadoService.list();

        if(!estadosOutputDTO.isEmpty()){
            return new ResponseEntity<>(estadosOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDTO> getById(@PathVariable Long id){

        EstadoOutputDTO possivelEstado = estadoService.getById(id);

        if (possivelEstado != null){
            return new ResponseEntity<>(possivelEstado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDTO> create(@RequestBody EstadoInputDTO estadoInputDTO){

           EstadoOutputDTO estadoSalvoOutputDTO = estadoService.create(estadoInputDTO);

       if(estadoSalvoOutputDTO != null){
           return new ResponseEntity<>(estadoSalvoOutputDTO, HttpStatus.CREATED);
       }else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping(value ="/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDTO> update(@PathVariable Long id,@RequestBody EstadoInputDTO estadoInputDTO){

        EstadoOutputDTO estadoAtualizadoOutputDTO = estadoService.update(id, estadoInputDTO);

        if(estadoAtualizadoOutputDTO != null){
            return new ResponseEntity<>(estadoAtualizadoOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        EstadoOutputDTO possivelEstado = estadoService.getById(id);

        if(possivelEstado != null) {

            estadoService.delete(possivelEstado.getId());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
