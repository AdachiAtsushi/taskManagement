package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskHistoryService {
	
	@Autowired
	TaskRepository taskRepository;
	
	/**
	 * �X�e�[�^�X�������ɂȂ��Ă���e�[�u�����u�^�X�N�v�̈ꗗ���擾����
	 * @return �e�[�u�����u�^�X�N�v�̃��X�g
	 */
	public List<Task> getClosingTaskList() {
		return this.taskRepository.findByClosingTaskList();
	}
	
	/**
	 * ���̓L�[���[�h����X�e�[�^�X���u�����v�ɂȂ��Ă���^�X�N�̈ꗗ���擾����
	 * @param keyword
	 * @return �e�[�u�����u�^�X�N�v�̈ꗗ
	 */
	public List<Task> getSearctClosingTaskList(String keyword) {
		return this.taskRepository.searchByClosingTaskList(keyword);
	}
	
	/**
	 * �e�[�u�����u�^�X�N�v�̎w���ID�̃��R�[�h���폜����
	 * @param id �w���ID
	 */
	public void deleteTaskHistory(int id) {
		this.taskRepository.deleteById(id);
	}
}
