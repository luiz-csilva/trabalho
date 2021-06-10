package main.java.models.cliente;

import main.java.models.Contato;
import main.java.models.Endereco;

import java.util.Objects;

public class PessoaFisica extends Cliente {

    private String cpf;

    private String nome;

    public PessoaFisica(String cpf, String nome, Endereco endereco, Contato contato) {
        super(endereco, contato);
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaFisica that = (PessoaFisica) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
