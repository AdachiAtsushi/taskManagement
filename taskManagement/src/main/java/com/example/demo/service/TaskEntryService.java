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
	 * テーブル名「Task」のIDの最大値を取得する
	 * @return id テーブル名「Task」のIDの最大値
	 */
	public int searchTaskMaxId() {
		return this.taskRepository.findByMaxId();
	}
	
	/**
	 * テーブル名「タスク」にレコードを登録する
	 * @param task 登録対象のテーブル名「タスク」のデータ
	 */
	public void insertTask(Task task) {
		this.taskRepository.save(task);
	}
	
	/**
	 * マスタテーブル「優先度」の全量データを取得する
	 * @return List<MstPriority> マスタテーブル「優先度」の全量データ
	 */
	public List<MstPriority> findByMstPriority() {
		return this.mstPriorityRepository.findAll();
	}
}
