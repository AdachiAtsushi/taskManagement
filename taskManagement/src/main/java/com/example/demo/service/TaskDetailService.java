package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MstPriority;
import com.example.demo.entity.MstStatus;
import com.example.demo.entity.Task;
import com.example.demo.repository.MstPriorityRepository;
import com.example.demo.repository.MstStatusRepository;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskDetailService {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	MstPriorityRepository mstPriorityRepository;
	
	@Autowired
	MstStatusRepository mstStatusRepository;
	
	/**
	 * �w���ID�̃e�[�u�����u�^�X�N�v�̃��R�[�h���擾����
	 * @param id
	 * @return
	 */
	public Task findByTask(int id) {
		Optional<Task> task = this.taskRepository.findById(id);
		
		// ID�w�肵�āA���R�[�h�����݂��Ȃ�������
		if (task.isEmpty()) {
			throw new TaskNotFoundException("�e�[�u�����uTask�v�Ɏw���ID�̃��R�[�h�����݂��܂���B");
		}
		
		return task.get();
	}
	
	/**
	 * �}�X�^�e�[�u���u�D��x�v�̑S�ʃf�[�^���擾����
	 * @return �}�X�^�e�[�u���u�D��x�v��List
	 */
	public List<MstPriority> findByAllMstPriority() {
		return this.mstPriorityRepository.findAll();
	}
	
	/**
	 * �}�X�^�e�[�u���u�X�e�[�^�X�v�̑S�ʃf�[�^���擾����
	 * @return �}�X�^�e�[�u���u�X�e�[�^�X�v��List
	 */
	public List<MstStatus> findByAllMstStatus() {
		return this.mstStatusRepository.findAll();
	}
	
	/**
	 * �e�[�u�����u�^�X�N�v���X�V����
	 * @param task
	 */
	public void updateTask(Task task) {
		this.taskRepository.save(task);
	}
	
	/**
	 * �e�[�u�����u�^�X�N�v���폜����
	 * @param id
	 */
	public void deleteTask(int id) {
		this.taskRepository.deleteById(id);
	}
}
