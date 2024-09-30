package edu.aranoua.aplicacao_spring01.controller;

import edu.aranoua.aplicacao_spring01.dto.CidadeInputDTO;
import edu.aranoua.aplicacao_spring01.dto.CidadeOutputDTO;
import edu.aranoua.aplicacao_spring01.dto.EstadoInputDTO;
import edu.aranoua.aplicacao_spring01.dto.EstadoOutputDTO;
import edu.aranoua.aplicacao_spring01.model.Cidade;
import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CidadeOutputDTO>> List(){

        List<Cidade> cidades = cidadeRepository.findAll();

        for(Cidade cidade : cidades){
            System.out.println(cidade);
        }
        List<CidadeOutputDTO> cidadesOutputDTO = new ArrayList<>();

        for (Cidade cidade : cidades) {
            cidadesOutputDTO.add(new CidadeOutputDTO(cidade));
        }

        if(!cidadesOutputDTO.isEmpty()) {
            return new ResponseEntity<>(cidadesOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDTO> create(@RequestBody CidadeInputDTO cidadeInputDTO){

        try {

            Cidade cidade = cidadeInputDTO.build(estadoRepository);

            Cidade cidadeNoBD = cidadeRepository.save(cidade);

            CidadeOutputDTO cidadeOutputDTO = new CidadeOutputDTO(cidadeNoBD);

            return new ResponseEntity<>(cidadeOutputDTO, HttpStatus.CREATED);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping(value ="/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDTO> update(@PathVariable Long id, @RequestBody CidadeInputDTO cidadeInputDTO){

        try {

            Optional<Cidade> possivelCidade = cidadeRepository.findById(id);

            if (possivelCidade.isPresent()) {

                Cidade cidadeEncontrada = possivelCidade.get();

                cidadeEncontrada.setNome(cidadeInputDTO.getNome());
                cidadeEncontrada.setEstado(estadoRepository.findByNome(cidadeInputDTO.getEstado()));

                Cidade cidadeAtualizada = cidadeRepository.save(cidadeEncontrada);

                CidadeOutputDTO cidadeOutputDTO = new CidadeOutputDTO(cidadeAtualizada);

                return new ResponseEntity<>(cidadeOutputDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
