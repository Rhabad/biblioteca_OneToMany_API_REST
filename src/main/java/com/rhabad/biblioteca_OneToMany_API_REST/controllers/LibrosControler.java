package com.rhabad.biblioteca_OneToMany_API_REST.controllers;

import com.rhabad.biblioteca_OneToMany_API_REST.models.Biblioteca;
import com.rhabad.biblioteca_OneToMany_API_REST.models.Libros;
import com.rhabad.biblioteca_OneToMany_API_REST.repository.BibliotecaRepository;
import com.rhabad.biblioteca_OneToMany_API_REST.repository.LibrosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibrosControler {


    @Autowired
    private LibrosRepository librosRepository;
    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @GetMapping
    public ResponseEntity<Page<Libros>> listarLibros(Pageable pageable){
        return ResponseEntity.ok(librosRepository.findAll(pageable));
    }
    @PostMapping
    public ResponseEntity<Libros> guardarLibros(@Valid @RequestBody Libros libros){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libros.getBibliotecaFK().getId());
        if (bibliotecaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        libros.setBibliotecaFK(bibliotecaOptional.get());
        Libros librosGuardado = librosRepository.save(libros);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(librosGuardado.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(librosGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libros> actualizarLibros(@PathVariable Long id, @Valid @RequestBody Libros libros){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libros.getBibliotecaFK().getId());
        if (bibliotecaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Libros> librosOptional = librosRepository.findById(id);
        if (librosOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        libros.setBibliotecaFK(bibliotecaOptional.get());
        libros.setId(librosOptional.get().getId());
        librosRepository.save(libros);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Libros> eliminarLibros(@PathVariable Long id){
        Optional<Libros> librosOptional = librosRepository.findById(id);
        if (librosOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        librosRepository.delete(librosOptional.get());
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Libros> obtenerLibroPorId(@PathVariable Long id){
        Optional<Libros> librosOptional = librosRepository.findById(id);
        if (librosOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(librosOptional.get());
    }


}
