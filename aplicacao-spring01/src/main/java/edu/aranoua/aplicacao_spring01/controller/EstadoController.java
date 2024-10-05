package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.dto.EstadoInputDTO;
import edu.aranoua.aplicacao_spring01.dto.EstadoOutputDTO;
import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;
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
    private EstadoRepository estadoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstadoOutputDTO>> list(){

        List<Estado> estados = estadoRepository.findAll();

        List<EstadoOutputDTO> estadosOutputDTO = new ArrayList<>();

        for(Estado estado : estados){
            estadosOutputDTO.add(new EstadoOutputDTO(estado));
        }

        if(!estadosOutputDTO.isEmpty()){
            return new ResponseEntity<>(estadosOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> getById(@PathVariable Long id){

        Optional<Estado> possivelEstado = estadoRepository.findById(id);

        if (possivelEstado.isPresent()){
            return new ResponseEntity<>(possivelEstado.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDTO> create(@RequestBody EstadoInputDTO estadoInputDTO){
        try {
           Estado estadoNovo = estadoInputDTO.build();

           Estado estadoNoBD = estadoRepository.save(estadoNovo);

           EstadoOutputDTO estadoOutputDTO = new EstadoOutputDTO(estadoNoBD);

           return new ResponseEntity<>(estadoOutputDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value ="/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDTO> update(@PathVariable Long id,@RequestBody EstadoInputDTO estadoInputDTO){

        try {

            Optional<Estado> possivelEstado = estadoRepository.findById(id);

            if (possivelEstado.isPresent()) {
                Estado estadoEncontrado = possivelEstado.get();
                estadoEncontrado.setNome(estadoInputDTO.getNome());
                estadoEncontrado.setSigla(estadoInputDTO.getSigla());
                Estado estadoAtualizado = estadoRepository.save(estadoEncontrado);
                EstadoOutputDTO estadoOutputDTO = new EstadoOutputDTO(estadoEncontrado);
                return new ResponseEntity<>(estadoOutputDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
