package com.rhabad.biblioteca_OneToMany_API_REST.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "biblioteca")
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;

    @OneToMany(mappedBy = "bibliotecaFK", cascade = CascadeType.ALL)
    private Set<Libros> librosSet = new HashSet<>();

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

    public Set<Libros> getLibrosSet() {
        return librosSet;
    }

    public void setLibrosSet(Set<Libros> librosSet) {
        this.librosSet = librosSet;
        for (Libros lib : librosSet){
            lib.setBibliotecaFK(this);
        }
    }
}
