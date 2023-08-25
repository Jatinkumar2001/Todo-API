package com.todo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.Task;
import com.todo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController 
{ 
	@Autowired
	private TodoService service;
	
	@GetMapping("list")
	public ResponseEntity<List<Task>> getTask()
	{
		List<Task> lst= service.getList();
		if(lst.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.status(HttpStatus.OK).body(lst);
		
	}
	@PostMapping("add")
	public ResponseEntity<Task> getTask(@RequestBody Task tsk)
	{
		Task t=service.addTask(tsk);
		return ResponseEntity.status(HttpStatus.OK).body(t);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Boolean> deleteTask(@PathVariable int id)
	{
		if(service.deleteTask(id))
		return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
	}
	@PatchMapping("completed/{id}")
	public ResponseEntity<Boolean> updateTask(@PathVariable int id)
	{
		if(service.updateTask(id))
		return ResponseEntity.status(HttpStatus.OK).body(true);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
	}
	

}
