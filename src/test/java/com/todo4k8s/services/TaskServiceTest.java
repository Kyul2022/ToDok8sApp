package com.todo4k8s.services;

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

import com.todo4k8s.models.Task;
import com.todo4k8s.repositories.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
	
	@Mock
	private TaskRepository T;
	
	private TaskService underTest;

	@BeforeEach
	void setUp() throws Exception {
		underTest = new TaskService(T);
	}

	@Test
	void doesFindAll() {
		
		// Given
		Task T1 = new Task(1,"Wash the dishes","You should wash them");
		Task T2 = new Task(2,"Wash the clothes","You should wash them");
		List<Task> todo = new ArrayList<Task>();
		todo.add(T2);
		todo.add(T1);
		when(T.findAll()).thenReturn(todo);

		// When
		List<Task> results = underTest.findAll();
		
		// Then
		verify(T).findAll();
		assertThat(results).hasSize(2);
		assertThat(results).contains(T1, T2);
		
	}
	
	@Test
	void doesAdd() {
		
		// Given
		Task T1 = new Task(1,"Wash the dishes","You should wash them");
		when(T.save(T1)).thenReturn(T1);
		
		// When
		Task result = underTest.addTask(T1);
		
		// Then
		verify(T).save(T1);
		assertThat(result).isEqualTo(T1);
		
	}

}
