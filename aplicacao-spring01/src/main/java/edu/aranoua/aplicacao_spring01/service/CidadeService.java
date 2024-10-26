package edu.aranoua.aplicacao_spring01.service;

import edu.aranoua.aplicacao_spring01.dto.CidadeInputDTO;
import edu.aranoua.aplicacao_spring01.dto.CidadeOutputDTO;
import edu.aranoua.aplicacao_spring01.model.Cidade;
import edu.aranoua.aplicacao_spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {


    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    public List<CidadeOutputDTO> List(){

        List<Cidade> cidades = cidadeRepository.findAll();
        List<CidadeOutputDTO> cidadesOutputDTO = new ArrayList<>();

        for (Cidade cidade : cidades) {
            cidadesOutputDTO.add(new CidadeOutputDTO(cidade));
        }

        return cidadesOutputDTO;

    }

    public CidadeOutputDTO create(CidadeInputDTO cidadeInputDTO){

        try {

            Cidade cidade = cidadeInputDTO.build(estadoRepository);

            Cidade cidadeNoBD = cidadeRepository.save(cidade);

            CidadeOutputDTO cidadeOutputDTO = new CidadeOutputDTO(cidadeNoBD);

            return cidadeOutputDTO;
        }catch (Exception e){

            return null;

        }

    }


    public CidadeOutputDTO update(Long id,  CidadeInputDTO cidadeInputDTO){

        try {

            Optional<Cidade> possivelCidade = cidadeRepository.findById(id);

            if (possivelCidade.isPresent()) {

                Cidade cidadeEncontrada = possivelCidade.get();

                cidadeEncontrada.setNome(cidadeInputDTO.getNome());
                cidadeEncontrada.setEstado(estadoRepository.findByNome(cidadeInputDTO.getEstado()));

                Cidade cidadeAtualizada = cidadeRepository.save(cidadeEncontrada);

                CidadeOutputDTO cidadeOutputDTO = new CidadeOutputDTO(cidadeAtualizada);

                return cidadeOutputDTO ;

            } else {
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }

    public CidadeOutputDTO findById(Long id){
        Optional<Cidade> possivelCidade = cidadeRepository.findById(id);
        return new CidadeOutputDTO(possivelCidade.get());
    }

    public void delete(CidadeOutputDTO cidadeOutputDTO){
        try{
            cidadeRepository.deleteById(cidadeOutputDTO.getId());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
