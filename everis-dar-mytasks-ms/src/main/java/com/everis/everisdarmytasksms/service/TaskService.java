package com.everis.everisdarmytasksms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.everisdarmytasksms.controller.Task;
import com.everis.everisdarmytasksms.repository.TasksRepository;


@Service
public class TaskService {

	@Autowired
	TasksRepository repository;
	

	public boolean checkTaskAlreadyExist(int id) {
		Optional<Task> task = repository.findById(id);
		if(task.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public Task getTaskById(int id) {
		return repository.findById(id).get();
	}
	
	
}
