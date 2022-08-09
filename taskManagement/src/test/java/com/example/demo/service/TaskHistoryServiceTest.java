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
	@DisplayName("getClosingTaskList_テーブル名「Task」からステータスが「完了」のレコード群の取得に成功した場合")
	void testGetClosingTaskList_success() {
		// 事前準備
		Task expected1 = new Task();
		expected1.setId(102);
		expected1.setTitle("事務処理");
		expected1.setComment("領収書を整理する");
		expected1.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected1.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected2 = new Task();
		expected2.setId(103);
		expected2.setTitle("買い物(日用品)");
		expected2.setComment("ティッシュを買う");
		expected2.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected2.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// 優先度マスタの値を設定する
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("2");
		mstPriority.setPriorityText("中");
		expected1.setMstPriority(mstPriority);
		expected2.setMstPriority(mstPriority);
		
		// ステータスマスタの値を設定する
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("3");
		mstStatus.setStatusText("完了");
		expected1.setMstStatus(mstStatus);
		expected2.setMstStatus(mstStatus);
		
		List<Task> expectedList = new ArrayList<Task>();
		expectedList.add(expected1);
		expectedList.add(expected2);
		
		// 実行
		List<Task> actual = this.sut.getClosingTaskList();
		
		// 検証
		assertThat(actual, is(expectedList));
	}
	
	/**================ getSearctClosingTaskList ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("getSearctClosingTaskList_テーブル名「Task」のステータスが「完了」のレコード群から検索キーワードに該当するデータの取得に成功した場合")
	void testGetSearctClosingTaskList_success() {
		// 事前準備
		Task expected = new Task();
		expected.setId(103);
		expected.setTitle("買い物(日用品)");
		expected.setComment("ティッシュを買う");
		expected.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// 優先度マスタの値を設定する
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("2");
		mstPriority.setPriorityText("中");
		expected.setMstPriority(mstPriority);
		
		// ステータスマスタの値を設定する
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("3");
		mstStatus.setStatusText("完了");
		expected.setMstStatus(mstStatus);
		
		List<Task> expectedList = new ArrayList<Task>();
		expectedList.add(expected);
		
		// 実行
		List<Task> actual = this.sut.getSearctClosingTaskList("買い物");
		
		// 検証
		assertThat(actual.get(0), is(expected));
	}

	/**================ deleteTaskHistory ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("deleteTaskHistory_テーブル名「Task」から指定したデータの削除に成功した場合")
	void testDeleteTaskHistory_success() {
		// 実行
		this.sut.deleteTaskHistory(102);
		
		// 検証
		boolean actual = this.taskRepository.existsById(102);
		assertThat(actual, is(false));
	}

}
