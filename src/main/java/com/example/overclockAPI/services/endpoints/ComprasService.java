package com.example.overclockAPI.services.endpoints;

import com.example.overclockAPI.dto.db.ComprasDTO;
import com.example.overclockAPI.entitys.Compras;
import com.example.overclockAPI.entitys.Fornecedores;
import com.example.overclockAPI.entitys.Pecas;
import com.example.overclockAPI.entitys.Usuarios;
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

    public boolean saveCompra(ComprasDTO comprasDTO, Long userId){
        try {
            Compras novaCompra = new Compras();
            novaCompra.setObservacao(comprasDTO.observacao());

            Usuarios usuario = new Usuarios();
            usuario.setId_usuario(userId);
            novaCompra.setUsuarios(usuario);

            Fornecedores fornecedor = new Fornecedores();
            fornecedor.setId_fornecedor((long) comprasDTO.id_fornecedor());
            novaCompra.setFornecedores(fornecedor);

            Pecas pecas = new Pecas();
            pecas.setId_peca((long) comprasDTO.id_peca());
            novaCompra.setPecas(pecas);

            comprasRepos.save(novaCompra);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean editarCompra(Long id, String observacao){
        boolean exist = comprasRepos.existsById(id);

        if (exist){
            Compras compraAtualizada = comprasRepos.getById(id);
            compraAtualizada.setObservacao(observacao);
            comprasRepos.save(compraAtualizada);
            return true;
        }
        else{
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
