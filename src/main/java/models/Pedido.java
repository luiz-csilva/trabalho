package main.java.models;

import main.java.models.cliente.Cliente;
import main.java.models.cliente.PessoaFisica;
import main.java.models.cliente.PessoaJuridica;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Pedido {

    private final UUID id = randomUUID();

    private Cliente cliente;

    private Endereco endereco;

    private List<Produto> produtos;

    private Fornecedor fornecedor;

    public Pedido(Cliente cliente, Endereco endereco, List<Produto> produtos, Fornecedor fornecedor) {
        this.cliente = cliente;
        this.endereco = endereco;
        this.produtos = produtos;
        this.fornecedor = fornecedor;
    }

    public String getClienteNome() {
        if (this.cliente instanceof PessoaFisica) {
            return ((PessoaFisica) this.cliente).getNome();
        }
        return ((PessoaJuridica) this.cliente).getRazaoSocial();
    }

    public UUID getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id + "\n" +
                "Cliente=" + getClienteNome() + "\n" +
                "Endereco=" + endereco + "\n" +
                "Produtos=" + produtos + "\n" +
                "Fornecedor=" + fornecedor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
