package com.example.overclockAPI.services;

import com.example.overclockAPI.entitys.*;
import com.example.overclockAPI.repository.MovimentacaoCompraRepository;
import com.example.overclockAPI.repository.MovimentacaoPedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MovimentacaoService {

    @Autowired
    MovimentacaoCompraRepository movimentacaoCompraRepos;

    @Autowired
    MovimentacaoPedidoRepository movimentacaoPedidoRepos;

    public MovimentacaoCompra salvarMovimentacaoCompra(Compras compra) {
        MovimentacaoCompra movimentacao = new MovimentacaoCompra();
        movimentacao.setCompra(compra);  // Passa o objeto compra inteiro, não apenas o ID
        movimentacao.setPeca(compra.getPecas());
        movimentacao.setQuantidade(compra.getQuantidade());

        return movimentacaoCompraRepos.save(movimentacao);
    }

    public MovimentacaoPedido salvarMovimentacaoPedido(Pedidos pedido) {
        MovimentacaoPedido movimentacaoPedido = new MovimentacaoPedido();
        movimentacaoPedido.setPedido(pedido);

        Pecas peca = new Pecas();
        peca.setId_peca(pedido.getIdPeca());
        movimentacaoPedido.setPeca(peca);

        movimentacaoPedido.setQuantidade(pedido.getQuantidade());

        return movimentacaoPedidoRepos.save(movimentacaoPedido);
    }
}