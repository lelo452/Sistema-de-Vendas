package com.algaworks.pedidovenda.service;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.Produtos;
import com.algaworks.pedidovenda.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroProdutoService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Produtos produtos;

    @Transactional
    public Produto salvar(Produto produto) throws NegocioException {
        Produto produtoExistente = produtos.porSku(produto.getSku());

        if (produtoExistente != null && !produtoExistente.equals(produto)) {
            throw new NegocioException("Já existe um produto com o SKU informado.");
        }

        return produtos.guardar(produto);
    }
}
