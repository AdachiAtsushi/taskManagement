CREATE TABLE task (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(10) NOT NULL,
    comment VARCHAR(100) NOT NULL,
    priority CHAR(1) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME,
    finish_flg BOOLEAN,
    PRIMARY KEY(id)
);

-- FIXME àÍóóâÊñ ÇÃëÃçŸÇïœçXÇ∑ÇÈç€Ç…íËã`Ç∑ÇÈ
-- CREATE mst_priority (
--    priority CHAR(1) NOT NULL,
--    priority_value CHAR(1) NOT NULL
-- );