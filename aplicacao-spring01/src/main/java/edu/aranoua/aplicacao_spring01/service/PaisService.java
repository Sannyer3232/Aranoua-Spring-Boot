package edu.aranoua.aplicacao_spring01.service;

import edu.aranoua.aplicacao_spring01.dto.PaisInputDTO;
import edu.aranoua.aplicacao_spring01.dto.PaisOutputDTO;
import edu.aranoua.aplicacao_spring01.model.Pais;
import edu.aranoua.aplicacao_spring01.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<PaisOutputDTO> list(){

        List<Pais> paises = paisRepository.findAll();

        List<PaisOutputDTO> paisesOutputDTO = new ArrayList<>();

        for (Pais pais : paises) {
            paisesOutputDTO.add(new PaisOutputDTO(pais));
        }
        return  paisesOutputDTO;
    }

    public PaisOutputDTO create(@RequestBody PaisInputDTO paisInputDTO){

        try{
            Pais paisNovo = paisInputDTO.build();

            Pais paisNoBD = paisRepository.save(paisNovo);

            PaisOutputDTO paisOutputDTO = new PaisOutputDTO(paisNoBD);

            return paisOutputDTO;
        }catch (Exception e){

            return null;

        }
    }

    public PaisOutputDTO getById(Long id){
        Optional<Pais> pais = paisRepository.findById(id);
        return new PaisOutputDTO(pais.get());
    }

    public PaisOutputDTO update(Long id, PaisInputDTO paisInputDTO){

        try {

            Optional<Pais> possivelPais = paisRepository.findById(id);

            if (possivelPais.isPresent()) {

                Pais paisEncontrado = possivelPais.get();

                paisEncontrado.setNome(paisInputDTO.getNome());

                paisEncontrado.setSigla(paisInputDTO.getSigla());

                Pais paisAtualizado = paisRepository.save(paisEncontrado);

                PaisOutputDTO paisOutputDTO = new PaisOutputDTO(paisAtualizado);

                return paisOutputDTO;
            } else {
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }

    public void delete(Long id){

        try{
            paisRepository.deleteById(id);
        }catch(Exception e){

            e.printStackTrace();
        }
    }

}
