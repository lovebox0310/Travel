--게시글 Table.
create table notice(
	num number(4) primary key,
	writer varchar2(21) not null,
	title varchar2(60) not null,
	content varchar2(1000),
	writeday date default sysdate,
	readcnt number(4) default 0
	filename varchar2(100)
);


--답글 Table.
create table notice_comment(
	comment_num number(4) primary key,
	comment_board number,
	comment_content varchar2(1000),
	constraint FK_KEY foreign key(comment_board) references notice(num) on delete cascade,
	comment_writer varchar2(21),	
	comment_day date default sysdate,
	repRoot number(4),
	repStep number(4),
	repIndent number(4)
);
