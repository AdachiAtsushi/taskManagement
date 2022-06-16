package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MstPriority;
import com.example.demo.entity.Task;
import com.example.demo.repository.MstPriorityRepository;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskEntryService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private MstPriorityRepository mstPriorityRepository;
	
	/**
	 * �e�[�u�����uTask�v��ID�̍ő�l���擾����
	 * @return id �e�[�u�����uTask�v��ID�̍ő�l
	 */
	public int searchTaskMaxId() {
		return this.taskRepository.findByMaxId();
	}
	
	/**
	 * �e�[�u�����u�^�X�N�v�Ƀ��R�[�h��o�^����
	 * @param task �o�^�Ώۂ̃e�[�u�����u�^�X�N�v�̃f�[�^
	 */
	public void insertTask(Task task) {
		this.taskRepository.save(task);
	}
	
	/**
	 * �}�X�^�e�[�u���u�D��x�v�̑S�ʃf�[�^���擾����
	 * @return List<MstPriority> �}�X�^�e�[�u���u�D��x�v�̑S�ʃf�[�^
	 */
	public List<MstPriority> findByMstPriority() {
		return this.mstPriorityRepository.findAll();
	}
}
