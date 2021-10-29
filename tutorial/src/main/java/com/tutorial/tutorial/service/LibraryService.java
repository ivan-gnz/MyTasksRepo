package com.tutorial.tutorial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.tutorial.controller.Library;
import com.tutorial.tutorial.repository.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	LibraryRepository repository;
	
	public String buildId (String isbn, int aisle) {
		if(isbn.startsWith("Z")) {
			return "OLD"+isbn+aisle;
		}else {
			return isbn+aisle;
		}
	}
	
	public boolean checkBookAlreadyExist(String id) {
		Optional<Library> lib = repository.findById(id);
		if(lib.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public Library getBookById(String id) {
		return repository.findById(id).get();
	}
	
	
}
