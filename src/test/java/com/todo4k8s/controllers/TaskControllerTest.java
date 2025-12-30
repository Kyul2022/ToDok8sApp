package com.todo4k8s.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.todo4k8s.models.Task;
import com.todo4k8s.services.TaskService;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

	@Mock
	private TaskService TService;
	
	private TaskController underTest;
	
	@BeforeEach
	void setUp() throws Exception {
		underTest = new TaskController(TService);
	}

	@Test
	void doGetAllTasks() {
		// Given
		Task T1 = new Task(1,"Wash the dishes","You should wash them");
		Task T2 = new Task(2,"Wash the clothes","You should wash them");
		List<Task> todo = new ArrayList<Task>();
		todo.add(T2);
		todo.add(T1);
		when(TService.findAll()).thenReturn(todo);
		
		// When
		ResponseEntity<List<Task>> results = underTest.getAllTasks();
		
		// Then
		verify(TService).findAll();
		assertThat(results.getBody()).isEqualTo(todo);
		assertThat(results.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
