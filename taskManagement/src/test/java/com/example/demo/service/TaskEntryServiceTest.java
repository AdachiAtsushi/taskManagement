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
	@DisplayName("insertTask_テーブル名「Task」に登録が成功した場合")
	public void testInsertTask_success() {
		// 事前準備データ
		Task expected = new Task();
		expected.setId(105);
		expected.setTitle("瞑想");
		expected.setComment("瞑想をする");
		
		// 優先度マスタの値を設定する
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("1");
		mstPriority.setPriorityText("高");
		expected.setMstPriority(mstPriority);
		
		expected.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// ステータスマスタの値を設定する
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("1");
		mstStatus.setStatusText("未着手");
		expected.setMstStatus(mstStatus);
		
		// 実行
		this.sut.insertTask(expected);
		
		// 検証
		Optional<Task> actual = this.taskRepository.findById(105);
		assertThat(actual.get(), is(expected));
	}
	
	/**================ searchTaskMaxId ================**/
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("searchTaskMaxId_テーブル名「Task」のIDの最大値の取得が成功した場合")
	public void testSearchTaskMaxId_success() {
		// 事前準備
		int expected = 104;
		
		// 実行
		int actual = this.sut.searchTaskMaxId();
		
		// 検証
		assertThat(actual, is(expected));
	}
	
	/**================ findByMstPriority ================**/
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("findByMstPriority_テーブル名「優先度」の全データ取得に成功した場合")
	public void testFindByMstPriority_success() {
		// 事前準備
		MstPriority expected_1 = new MstPriority();
		expected_1.setPriority("1");
		expected_1.setPriorityText("高");
		
		MstPriority expected_2 = new MstPriority();
		expected_2.setPriority("2");
		expected_2.setPriorityText("中");
		
		MstPriority expected_3 = new MstPriority();
		expected_3.setPriority("3");
		expected_3.setPriorityText("低");
		
		List<MstPriority> expectedList = new ArrayList<MstPriority>();
		expectedList.add(expected_1);
		expectedList.add(expected_2);
		expectedList.add(expected_3);
		
		// 実行
		List<MstPriority> actual = this.sut.findByMstPriority();
		
		// 検証
		assertThat(actual, is(expectedList));
	}
	
}
