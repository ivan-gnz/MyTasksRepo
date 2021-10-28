package com.tutorial.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.tutorial.controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {
	
	

}
