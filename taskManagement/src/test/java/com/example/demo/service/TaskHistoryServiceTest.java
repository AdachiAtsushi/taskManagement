package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.MstPriority;
import com.example.demo.entity.MstStatus;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
class TaskHistoryServiceTest {
	
	@Autowired
	TaskHistoryService sut;
	
	@Autowired
	TaskRepository taskRepository;
	
	/**================ getClosingTaskList ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("getClosingTaskList_�e�[�u�����uTask�v����X�e�[�^�X���u�����v�̃��R�[�h�Q�̎擾�ɐ��������ꍇ")
	void testGetClosingTaskList_success() {
		// ���O����
		Task expected1 = new Task();
		expected1.setId(102);
		expected1.setTitle("��������");
		expected1.setComment("�̎����𐮗�����");
		expected1.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected1.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected2 = new Task();
		expected2.setId(103);
		expected2.setTitle("������(���p�i)");
		expected2.setComment("�e�B�b�V���𔃂�");
		expected2.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected2.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// �D��x�}�X�^�̒l��ݒ肷��
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("2");
		mstPriority.setPriorityText("��");
		expected1.setMstPriority(mstPriority);
		expected2.setMstPriority(mstPriority);
		
		// �X�e�[�^�X�}�X�^�̒l��ݒ肷��
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("3");
		mstStatus.setStatusText("����");
		expected1.setMstStatus(mstStatus);
		expected2.setMstStatus(mstStatus);
		
		List<Task> expectedList = new ArrayList<Task>();
		expectedList.add(expected1);
		expectedList.add(expected2);
		
		// ���s
		List<Task> actual = this.sut.getClosingTaskList();
		
		// ����
		assertThat(actual, is(expectedList));
	}
	
	/**================ getSearctClosingTaskList ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("getSearctClosingTaskList_�e�[�u�����uTask�v�̃X�e�[�^�X���u�����v�̃��R�[�h�Q���猟���L�[���[�h�ɊY������f�[�^�̎擾�ɐ��������ꍇ")
	void testGetSearctClosingTaskList_success() {
		// ���O����
		Task expected = new Task();
		expected.setId(103);
		expected.setTitle("������(���p�i)");
		expected.setComment("�e�B�b�V���𔃂�");
		expected.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// �D��x�}�X�^�̒l��ݒ肷��
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("2");
		mstPriority.setPriorityText("��");
		expected.setMstPriority(mstPriority);
		
		// �X�e�[�^�X�}�X�^�̒l��ݒ肷��
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("3");
		mstStatus.setStatusText("����");
		expected.setMstStatus(mstStatus);
		
		List<Task> expectedList = new ArrayList<Task>();
		expectedList.add(expected);
		
		// ���s
		List<Task> actual = this.sut.getSearctClosingTaskList("������");
		
		// ����
		assertThat(actual.get(0), is(expected));
	}

	/**================ deleteTaskHistory ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("deleteTaskHistory_�e�[�u�����uTask�v����w�肵���f�[�^�̍폜�ɐ��������ꍇ")
	void testDeleteTaskHistory_success() {
		// ���s
		this.sut.deleteTaskHistory(102);
		
		// ����
		boolean actual = this.taskRepository.existsById(102);
		assertThat(actual, is(false));
	}

}
