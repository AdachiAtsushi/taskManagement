-- �e�[�u�����u�^�X�N�v 
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (1, 'test1', '�����߂�', '1', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '1');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (2, 'test2', '���񂱂�', '2', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '1');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (3, 'test3', '�Ă���', '3', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '2');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (4, 'test4', '���߂���', '2', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '3');
INSERT INTO task (id, title, comment, priority, start_time, end_time, status_id) VALUES (5, 'test5', '���߂ɂȂ�͂Ȃ�', '2', '2022-06-01 12:00:00', '2022-06-02 12:00:00', '4');

-- �}�X�^�e�[�u�����u�D��x�v
INSERT INTO mst_priority (priority, priority_text) VALUES ('1', '��');
INSERT INTO mst_priority (priority, priority_text) VALUES ('2', '��');
INSERT INTO mst_priority (priority, priority_text) VALUES ('3', '��');

-- �}�X�^�e�[�u�����u�X�e�[�^�X�v
INSERT INTO mst_status (status_id, status_text) VALUES ('1', '������');
INSERT INTO mst_status (status_id, status_text) VALUES ('2', '���蒆');
INSERT INTO mst_status (status_id, status_text) VALUES ('3', '����');
INSERT INTO mst_status (status_id, status_text) VALUES ('4', '�ۗ�');