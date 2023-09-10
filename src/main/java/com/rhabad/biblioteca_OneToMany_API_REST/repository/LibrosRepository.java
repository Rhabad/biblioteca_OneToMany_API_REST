package com.rhabad.biblioteca_OneToMany_API_REST.repository;

import com.rhabad.biblioteca_OneToMany_API_REST.models.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libros, Long> {
}
