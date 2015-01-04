

CREATE TABLE card(
	cardid VARCHAR(10) PRIMARY KEY,
	username VARCHAR(10) NOT NULL,
	password VARCHAR(20) NOT NULL,
	balance DOUBLE(5,2),
	state INTEGER NOT NULL
);

CREATE TABLE computer(
	computerid VARCHAR(10) PRIMARY KEY,
	state INTEGER(1) NOT NULL,
	remark VARCHAR(50)
);

CREATE TABLE record(
	recordid INTEGER(5) PRIMARY KEY,
	cardid VARCHAR(10) NOT NULL,
	computerid VARCHAR(10) NOT NULL,
	begintime DATETIME NOT NULL,
	endtime DATETIME,
	fee DOUBLE(5,2)
);

INSERT INTO card VALUES('CARD001', '张三', '123', 100,0);
INSERT INTO card VALUES('CARD002', '李四', '123', 100,0);
INSERT INTO card VALUES('CARD003', '王五', '123', 100,0);
INSERT INTO card VALUES('CARD004', '伟哥', '123', 100,0);
INSERT INTO card VALUES('CARD005', '小强', '123', 100,0);
INSERT INTO card VALUES('CARD006', '四爷', '123', 100,0);

INSERT INTO computer(computerid, state) VALUES('COM001', 0);
INSERT INTO computer(computerid, state) VALUES('COM002', 0);
INSERT INTO computer(computerid, state) VALUES('COM003', 0);
INSERT INTO computer(computerid, state) VALUES('COM004', 0);
INSERT INTO computer(computerid, state) VALUES('COM005', 0);

#COMMIT;

#select * from card;
#select * from computer;
#SELECT * FROM record;

#update computer set state=0;

#CREATE SEQUENCE record_seq;
