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
	 * �^�X�N�ꗗ���
	 * �����@�\
	 * @param keyword
	 * @return
	 */
	@Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.comment LIKE %:keyword% ORDER BY t.id ASC")
	public List<Task> searchByTaskList(@Param("keyword") String keyword);
	
	@Query("SELECT MAX(t.id) FROM Task t WHERE NOT t.mstStatus.statusId = '4'")
	public int findByMaxId();
	
	/**
	 * �^�X�N�������
	 * �X�e�[�^�X���u�����v�ɂȂ��Ă���e�[�u�����u�^�X�N�v�̈ꗗ���擾����B
	 * @return �X�e�[�^�X�u�����v�̃e�[�u�����u�^�X�N�v�̈ꗗ
	 */
	@Query("SELECT t FROM Task t WHERE t.mstStatus.statusId = '3'")
	public List<Task> findByClosingTaskList();
	
	/**
	 * �^�X�N�������
	 * �^�C�g�����R�����g�ɓ��̓L�[���[�h�̒l�����݂��A���X�e�[�^�X���u�����v�ł���e�[�u�����u�^�X�N�v�̈ꗗ���擾����B
	 * @param keyword ���̓L�[���[�h
	 * @return
	 */
	@Query("SELECT t FROM Task t WHERE (t.title LIKE %:keyword% OR t.comment LIKE %:keyword%) AND t.mstStatus.statusId = '3' ORDER BY t.id ASC")
	public List<Task> searchByClosingTaskList(@Param("keyword") String keyword);
}
