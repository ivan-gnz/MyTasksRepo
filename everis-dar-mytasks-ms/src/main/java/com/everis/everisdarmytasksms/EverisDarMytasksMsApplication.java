package com.everis.everisdarmytasksms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.everis.everisdarmytasksms.controller.Task;
import com.everis.everisdarmytasksms.repository.TasksRepository;

@SpringBootApplication
public class EverisDarMytasksMsApplication  /*implements CommandLineRunner */{

	@Autowired
	TasksRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(EverisDarMytasksMsApplication.class, args);
	}
	

}
