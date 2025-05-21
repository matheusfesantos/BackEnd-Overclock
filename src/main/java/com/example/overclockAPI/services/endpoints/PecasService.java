package com.example.overclockAPI.services.endpoints;

import com.example.overclockAPI.entitys.Pecas;
import com.example.overclockAPI.repository.PecasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecasService {

    @Autowired
    private PecasRepository pecasRepos;

    public List<Pecas> listarPecas() {
        return pecasRepos.findAll();
    }

    public ResponseEntity<Pecas> listarPecasPorID(Long id) {
        Optional<Pecas> pecasOptional = pecasRepos.findById(id);

        if(pecasOptional.isPresent()){
            return new ResponseEntity<>(pecasOptional.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Pecas salvarPecas(Pecas pecas) {
        return pecasRepos.save(pecas);
    }

    public boolean validarId(Long id){
        boolean exist = pecasRepos.existsById(id);
        if(exist){
            return true;
        }
        else{
            return false;
        }
    }

    //Service para atualizar Peças
    /*
    public boolean editarPecas(Long id, Pecas pecas) {
        Optional<Pecas> pecasOptional = pecasRepos.findById(id);

        if(pecasOptional.isPresent()){
            pecasRepos.save(pecas);
            return true;
        }
        return false;
    }
     */

    public boolean deletarPecas (Long id) {
        Optional<Pecas> pecasOptional = pecasRepos.findById(id);

        if(pecasOptional.isPresent()){
            pecasRepos.deleteById(id);
            return true;
        }
        return false;
    }
}
