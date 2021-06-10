package main.java.models.cliente;

import main.java.models.Contato;
import main.java.models.Endereco;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public abstract class Cliente {

    final UUID id = randomUUID();

    Endereco endereco;

    Contato contato;

    Cliente(Endereco endereco, Contato contato) {
        this.endereco = endereco;
        this.contato = contato;
    }

    public UUID getId() {
        return id;
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
}
