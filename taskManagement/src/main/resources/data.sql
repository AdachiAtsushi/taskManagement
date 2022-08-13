-- テーブル名「タスク」 
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (1, 'test1', 'おためし', '1', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '1');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (2, 'test2', 'さんこう', '1', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '1');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (3, 'test3', 'てすと', '2', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '2');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (4, 'test4', 'てすと', '2', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '2');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (5, 'test5', 'ためそう', '3', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '3');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (6, 'test6', 'ためになるはなし', '3', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '3');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (7, 'test7', 'ためになるはなし', '3', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '4');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (8, 'test8', 'ためそう', '3', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '1');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (9, 'test9', 'ためになるはなし', '3', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '1');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (10, 'test10', 'ためになるはなし', '3', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '1');


-- マスタテーブル名「優先度」
INSERT INTO mst_priority (priority, priority_text) VALUES ('1', '高');
INSERT INTO mst_priority (priority, priority_text) VALUES ('2', '中');
INSERT INTO mst_priority (priority, priority_text) VALUES ('3', '低');

-- マスタテーブル名「ステータス」
INSERT INTO mst_status (status_id, status_text) VALUES ('1', '未着手');
INSERT INTO mst_status (status_id, status_text) VALUES ('2', '着手中');
INSERT INTO mst_status (status_id, status_text) VALUES ('3', '完了');
INSERT INTO mst_status (status_id, status_text) VALUES ('4', '保留');