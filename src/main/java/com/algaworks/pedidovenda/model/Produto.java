package com.algaworks.pedidovenda.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.validation.SKU;

@Entity
@Table(name="produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String nome;

    @NotBlank(message = "Por favor, informe o SKU")
    @SKU(message = "Por favor, informe um SKU no formato XX9999")
    @Column(nullable = false, length = 20, unique = true)
    private String sku;

    @NotNull(message = "Valor unitário é obrigatório")
    @Column(name="valor_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @NotNull @Min(0) @Max(value = 9999, message = "tem um valor muito alto")
    @Column(name="quantidade_estoque", nullable = false, length = 5)
    private Integer quantididadeEstoque;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantididadeEstoque() {
        return quantididadeEstoque;
    }

    public void setQuantididadeEstoque(Integer quantididadeEstoque) {
        this.quantididadeEstoque = quantididadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void baixarEstoque(Integer quantidade) throws NegocioException {
        int novaQuantidade = this.getQuantididadeEstoque() - quantidade;

        if (novaQuantidade < 0) {
            throw new NegocioException("Não há disponibilidade no estoque de "
                    + quantidade + " itens do produto " + this.getSku() + ".");
        }

        this.setQuantididadeEstoque(novaQuantidade);
    }

    public void adicionarEstoque(Integer quantidade) {
        this.setQuantididadeEstoque(getQuantididadeEstoque() + quantidade);
    }
}
