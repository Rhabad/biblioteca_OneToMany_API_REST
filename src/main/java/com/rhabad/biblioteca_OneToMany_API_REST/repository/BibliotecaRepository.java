package com.rhabad.biblioteca_OneToMany_API_REST.repository;

import com.rhabad.biblioteca_OneToMany_API_REST.models.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}
