package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entity.Laptop;
import com.example.obrestdatajpa.repository.LaptopRepository;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.*;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public ResponseEntity<List<Laptop>> find(){
        List<Laptop> laptops = laptopRepository.findAll();
        if(laptops.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(laptops);
        }
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) throws ServerException {
        Laptop newLaptop = laptopRepository.save(laptop);
        if(newLaptop == null){
            throw new ServerException("No se pudo crear la laptop");
        }else{
           return new ResponseEntity<>(newLaptop, HttpStatus.CREATED);
        }
    }
}
