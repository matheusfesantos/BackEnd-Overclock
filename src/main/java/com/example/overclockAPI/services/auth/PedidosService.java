package com.example.overclockAPI.services.auth;

import com.example.overclockAPI.dto.db.PedidosDTO;
import com.example.overclockAPI.entitys.Fornecedores;
import com.example.overclockAPI.entitys.Pecas;
import com.example.overclockAPI.entitys.Pedidos;
import com.example.overclockAPI.entitys.Usuarios;
import com.example.overclockAPI.repository.PecasRepository;
import com.example.overclockAPI.repository.PedidosRepository;
import com.example.overclockAPI.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosService {

    @Autowired
    PedidosRepository pedidosRepos;

    @Autowired
    PecasRepository pecasRepos;

    @Autowired
    MovimentacaoService movimentacaoServ;

    public List<Pedidos> findAll(){
        return pedidosRepos.findAll();
    }

    public Pedidos findById(Long id){
        return pedidosRepos.getById(id);
    }

    public boolean validarId(Long id){
        boolean exist = pedidosRepos.existsById(id);

        if (exist){
            return true;
        }
        return false;
    }

    public boolean save(PedidosDTO pedidosDTO, Long userId) {
        try{

            Pecas peca = pecasRepos.getReferenceById((long) pedidosDTO.id_peca());
            int novoEstoque = peca.getQuantidade_estoque() - pedidosDTO.quantidade();
            if (novoEstoque < 0) {
                throw new IllegalArgumentException("Estoque insuficiente para a compra.");
            }

            Usuarios usuario = new Usuarios();
            usuario.setId_usuario(userId);

            peca.setId_peca(Long.valueOf(pedidosDTO.id_peca()));

            Pedidos novoPedido = new Pedidos();
            novoPedido.setUsuarios(usuario);
            novoPedido.setIdFornecedor(Long.valueOf(pedidosDTO.id_fornecedor()));
            novoPedido.setQuantidade(pedidosDTO.quantidade());
            novoPedido.setIdPeca(Long.valueOf(pedidosDTO.id_peca()));
            novoPedido.setObservacao(pedidosDTO.observacao());

            Pedidos pedidoSalvo = pedidosRepos.save(novoPedido);
            movimentacaoServ.salvarMovimentacaoPedido(pedidoSalvo);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean deletarPedido(Long id){
        boolean exist = pedidosRepos.existsById(id);

        if (exist){
            pedidosRepos.deleteById(id);
            return true;
        }
        return false;
    }
}
