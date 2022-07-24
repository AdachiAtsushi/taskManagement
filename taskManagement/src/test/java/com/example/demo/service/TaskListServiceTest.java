package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

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
class TaskListServiceTest {
	@Autowired
	TaskListService sut;
	
	@Autowired
	TaskRepository taskRepository;
	
	/**================ getTaskList ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("getTaskList_�e�[�u�����uTask�v�̑S�f�[�^�̎擾�ɐ��������ꍇ")
	void testGetTaskList_success() {
		// ���O����
		Task expected1 = new Task();
		expected1.setId(100);
		expected1.setTitle("�؃g��");
		expected1.setComment("�r���ĕ���������");
		expected1.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected1.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected2 = new Task();
		expected2.setId(101);
		expected2.setTitle("�Ǐ�");
		expected2.setComment("���Ȍ[���{��ǂ�");
		expected2.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected2.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected3 = new Task();
		expected3.setId(102);
		expected3.setTitle("��������");
		expected3.setComment("�̎����𐮗�����");
		expected3.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected3.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected4 = new Task();
		expected4.setId(103);
		expected4.setTitle("������(���p�i)");
		expected4.setComment("�e�B�b�V���𔃂�");
		expected4.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected4.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected5 = new Task();
		expected5.setId(104);
		expected5.setTitle("�|��");
		expected5.setComment("���r���O��|������");
		expected5.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected5.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// �D��x�}�X�^�̒l��ݒ肷��
		MstPriority mstPriority1 = new MstPriority();
		mstPriority1.setPriority("1");
		mstPriority1.setPriorityText("��");
		
		MstPriority mstPriority2 = new MstPriority();
		mstPriority2.setPriority("2");
		mstPriority2.setPriorityText("��");
		
		MstPriority mstPriority3 = new MstPriority();
		mstPriority3.setPriority("3");
		mstPriority3.setPriorityText("��");
		
		expected1.setMstPriority(mstPriority1);
		expected2.setMstPriority(mstPriority1);
		expected3.setMstPriority(mstPriority2);
		expected4.setMstPriority(mstPriority2);
		expected5.setMstPriority(mstPriority3);
		
		// �X�e�[�^�X�}�X�^�̒l��ݒ肷��
		MstStatus mstStatus1 = new MstStatus();
		mstStatus1.setStatusId("1");
		mstStatus1.setStatusText("������");
		
		MstStatus mstStatus2 = new MstStatus();
		mstStatus2.setStatusId("2");
		mstStatus2.setStatusText("���蒆");
		
		MstStatus mstStatus3 = new MstStatus();
		mstStatus3.setStatusId("3");
		mstStatus3.setStatusText("����");
		
		expected1.setMstStatus(mstStatus1);
		expected2.setMstStatus(mstStatus2);
		expected3.setMstStatus(mstStatus3);
		expected4.setMstStatus(mstStatus3);
		expected5.setMstStatus(mstStatus1);
		
		List<Task> expectedList = new ArrayList<Task>();
		expectedList.add(expected1);
		expectedList.add(expected2);
		expectedList.add(expected3);
		expectedList.add(expected4);
		expectedList.add(expected5);
		
		// ���s
		List<Task> actual = this.sut.getTaskList();
		
		// ����
		assertThat(actual, is(expectedList));
	}

	/**================ getSearchTaskList ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("getSearchTaskList_�e�[�u�����uTask�v���猟���L�[���[�h�ɊY������f�[�^�̎擾�ɐ��������ꍇ")
	void testGetSearchTaskList_success() {
		// ���O����
		Task expected1 = new Task();
		expected1.setId(100);
		expected1.setTitle("�؃g��");
		expected1.setComment("�r���ĕ���������");
		expected1.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected1.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// �D��x�}�X�^�̒l��ݒ肷��
		MstPriority mstPriority1 = new MstPriority();
		mstPriority1.setPriority("1");
		mstPriority1.setPriorityText("��");
		expected1.setMstPriority(mstPriority1);
		
		// �X�e�[�^�X�}�X�^�̒l��ݒ肷��
		MstStatus mstStatus1 = new MstStatus();
		mstStatus1.setStatusId("1");
		mstStatus1.setStatusText("������");
		expected1.setMstStatus(mstStatus1);
		
		List<Task> expectedList = new ArrayList<Task>();
		expectedList.add(expected1);
		
		// ���s
		List<Task> actual = this.sut.getSearchTaskList("�؃g��");
		
		// ����
		assertThat(actual.get(0), is(expected1));
	}

	/**================ delete ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("delete_�e�[�u�����uTask�v����f�[�^�̍폜�ɐ��������ꍇ")
	void testDelete_success() {
		// ���s
		this.sut.delete(100);
		
		// ����
		boolean actual = this.taskRepository.existsById(100);
		assertThat(actual, is(false));
	}

}
