package com.everis.everisdarmytasksms.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

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

import com.everis.everisdarmytasksms.controller.Task;
import com.everis.everisdarmytasksms.repository.TasksRepository;
import com.everis.everisdarmytasksms.service.TaskService;

@RestController
public class TaskController {
	@Autowired
	TasksRepository repository;
	
	@Autowired
	TaskService taskService;
	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
		
	//Consultar lista de todas las tareas
	@GetMapping("/tasks")
	public List<Task> getTasks() {
		return repository.findAll();
	}
	
	//Consultar una tarea por id
	@GetMapping("/tasks/{id}")
    private Task getTaskById(@PathVariable(value="id") int id) {
        return repository.findById(id).get();
    }
	
	//Consultar tareas por estado
	@GetMapping("/tasks/status")
    private List<Task> getTasksByStatus(@RequestParam(value="status") String status) {
        return repository.findAllByStatus(status);
    }
	
	//Crear una nueva tarea
	@PostMapping("/addTask")
	public ResponseEntity addTask(@RequestBody Task task) {
		
		AddResponse ad = new AddResponse();
		
		if(!taskService.checkTaskAlreadyExist(task.getId())) {
			logger.info("Task do not exist so creating one");
			repository.save(task);
			
			ad.setMsg("Sucess Task is Added");
			ad.setId(task.getId());
			
			return new ResponseEntity<AddResponse>(ad, HttpStatus.CREATED);
		}else {
			//Si la tarea ya existe
			logger.info("Task  exist so skipping creation");
			ad.setMsg("Task already exist");
			ad.setId(task.getId());
			return new ResponseEntity<AddResponse>(ad, HttpStatus.ACCEPTED);
		}	
		
	}
	
	
	//Actualizar tarea
	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Task> updateBook(@PathVariable(value="id")int id, @RequestBody Task task) {
	
		//Task existingTask = repository.findById(id).get();
		Task existingTask = taskService.getTaskById(id);
		
		existingTask.setDescription(task.getDescription());
		existingTask.setId(task.getId());
		existingTask.setStatus(task.getStatus());
		repository.save(existingTask);
		
		return new ResponseEntity<Task>(existingTask, HttpStatus.OK);
	}
	
	//Eliminar una tarea
	@DeleteMapping("/deleteTask")
	public ResponseEntity<String> deleteTaskkById(@RequestBody Task task) {
		//Task taskDelete = repository.findById(task.getId()).get();
		Task taskDelete = taskService.getTaskById(task.getId());
		repository.delete(taskDelete);
		
		logger.info("Task is deleted ");
		return new ResponseEntity<>("Task is deleted",HttpStatus.CREATED);
	}
		

	
	
	
	
	
}
