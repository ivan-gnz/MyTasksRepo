package com.everis.everisdarmytasksms;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.everis.everisdarmytasksms.controller.Task;
import com.everis.everisdarmytasksms.controller.TaskController;
import com.everis.everisdarmytasksms.repository.TasksRepository;
import com.everis.everisdarmytasksms.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class EverisDarMytasksMsApplicationTests {

	@Autowired
	TaskController con;
	@MockBean
	TasksRepository repository;
	@MockBean
	TaskService taskService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void addTaskControllerTest() throws Exception{
		Task task = buildTask();
		ObjectMapper map =new ObjectMapper();
		String jsonString = map.writeValueAsString(task);
		
		when(taskService.checkTaskAlreadyExist(task.getId())).thenReturn(false);
		when(repository.save(any())).thenReturn(task);
		
		this.mockMvc.perform(post("/addTask").contentType(MediaType.APPLICATION_JSON)
		.content(jsonString)).andDo(print()).andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(task.getId()));
		
	}
	
	@Test
	public void updateTaskTest() throws Exception{
		Task task =buildTask();
		ObjectMapper map =new ObjectMapper();
		String jsonString = map.writeValueAsString(UpdateTask());
		when(taskService.getTaskById(anyInt())).thenReturn(buildTask());
	
		this.mockMvc.perform(put("/updateTask/"+task.getId()).contentType(MediaType.APPLICATION_JSON)
		.content(jsonString)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().json("{\"id\":7,\"description\":\"Hacer las pruebas\",\"status\":\"Finished\"}"));
	
	}
	
	@Test
	public void deleteBookControllerTest() throws Exception{
		when(taskService.getTaskById(anyInt())).thenReturn(buildTask());	
		doNothing().when(repository).delete(buildTask());
		this.mockMvc.perform(delete("/deleteBook").contentType(MediaType.APPLICATION_JSON)
		.content("{\"id\":7}")).andDo(print())
		.andExpect(status().isCreated()).andExpect(content().string("Task is deleted"));
	}
	
	
	
	
	
	public Task buildTask(){
		Task task =new Task();
		task.setId(7);
		task.setDescription("Hacer las pruebas");;
		task.setStatus("In process");
		return task;
	}
	
	public Task UpdateTask(){
		Task task =new Task();
		task.setId(7);
		task.setDescription("Hacer las pruebas");;
		task.setStatus("Finished");
		return task;
	}

}
