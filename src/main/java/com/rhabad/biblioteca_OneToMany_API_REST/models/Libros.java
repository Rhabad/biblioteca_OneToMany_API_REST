package com.rhabad.biblioteca_OneToMany_API_REST.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "libros", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bibliotecaFK")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biblioteca bibliotecaFK;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Biblioteca getBibliotecaFK() {
        return bibliotecaFK;
    }

    public void setBibliotecaFK(Biblioteca bibliotecaFK) {
        this.bibliotecaFK = bibliotecaFK;
    }
}
