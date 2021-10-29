package com.everis.everisdarmytasksms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.everis.everisdarmytasksms.controller.Task;

public class TasksRepositoryImpl implements TasksRepositoryCustom{

	@Autowired
	TasksRepository repository;
	
	@Override
	public List<Task> findAllByStatus(String status) {
		List<Task>tasksByStatus = new ArrayList<Task>();
		List<Task>tasks =repository.findAll();
		for(Task item : tasks) {
			if(item.getStatus().equalsIgnoreCase(status)){
				tasksByStatus.add(item);
			}
		}
		return tasksByStatus;
	}
	
}
