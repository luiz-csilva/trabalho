package main.java.models;

import main.java.enums.TipoFornecedor;

import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Fornecedor {

    private final UUID id = randomUUID();

    private String nome;

    private Endereco endereco;

    private Contato contato;

    private TipoFornecedor tipo;

    public Fornecedor(String nome, Endereco endereco, Contato contato, TipoFornecedor tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.tipo = tipo;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public TipoFornecedor getTipo() {
        return tipo;
    }

    public void setTipo(TipoFornecedor tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", contato=" + contato +
                ", tipo=" + tipo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
