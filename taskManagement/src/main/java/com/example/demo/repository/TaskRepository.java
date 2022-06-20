package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	/**
	 * タスク一覧画面
	 * 検索機能
	 * @param keyword
	 * @return
	 */
	@Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.comment LIKE %:keyword% ORDER BY t.id ASC")
	public List<Task> searchByTaskList(@Param("keyword") String keyword);
	
	@Query("SELECT MAX(t.id) FROM Task t WHERE NOT t.mstStatus.statusId = '4'")
	public int findByMaxId();
	
	/**
	 * ステータスが「完了」になっているテーブル名「タスク」の一覧を取得する。
	 * @return ステータス「完了」のテーブル名「タスク」の一覧
	 */
	@Query("SELECT t FROM Task t WHERE t.mstStatus.statusId = '3'")
	public List<Task> findByClosingTaskList();
}
