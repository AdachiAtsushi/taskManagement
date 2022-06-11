package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskListService {
	
	@Autowired
	private TaskRepository repository;
	
	public List<Task> getTaskList() {
		return this.repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
}
