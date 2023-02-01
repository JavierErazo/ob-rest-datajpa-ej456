package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entity.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.*;

@RestController
public class BookController {

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
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) throws ServerException {
        System.out.println(headers.get("User-Agent"));
        Book newBook = bookRepository.save(book);
        if(newBook == null){
            throw new ServerException("No se pudo crear.");
        }else{
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        }
    }


    //actualizar un libro existente

    //borrar un libro en base de datos
}