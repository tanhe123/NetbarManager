

CREATE TABLE card(
	cardid INTEGER PRIMARY KEY auto_increment,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	balance DOUBLE(5,2),
	state INTEGER NOT NULL
);

# 卡号自动从1000开始增长
ALTER TABLE card auto_increment=1000;

CREATE TABLE computer(
	computerid INTEGER PRIMARY KEY auto_increment,
	state INTEGER(1) NOT NULL
#	remark VARCHAR(50)
);

CREATE TABLE record(
	recordid INTEGER PRIMARY KEY auto_increment,
	cardid INTEGER NOT NULL,
	computerid INTEGER NOT NULL,
	begintime DATETIME NOT NULL,
	endtime DATETIME,
	fee DOUBLE(5,2)
);

INSERT INTO card(username, password, balance, state) VALUES('张三', '123', 100.00,0);
INSERT INTO card(username, password, balance, state) VALUES('李四', '123', 100.00,0);
INSERT INTO card(username, password, balance, state) VALUES('王五', '123', 100.0,0);
INSERT INTO card(username, password, balance, state) VALUES('伟哥', '123', 100.0,0);
INSERT INTO card(username, password, balance, state) VALUES('小强', '123', 100.0,0);
INSERT INTO card(username, password, balance, state) VALUES('四爷', '123', 100.0,0);

INSERT INTO computer(state) VALUES(0);
INSERT INTO computer(state) VALUES(0);
INSERT INTO computer(state) VALUES(0);
INSERT INTO computer(state) VALUES(0);
INSERT INTO computer(state) VALUES(0);

#COMMIT;

#select * from card;
#select * from computer;
#SELECT * FROM record;

#update computer set state=0;

#CREATE SEQUENCE record_seq;
