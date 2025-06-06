package com.example.overclockAPI.services;

import com.example.overclockAPI.dto.db.ComprasDTO;
import com.example.overclockAPI.entitys.*;
import com.example.overclockAPI.repository.ComprasRepository;
import com.example.overclockAPI.repository.PecasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprasService {

    @Autowired
    ComprasRepository comprasRepos;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    PecasRepository pecasRepository;

    public List<Compras> findAll(){
        return comprasRepos.findAll();
    }

    public Compras findById(Long id){
        return comprasRepos.getById(id);
    }

    public boolean existsById(Long id){
        return comprasRepos.existsById(id);
    }

    public boolean saveCompra(ComprasDTO comprasDTO, Long userId) {
        try {

            Pecas peca = pecasRepository.getReferenceById((long) comprasDTO.id_peca());
            int novoEstoque = peca.getQuantidade_estoque() + comprasDTO.quantidade();

            peca.setQuantidade_estoque(novoEstoque);
            pecasRepository.save(peca);

            Compras novaCompra = new Compras();

            Usuarios usuario = new Usuarios();
            usuario.setId_usuario(userId);
            novaCompra.setUsuarios(usuario);

            Fornecedores fornecedor = new Fornecedores();
            fornecedor.setIdFornecedor(Long.valueOf(comprasDTO.id_fornecedor()));
            novaCompra.setId_fornecedor(fornecedor.getIdFornecedor());

            novaCompra.setPecas(peca);
            novaCompra.setQuantidade(comprasDTO.quantidade());
            novaCompra.setObservacao(comprasDTO.observacao());

            Compras compraSalva = comprasRepos.save(novaCompra);
            movimentacaoService.salvarMovimentacaoCompra(compraSalva);
            return true;

        } catch (Exception e) {
            return false;
        }
    }


    public boolean editarCompra(Long id, String observacao){
        boolean exist = comprasRepos.existsById(id);

        if (exist){
            Compras compraAtualizada = comprasRepos.getById(id);
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
