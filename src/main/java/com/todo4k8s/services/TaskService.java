package com.todo4k8s.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo4k8s.models.Task;
import com.todo4k8s.repositories.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepo;
	
	public TaskService(TaskRepository T) {
		this.taskRepo = T;
	}
	
	public List<Task> findAll(){
		return this.taskRepo.findAll();
	}
	
	public Task addTask(Task T) {
		return this.taskRepo.save(T);
	}
}
