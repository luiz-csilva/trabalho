package main.java.models.cliente;

import main.java.models.Contato;
import main.java.models.Endereco;

import java.util.Objects;

public class PessoaJuridica extends Cliente {

    private String cnpj;

    private String razaoSocial;

    public PessoaJuridica(String cnpj, String razaoSocial, Endereco endereco, Contato contato) {
        super(endereco, contato);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaJuridica that = (PessoaJuridica) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
