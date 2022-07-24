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
	@DisplayName("getTaskList_テーブル名「Task」の全データの取得に成功した場合")
	void testGetTaskList_success() {
		// 事前準備
		Task expected1 = new Task();
		expected1.setId(100);
		expected1.setTitle("筋トレ");
		expected1.setComment("腕立て伏せをする");
		expected1.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected1.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected2 = new Task();
		expected2.setId(101);
		expected2.setTitle("読書");
		expected2.setComment("自己啓発本を読む");
		expected2.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected2.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected3 = new Task();
		expected3.setId(102);
		expected3.setTitle("事務処理");
		expected3.setComment("領収書を整理する");
		expected3.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected3.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected4 = new Task();
		expected4.setId(103);
		expected4.setTitle("買い物(日用品)");
		expected4.setComment("ティッシュを買う");
		expected4.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected4.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		Task expected5 = new Task();
		expected5.setId(104);
		expected5.setTitle("掃除");
		expected5.setComment("リビングを掃除する");
		expected5.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected5.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// 優先度マスタの値を設定する
		MstPriority mstPriority1 = new MstPriority();
		mstPriority1.setPriority("1");
		mstPriority1.setPriorityText("高");
		
		MstPriority mstPriority2 = new MstPriority();
		mstPriority2.setPriority("2");
		mstPriority2.setPriorityText("中");
		
		MstPriority mstPriority3 = new MstPriority();
		mstPriority3.setPriority("3");
		mstPriority3.setPriorityText("低");
		
		expected1.setMstPriority(mstPriority1);
		expected2.setMstPriority(mstPriority1);
		expected3.setMstPriority(mstPriority2);
		expected4.setMstPriority(mstPriority2);
		expected5.setMstPriority(mstPriority3);
		
		// ステータスマスタの値を設定する
		MstStatus mstStatus1 = new MstStatus();
		mstStatus1.setStatusId("1");
		mstStatus1.setStatusText("未着手");
		
		MstStatus mstStatus2 = new MstStatus();
		mstStatus2.setStatusId("2");
		mstStatus2.setStatusText("着手中");
		
		MstStatus mstStatus3 = new MstStatus();
		mstStatus3.setStatusId("3");
		mstStatus3.setStatusText("完了");
		
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
		
		// 実行
		List<Task> actual = this.sut.getTaskList();
		
		// 検証
		assertThat(actual, is(expectedList));
	}

	/**================ getSearchTaskList ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("getSearchTaskList_テーブル名「Task」から検索キーワードに該当するデータの取得に成功した場合")
	void testGetSearchTaskList_success() {
		// 事前準備
		Task expected1 = new Task();
		expected1.setId(100);
		expected1.setTitle("筋トレ");
		expected1.setComment("腕立て伏せをする");
		expected1.setStartTime(LocalDateTime.parse("2022-06-01T12:00"));
		expected1.setEndTime(LocalDateTime.parse("2022-06-02T12:00:00"));
		
		// 優先度マスタの値を設定する
		MstPriority mstPriority1 = new MstPriority();
		mstPriority1.setPriority("1");
		mstPriority1.setPriorityText("高");
		expected1.setMstPriority(mstPriority1);
		
		// ステータスマスタの値を設定する
		MstStatus mstStatus1 = new MstStatus();
		mstStatus1.setStatusId("1");
		mstStatus1.setStatusText("未着手");
		expected1.setMstStatus(mstStatus1);
		
		List<Task> expectedList = new ArrayList<Task>();
		expectedList.add(expected1);
		
		// 実行
		List<Task> actual = this.sut.getSearchTaskList("筋トレ");
		
		// 検証
		assertThat(actual.get(0), is(expected1));
	}

	/**================ delete ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("delete_テーブル名「Task」からデータの削除に成功した場合")
	void testDelete_success() {
		// 実行
		this.sut.delete(100);
		
		// 検証
		boolean actual = this.taskRepository.existsById(100);
		assertThat(actual, is(false));
	}

}
