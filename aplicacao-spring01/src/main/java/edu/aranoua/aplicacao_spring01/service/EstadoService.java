package edu.aranoua.aplicacao_spring01.service;

import edu.aranoua.aplicacao_spring01.dto.EstadoInputDTO;
import edu.aranoua.aplicacao_spring01.dto.EstadoOutputDTO;
import edu.aranoua.aplicacao_spring01.dto.PaisOutputDTO;
import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.model.Pais;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;
import edu.aranoua.aplicacao_spring01.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private PaisRepository paisRepository;


    public List<EstadoOutputDTO> list() {

        List<Estado> estados = estadoRepository.findAll();

        List<EstadoOutputDTO> estadosOutputDTO = new ArrayList<>();

        for (Estado estado : estados) {
            estadosOutputDTO.add(new EstadoOutputDTO(estado));
        }

        if (!estadosOutputDTO.isEmpty()) {
            return estadosOutputDTO;
        } else {
            return null;
        }
    }

    public EstadoOutputDTO getById(Long id) {

        Optional<Estado> estado = estadoRepository.findById(id);

        return new EstadoOutputDTO(estado.get());
    }

    public EstadoOutputDTO create(EstadoInputDTO estadoInputDTO) {
        try {
            Estado estadoNovo = estadoInputDTO.build(paisRepository);

            Estado estadoNoBD = estadoRepository.save(estadoNovo);

            EstadoOutputDTO estadoOutputDTO = new EstadoOutputDTO(estadoNoBD);

            return estadoOutputDTO;
        } catch (Exception e) {
            return null;
        }
    }

    public EstadoOutputDTO update(Long id, EstadoInputDTO estadoInputDTO) {

        try {

            Optional<Estado> possivelEstado = estadoRepository.findById(id);

            if (possivelEstado.isPresent()) {

                Estado estadoEncontrado = possivelEstado.get();

                estadoEncontrado.setNome(estadoInputDTO.getNome());

                estadoEncontrado.setSigla(estadoInputDTO.getSigla());

                estadoEncontrado.setPais(paisRepository.findByNome(estadoInputDTO.getPais()));

                Estado estadoAtualizado = estadoRepository.save(estadoEncontrado);

                EstadoOutputDTO estadoOutputDTO = new EstadoOutputDTO(estadoAtualizado);

                return estadoOutputDTO;

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    public void delete(Long id){

        try{
            estadoRepository.deleteById(id);
        }catch(Exception e){

            e.printStackTrace();
        }
    }

}