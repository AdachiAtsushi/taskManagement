CREATE TABLE task (
    id INT NOT NULL,
    title VARCHAR(10) NOT NULL,
    comment VARCHAR(100) NOT NULL,
    priority CHAR(1) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    finish_flg BOOLEAN,
    PRIMARY KEY(id)
);

CREATE TABLE mst_priority (
  priority CHAR(1) NOT NULL,
  priority_text CHAR(1) NOT NULL,
  PRIMARY KEY(priority)
);