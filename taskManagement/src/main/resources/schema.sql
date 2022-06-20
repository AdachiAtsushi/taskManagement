CREATE TABLE task (
    id INT NOT NULL,
    title VARCHAR(10) NOT NULL,
    comment VARCHAR(100) NOT NULL,
    priority CHAR(1) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status_id CHAR(1) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE mst_priority (
  priority CHAR(1) NOT NULL,
  priority_text CHAR(1) NOT NULL,
  PRIMARY KEY(priority)
);

CREATE TABLE mst_status (
  status_id CHAR(1) NOT NULL,
  status_text VARCHAR(5) NOT NULL,
  PRIMARY KEY(status_id)
);