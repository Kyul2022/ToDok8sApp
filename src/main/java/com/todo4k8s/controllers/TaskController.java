package com.todo4k8s.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo4k8s.models.Task;
import com.todo4k8s.services.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	private final TaskService taskService;
	
	public TaskController(TaskService T) {
		this.taskService = T;
	}
	
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks(){
		return ResponseEntity.ok(this.taskService.findAll());
	}
	
	@PostMapping("/add")
	public ResponseEntity<Task> addTask(@RequestBody Task T) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(T));
	}
}
