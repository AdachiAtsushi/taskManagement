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
	 * 指定のIDのテーブル名「タスク」のレコードを取得する
	 * @param id
	 * @return
	 */
	public Task findByTask(int id) {
		Optional<Task> task = this.taskRepository.findById(id);
		
		// ID指定して、レコードが存在しなかった時
		if (task.isEmpty()) {
			throw new TaskNotFoundException("テーブル名「Task」に指定のIDのレコードが存在しません。");
		}
		
		return task.get();
	}
	
	/**
	 * マスタテーブル「優先度」の全量データを取得する
	 * @return マスタテーブル「優先度」のList
	 */
	public List<MstPriority> findByAllMstPriority() {
		return this.mstPriorityRepository.findAll();
	}
	
	/**
	 * マスタテーブル「ステータス」の全量データを取得する
	 * @return マスタテーブル「ステータス」のList
	 */
	public List<MstStatus> findByAllMstStatus() {
		return this.mstStatusRepository.findAll();
	}
	
	/**
	 * テーブル名「タスク」を更新する
	 * @param task
	 */
	public void updateTask(Task task) {
		this.taskRepository.save(task);
	}
	
	/**
	 * テーブル名「タスク」を削除する
	 * @param id
	 */
	public void deleteTask(int id) {
		this.taskRepository.deleteById(id);
	}
}
