package com.tutorial.tutorial.repository;

import java.util.List;

import com.tutorial.tutorial.controller.Library;

public interface LibraryRepositoryCustom {

	List<Library> findAllByAuthor(String authorName);
	
}
