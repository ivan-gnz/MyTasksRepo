package com.tutorial.tutorial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tutorial.tutorial.controller.Library;
import com.tutorial.tutorial.repository.LibraryRepository;

@SpringBootApplication
public class TutorialApplication /*implements CommandLineRunner*/{

	@Autowired
	LibraryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

	/*@Override
	public void run(String[] args) {
		Library lib = repository.findById("fdsefr343").get();
		System.out.println(lib.getAuthor());
		
		//Create
		Library en = new Library();
		en.setAuthor("Ivan");
		en.setAisle(123);
		en.setBook_name("Sherlock Holmes");
		en.setIsbn("jsdjskdss");
		en.setId("sdh343");
		
		//repository.save(en);
		
		//Consultar todo
		List<Library> allrecords = repository.findAll();
		for(Library item: allrecords) {
			System.out.println(item.getBook_name());
		}
		
		//Eliminar
		repository.delete(en);
		
	}*/
	
}
