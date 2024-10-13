package edu.aranoua.aplicacao_spring01.service;

import edu.aranoua.aplicacao_spring01.dto.EstadoOutputDTO;
import edu.aranoua.aplicacao_spring01.dto.PessoaInputDTO;
import edu.aranoua.aplicacao_spring01.dto.PessoaOutputDTO;
import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.model.Pessoa;
import edu.aranoua.aplicacao_spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao_spring01.repository.PessoaRepository;
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
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    CidadeRepository cidadeRepository;

    public List<PessoaOutputDTO> list() {

        List<Pessoa> pessoas = pessoaRepository.findAll();

        List<PessoaOutputDTO> pessoasOutputDTO = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            pessoasOutputDTO.add(new PessoaOutputDTO(pessoa));
        }

        if (!pessoasOutputDTO.isEmpty()) {
            return pessoasOutputDTO;
        } else {
            return null;
        }

    }

    public PessoaOutputDTO getById(Long id) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        return new PessoaOutputDTO(pessoa.get());
    }

    public PessoaOutputDTO create(PessoaInputDTO pessoaInputDTO) {

        try {
            Pessoa pessoa = pessoaInputDTO.build(cidadeRepository);

            Pessoa pessoaNoBD = pessoaRepository.save(pessoa);

            PessoaOutputDTO pessoaOutputDTO = new PessoaOutputDTO(pessoaNoBD);

            return pessoaOutputDTO;

        } catch (Exception e) {
            return null;
        }
    }

    public PessoaOutputDTO update(Long id, PessoaInputDTO pessoaInputDTO) {

        try {

            Optional<Pessoa> possivelpessoa = pessoaRepository.findById(id);

            if (possivelpessoa.isPresent()) {

                Pessoa pessoaEncontrada = possivelpessoa.get();

                pessoaEncontrada.setNome(pessoaInputDTO.getNome());
                pessoaEncontrada.setCpf(pessoaInputDTO.getCpf());
                pessoaEncontrada.setIdade(pessoaInputDTO.getIdade());

                pessoaEncontrada.setCidade(cidadeRepository.findByNome(pessoaInputDTO.getCidade()));


                Pessoa pessoaAtualizada = pessoaRepository.save(pessoaEncontrada);

                return new PessoaOutputDTO(pessoaAtualizada);

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }

    public void delete(Long id){

        try{
            pessoaRepository.deleteById(id);
        }catch(Exception e){

            e.printStackTrace();
        }
    }

}