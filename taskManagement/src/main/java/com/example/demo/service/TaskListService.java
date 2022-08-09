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
	 * テーブル名「Task」の全量をidの昇順に取得する。
	 * 
	 * @return テーブル名「Task」の全量
	 */
	public List<Task> getTaskList() {
		return this.repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	/**
	 * 検索キーワードに合致するテーブル名「Task」の全量を取得する。
	 * 
	 * @param keyword 検索キーワード
	 * @return 検索キーワードに合致するテーブル名「Task」の全量
	 */
	public List<Task> getSearchTaskList(String keyword) {
		return this.repository.searchByTaskList(keyword);
	}
	
	/**
	 * テーブル名「Task」の指定したレコードを削除する。
	 * @param id
	 */
	public void delete(int id) {
		this.repository.deleteById(id);
	}
}
