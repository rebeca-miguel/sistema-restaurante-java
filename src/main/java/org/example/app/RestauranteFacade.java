package org.example.app;

import org.example.cardapio.*;
import org.example.pedido.GerenciadorPedidos;
import org.example.pedido.Pedido;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RestauranteFacade {
    private final GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public Pedido buscarPedido(int numero) {
        return gerenciadorPedidos.buscarPedido(numero);
    }

    public Collection<Pedido> listarPedidos() {
        return gerenciadorPedidos.getPedidos();
    }


    public double calcularTotalDoDia() {
        return gerenciadorPedidos.calcularTotalDoDia();
    }

    public Set<String> listarCombinacoesDiferentes() {
        Set<String> combinacoes = new HashSet<>();
        for (Pedido pedido : gerenciadorPedidos.getPedidos()) {
            if (!pedido.isCancelado()) {
                for (ItemCardapio item : pedido.getItens()) {
                    combinacoes.add(item.getDescricao());
                }
            }
        }
        return combinacoes;
    }

    public Lanche criarLanche(int opcao) {
        return switch (opcao) {
            case 1 -> LancheFactory.xBurger();
            case 2 -> LancheFactory.xSalada();
            case 3 -> LancheFactory.xBacon();
            default -> throw new IllegalArgumentException("Opção de lanche inválida.");
        };
    }

    public ItemCardapio personalizarAdicionais(Lanche lanche, boolean cheddar, boolean baconExtra) {
        ItemCardapio item = lanche;
        if (cheddar) item = new ComCheddar(item);
        if(baconExtra) item = new ComBanconExtra(item);
        return item;
    }
}
