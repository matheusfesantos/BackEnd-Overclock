package com.example.overclockAPI.services.endpoints;

import com.example.overclockAPI.entitys.Fornecedores;
import com.example.overclockAPI.repository.FornecedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedoresService {

    @Autowired
    private FornecedoresRepository fornecedoresRepos;

    public List<Fornecedores> findAll(){
        return fornecedoresRepos.findAll();
    }

    public Fornecedores findById(Long id) {
        return fornecedoresRepos.getById(id);
    }

    public Fornecedores save(Fornecedores fornecedores) {
        return fornecedoresRepos.save(fornecedores);
    }

    public boolean deletarUsuario(Long id) {
        Optional<Fornecedores> fornecedoresOptional = fornecedoresRepos.findById(id);

        if(fornecedoresOptional.isPresent()){
            fornecedoresRepos.deleteById(id);
            return true;
        }
        return false;
    }
}
