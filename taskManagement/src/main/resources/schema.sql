CREATE TABLE task (
    id INT NOT NULL,
    title VARCHAR(10) NOT NULL,
    comment VARCHAR(100) NOT NULL,
    priority CHAR(1) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME,
    finish_flg BOOLEAN,
    PRIMARY KEY(id)
);

-- FIXME ˆê——‰æ–Ê‚Ì‘ÌÙ‚ð•ÏX‚·‚éÛ‚É’è‹`‚·‚é
-- CREATE mst_priority (
--    priority CHAR(1) NOT NULL,
--    priority_value CHAR(1) NOT NULL
-- );