package com.example.overclockAPI.services.endpoints;

import com.example.overclockAPI.entitys.Compras;
import com.example.overclockAPI.repository.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprasService {

    @Autowired
    ComprasRepository comprasRepos;

    public List<Compras> findAll(){
        return comprasRepos.findAll();
    }

    public Compras findById(Long id){
        return comprasRepos.getById(id);
    }

    public boolean existsById(Long id){
        return comprasRepos.existsById(id);
    }

    public boolean saveCompra(Compras compras){
        try{
            comprasRepos.save(compras);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean deletarCompra(Long id){
        boolean deletado = false;
        boolean exist = comprasRepos.existsById(id);

        if(exist){
            comprasRepos.deleteById(id);
            deletado = true;
            return deletado;
        }
        return deletado;
    }
}
