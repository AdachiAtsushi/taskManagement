package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
public class TaskEntryServiceTest {
	
	@Autowired
	TaskEntryService sut;
	
	@Autowired
	TaskRepository taskRepository;
	
	/**================ insertTask ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("insertTask_�e�[�u�����uTask�v�ɓo�^�����������ꍇ")
	public void testInsertTask_success() {
		// ���O�����f�[�^
		Task expected = new Task();
		expected.setId(105);
		expected.setTitle("�ґz");
		expected.setComment("�ґz������");
		
		// �D��x�}�X�^�̒l��ݒ肷��
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("1");
		mstPriority.setPriorityText("��");
		expected.setMstPriority(mstPriority);
		
		expected.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// �X�e�[�^�X�}�X�^�̒l��ݒ肷��
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("1");
		mstStatus.setStatusText("������");
		expected.setMstStatus(mstStatus);
		
		// ���s
		this.sut.insertTask(expected);
		
		// ����
		Optional<Task> actual = this.taskRepository.findById(105);
		assertThat(actual.get(), is(expected));
	}
	
	/**================ searchTaskMaxId ================**/
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("searchTaskMaxId_�e�[�u�����uTask�v��ID�̍ő�l�̎擾�����������ꍇ")
	public void testSearchTaskMaxId_success() {
		// ���O����
		int expected = 104;
		
		// ���s
		int actual = this.sut.searchTaskMaxId();
		
		// ����
		assertThat(actual, is(expected));
	}
	
	/**================ findByMstPriority ================**/
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("findByMstPriority_�e�[�u�����u�D��x�v�̑S�f�[�^�擾�ɐ��������ꍇ")
	public void testFindByMstPriority_success() {
		// ���O����
		MstPriority expected_1 = new MstPriority();
		expected_1.setPriority("1");
		expected_1.setPriorityText("��");
		
		MstPriority expected_2 = new MstPriority();
		expected_2.setPriority("2");
		expected_2.setPriorityText("��");
		
		MstPriority expected_3 = new MstPriority();
		expected_3.setPriority("3");
		expected_3.setPriorityText("��");
		
		List<MstPriority> expectedList = new ArrayList<MstPriority>();
		expectedList.add(expected_1);
		expectedList.add(expected_2);
		expectedList.add(expected_3);
		
		// ���s
		List<MstPriority> actual = this.sut.findByMstPriority();
		
		// ����
		assertThat(actual, is(expectedList));
	}
	
}
