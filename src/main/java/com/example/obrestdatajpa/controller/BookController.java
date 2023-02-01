package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entity.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    //CRUD sobre la entidad Book
    BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> find(){
        return bookRepository.findAll();
    }

    //buscar un solo libro en base de datos
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        Optional<Book> bookOpt = bookRepository.findById(id);

        if(bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        }else{
            return ResponseEntity.notFound().build();
        }

        //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        //opcion 1
        //if(bookOpt.isPresent()){
        //    return bookOpt.get();
        //}else{
        //    return null;
        //}

        //opcion 2
        //return bookOpt.orElse(null);
    }

    //crear un nuevo libro en base de datos
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        if(book.getId() != null){
            log.warn("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }

        Book newBook = bookRepository.save(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }


    //actualizar un libro existente
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null){ //si no tiene id quiere decir que si es una creacion
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }

        if(!bookRepository.existsById(book.getId())){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    //borrar un libro en base de datos
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            log.warn("trying to delete non existant book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("api/books")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST request for delete all books");
        if(!(bookRepository.count() > 0)){
            return ResponseEntity.noContent().build();
        }
        bookRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}