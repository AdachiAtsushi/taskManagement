package com.example.demo.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.example.demo.entity.MstPriority;
import com.example.demo.entity.MstStatus;
import com.example.demo.entity.Task;
import com.example.demo.repository.MstPriorityRepository;
import com.example.demo.repository.MstStatusRepository;

@SpringBootTest
class TaskDetailServiceTest {
	
	@Autowired
	TaskDetailService service;
	
	@Autowired
	MstPriorityRepository mstPriorityRepository;
	
	@Autowired
	MstStatusRepository mstStatusRepository;
	
	/**================ findByTask ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("findByTask_�e�[�u�����uTask�v�ɊY���̃f�[�^�����݂����ꍇ")
	void testFindByTask_exist() {
		// �e�[�u�����uTask�v��ID�w�肵�A�f�[�^���擾
		Task task = this.service.findByTask(100);
		
		Task testTask = new Task();
		testTask.setId(100);
		testTask.setTitle("�؃g��");
		testTask.setComment("�r���ĕ���������");
		
		// �D��x�}�X�^�̒l��ݒ肷��
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("1");
		mstPriority.setPriorityText("��");
		testTask.setMstPriority(mstPriority);
		
		testTask.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		testTask.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// �X�e�[�^�X�}�X�^�̒l��ݒ肷��
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("1");
		mstStatus.setStatusText("������");
		testTask.setMstStatus(mstStatus);
		
		assertEquals(task, testTask);
	}
	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("findByTask_�e�[�u�����uTask�v�ɊY���̃f�[�^�����݂��Ȃ������ꍇ")
	void testFindByTask_notExist() {
		try {
			// �e�[�u�����uTask�v��ID�w�肵�A�f�[�^���擾
			this.service.findByTask(999);
		} catch (TaskNotFoundException e) {
			assertEquals(e.getMessage(), "�e�[�u�����uTask�v�Ɏw���ID�̃��R�[�h�����݂��܂���B");
		}
	}

	/**================ updateTask ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("updateTask_�e�[�u�����uTask�v�̔C�ӂ̃f�[�^�̍X�V�����������ꍇ")
	void testUpdateTask() {
		// �X�V�Ώۃf�[�^�����O�ɏ�������
		Task updateTgtTask = new Task();
		updateTgtTask.setId(100);
		updateTgtTask.setTitle("�f��ӏ�");
		updateTgtTask.setComment("�l�C�̉f����ς�");
		
		// �D��x�}�X�^�̒l��ݒ肷��
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("3");
		mstPriority.setPriorityText("��");
		updateTgtTask.setMstPriority(mstPriority);
		
		updateTgtTask.setStartTime(LocalDateTime.parse("2022-06-15T12:00"));
		updateTgtTask.setEndTime(LocalDateTime.parse("2022-06-15T14:00:00"));
		
		// �X�e�[�^�X�}�X�^�̒l��ݒ肷��
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("2");
		mstStatus.setStatusText("���蒆");
		updateTgtTask.setMstStatus(mstStatus);
		
		this.service.updateTask(updateTgtTask);
		Task resultTask = this.service.findByTask(100);
		assertEquals(resultTask, updateTgtTask);
	}
	
	/**================ deleteTask ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("deleteTask_�e�[�u�����uTask�v�̔C�ӂ̃f�[�^�̍폜�ɐ��������ꍇ")
	void testDeleteTask() {
		// �e�[�u�����uTask�v�̎w�肵��ID�̃f�[�^���폜����
		this.service.deleteTask(101);
		
		try {
			this.service.findByTask(101);
		} catch (TaskNotFoundException e) {
			assertEquals(e.getMessage(), "�e�[�u�����uTask�v�Ɏw���ID�̃��R�[�h�����݂��܂���B");
		}
	}

}
