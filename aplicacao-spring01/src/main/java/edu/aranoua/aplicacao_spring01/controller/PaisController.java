package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.dto.EstadoInputDTO;
import edu.aranoua.aplicacao_spring01.dto.EstadoOutputDTO;
import edu.aranoua.aplicacao_spring01.dto.PaisInputDTO;
import edu.aranoua.aplicacao_spring01.dto.PaisOutDTO;
import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.model.Pais;
import edu.aranoua.aplicacao_spring01.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/pais")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaisOutDTO>> list(){

        List<Pais> paises = paisRepository.findAll();

        List<PaisOutDTO> paisesOutputDTO = new ArrayList<>();

        for (Pais pais : paises) {
            paisesOutputDTO.add(new PaisOutDTO(pais));
        }

        if(!paisesOutputDTO.isEmpty()){
            return new ResponseEntity<>(paisesOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisOutDTO> create(@RequestBody PaisInputDTO paisInputDTO){
        try{
            Pais paisNovo = paisInputDTO.build();
            Pais paisNoBD = paisRepository.save(paisNovo);
            PaisOutDTO paisOutDTO = new PaisOutDTO(paisNovo);
            return new ResponseEntity<>(paisOutDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pais> getById(@PathVariable Long id){

        Optional<Pais> possivelPais = paisRepository.findById(id);

        if(possivelPais.isPresent()){
            return new ResponseEntity<>(possivelPais.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisOutDTO> update(@PathVariable Long id, @RequestBody PaisInputDTO paisInputDTO){
        try {
            Optional<Pais> possivelPais = paisRepository.findById(id);
            if (possivelPais.isPresent()) {
                Pais paisEncontrado = possivelPais.get();
                paisEncontrado.setNome(paisInputDTO.getNome());
                paisEncontrado.setSigla(paisInputDTO.getSigla());
                Pais paisAtualizado = paisRepository.save(paisEncontrado);
                PaisOutDTO paisOutDTO = new PaisOutDTO(paisEncontrado);
                return new ResponseEntity<>(paisOutDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
