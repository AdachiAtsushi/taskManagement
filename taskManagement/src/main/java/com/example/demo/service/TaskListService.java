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
	
	/**
	 * �e�[�u�����uTask�v�̑S�ʂ�id�̏����Ɏ擾����B
	 * 
	 * @return �e�[�u�����uTask�v�̑S��
	 */
	public List<Task> getTaskList() {
		return this.repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	/**
	 * �����L�[���[�h�ɍ��v����e�[�u�����uTask�v�̑S�ʂ��擾����B
	 * 
	 * @param keyword �����L�[���[�h
	 * @return �����L�[���[�h�ɍ��v����e�[�u�����uTask�v�̑S��
	 */
	public List<Task> getSearchTaskList(String keyword) {
		return this.repository.searchByTaskList(keyword);
	}
	
	/**
	 * �e�[�u�����uTask�v�̎w�肵�����R�[�h���폜����B
	 * @param id
	 */
	public void delete(int id) {
		this.repository.deleteById(id);
	}
}
