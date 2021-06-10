package main.java.models;

import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Contato {

    private final UUID id = randomUUID();

    private String telefone;

    private String email;

    public Contato(String telefone, String email) {
        this.telefone = telefone;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return id.equals(contato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
