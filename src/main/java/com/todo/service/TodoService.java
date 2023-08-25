package com.todo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.Task;
import com.todo.repository.TodoRepository;
@Service
public class TodoService {
  @Autowired
  private TodoRepository repo;

public List<Task> getList() {
	return repo.findAll();
	
}

public Task addTask(Task tsk) {
	tsk.setDate(LocalDate.now().toString());
	tsk.setTime(LocalTime.now().toString());
	repo.save(tsk);
	return tsk;
}

public boolean deleteTask(int id) {
	repo.deleteById(id);
	return true;
}

public boolean updateTask(int id) {
	Optional<Task> t=repo.findById(id);
	t.get().setCompleted(true);
	return true;
}
  
}
