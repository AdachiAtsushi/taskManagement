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
	 * ステータスが完了になっているテーブル名「タスク」の一覧を取得する
	 * @return テーブル名「タスク」のリスト
	 */
	public List<Task> getClosingTaskList() {
		return this.taskRepository.findByClosingTaskList();
	}
	
	/**
	 * 入力キーワードからステータスが「完了」になっているタスクの一覧を取得する
	 * @param keyword
	 * @return テーブル名「タスク」の一覧
	 */
	public List<Task> getSearctClosingTaskList(String keyword) {
		return this.taskRepository.searchByClosingTaskList(keyword);
	}
	
	/**
	 * テーブル名「タスク」の指定のIDのレコードを削除する
	 * @param id 指定のID
	 */
	public void deleteTaskHistory(int id) {
		this.taskRepository.deleteById(id);
	}
}
