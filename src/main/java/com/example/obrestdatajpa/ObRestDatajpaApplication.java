package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entity.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = (BookRepository) context.getBean(BookRepository.class);

		//CRUD
		//crear libro
		Book book = new Book(null, "Libro 1", "Javier", 100, 200.00d, LocalDate.of(1991, 2, 3), true);
		Book book2 = new Book(null, "Libro 2", "Francisco", 200, 400.00d, LocalDate.of(1992, 4, 6), false);

		//almacenar libro
		repository.save(book);
		repository.save(book2);

		//recuperar libros
		System.out.println(repository.findAll().size());

		//borrar libro
		//repository.deleteById(1L);

		System.out.println(repository.findAll().size());

	}
}
