package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
	TaskDetailService sut;
	
	@Autowired
	MstPriorityRepository mstPriorityRepository;
	
	@Autowired
	MstStatusRepository mstStatusRepository;
	
	/**================ findByTask ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("findByTask_テーブル名「Task」に該当のデータが存在した場合")
	void testFindByTask_exist() {
		// テーブル名「Task」にID指定し、データを取得
		Task acutual = this.sut.findByTask(100);
		
		Task expected = new Task();
		expected.setId(100);
		expected.setTitle("筋トレ");
		expected.setComment("腕立て伏せをする");
		
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
		
		assertThat(acutual, is(expected));
	}
	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("findByTask_テーブル名「Task」に該当のデータが存在しなかった場合")
	void testFindByTask_notExist() {
		try {
			// テーブル名「Task」にID指定し、データを取得
			this.sut.findByTask(999);
		} catch (TaskNotFoundException e) {
			assertThat(e.getMessage(), is("テーブル名「Task」に指定のIDのレコードが存在しません。"));
		}
	}

	/**================ updateTask ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("updateTask_テーブル名「Task」の任意のデータの更新が成功した場合")
	void testUpdateTask() {
		// 更新対象データを事前に準備する
		Task expected = new Task();
		expected.setId(100);
		expected.setTitle("映画鑑賞");
		expected.setComment("人気の映画を観る");
		
		// 優先度マスタの値を設定する
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority("3");
		mstPriority.setPriorityText("低");
		expected.setMstPriority(mstPriority);
		
		expected.setStartTime(LocalDateTime.parse("2022-06-15T12:00"));
		expected.setEndTime(LocalDateTime.parse("2022-06-15T14:00:00"));
		
		// ステータスマスタの値を設定する
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId("2");
		mstStatus.setStatusText("着手中");
		expected.setMstStatus(mstStatus);
		
		this.sut.updateTask(expected);
		Task actual = this.sut.findByTask(100);
		assertThat(actual, is(expected));
	}
	
	/**================ deleteTask ================**/	
	@Test
	@Sql({"/test-task-delete.sql", "/test-task-insert.sql"})
	@DisplayName("deleteTask_テーブル名「Task」の任意のデータの削除に成功した場合")
	void testDeleteTask() {
		// テーブル名「Task」の指定したIDのデータを削除する
		this.sut.deleteTask(101);
		
		try {
			this.sut.findByTask(101);
		} catch (TaskNotFoundException e) {
			assertThat(e.getMessage(), is("テーブル名「Task」に指定のIDのレコードが存在しません。"));
		}
	}

}
