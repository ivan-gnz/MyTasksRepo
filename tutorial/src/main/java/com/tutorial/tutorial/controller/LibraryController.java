package com.tutorial.tutorial.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tutorial.tutorial.repository.LibraryRepository;
import com.tutorial.tutorial.service.LibraryService;

@RestController
public class LibraryController {

	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);
	
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody Library library) {
		
		String id = libraryService.buildId(library.getIsbn(), library.getAisle());
		AddResponse ad = new AddResponse();

		if(!libraryService.checkBookAlreadyExist(id)) {
			logger.info("Book do not exist so creating one");
			library.setId(id);
			repository.save(library); //mock
			
			ad.setMsg("Success Book is Added");
			ad.setId(id);
			HttpHeaders headers = new HttpHeaders();
			headers.add("unique", id);
			
			return new ResponseEntity<AddResponse>(ad, headers, HttpStatus.CREATED);
		}else {
			//Si el libro ya existe
			logger.info("Book  exist so skipping creation");
			ad.setMsg("Book already exist");
			ad.setId(id);
			return new ResponseEntity<AddResponse>(ad, HttpStatus.ACCEPTED);
		}	
	}
	
	
	@GetMapping("/getBooks/{id}")
	public Library getBookById(@PathVariable(value="id")String id) {
		
		try {
			Library lib = repository.findById(id).get();
			return lib;	
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	
	}
	
	@GetMapping("/getBooks/author")
	public List<Library> getBookByAuthorName(@RequestParam(value="authorname")String authorname) {
		return repository.findAllByAuthor(authorname);	
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable(value="id")String id, @RequestBody Library library) {
	
		//Library existingBook = repository.findById(id).get();
		Library existingBook = libraryService.getBookById(id);
		
		existingBook.setAisle(library.getAisle());
		existingBook.setAuthor(library.getAuthor());
		existingBook.setBook_name(library.getBook_name());
		repository.save(existingBook);
		
		return new ResponseEntity<Library>(existingBook, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookById(@RequestBody Library library) {
		//Library libraryDelete = repository.findById(library.getId()).get();
		Library libraryDelete = libraryService.getBookById(library.getId());

		repository.delete(libraryDelete);
		
		logger.info("Book  is deleted ");
		return new ResponseEntity<>("Book is deleted",HttpStatus.CREATED);
	}
		
}
