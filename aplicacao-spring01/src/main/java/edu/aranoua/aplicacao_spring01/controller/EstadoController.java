package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> list(){
        return estadoRepository.findAll();
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
    public ResponseEntity<Estado> create(@RequestBody Estado estado){
        try {
           Estado estadoNovo = estadoRepository.save(estado);
           return new ResponseEntity<>(estadoNovo, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value ="/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> update(@PathVariable Long id,@RequestBody Estado estado){

        try {

            Optional<Estado> possivelEstado = estadoRepository.findById(id);

            if (possivelEstado.isPresent()) {
                Estado estadoEncontrado = possivelEstado.get();
                estadoEncontrado.setNome(estado.getNome());
                estadoEncontrado.setSigla(estado.getSigla());
                Estado estadoAtualizado = estadoRepository.save(estadoEncontrado);
                return new ResponseEntity<>(estadoAtualizado, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
