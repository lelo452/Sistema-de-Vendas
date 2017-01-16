package com.algaworks.pedidovenda.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private  Long id;

    @Column(nullable = false, length = 3)
    private Integer quantidade = 1;

    @Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Transient
    public BigDecimal getValorTotal() {
        return this.getValorUnitario().multiply(new BigDecimal(this.getQuantidade()));
    }

    @Transient
    public boolean isProdutoAssociado() {
        return this.getProduto() != null && this.getProduto().getId() != null;
    }

    @Transient
    public boolean isEstoqueSuficiente() {
        return this.getPedido().isEmitido() || this.getProduto().getId() == null
                || this.getProduto().getQuantididadeEstoque() >= this.getQuantidade();
    }

    @Transient
    public boolean isEstoqueInsuficiente() {
        return !this.isEstoqueSuficiente();
    }
}
