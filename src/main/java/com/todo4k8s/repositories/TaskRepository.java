package com.todo4k8s.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo4k8s.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	Task findByTitle(String title);
}
