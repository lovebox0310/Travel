select * from JSP_MEMBER

drop table JSP_MEMBER

select a.constraint_name, a.table_name, b.column_name, a.constraint_type, a.r_constraint_name
from user_constraints a, user_cons_columns b
where
a.constraint_name=b.constraint_name;


DROP TABLE MEMBER_BOARD CASCADE CONSTRAINTS;



create database board3 default character set utf8;

DROP TABLE member3

create table travelmember
(
  id varchar2(12) not null primary KEY
  ,pw varchar2(12) not null
  ,name varchar2(30)
  ,age number(3) check (age < 200)
);

select * from travelmember

insert into travelmember(id, pw, name, age) values ('sbs', 'qw','고길동',21)
insert into travelmember(id, pw, name, age) values ('mbc', 'qw','홍길동',25)
DROP TABLE member3

create table board1
(
   num number(4) not null primary KEY
  ,writer varchar2(21) not null
  ,title varchar2(21) not null
  ,content varchar2(1000) not null
  ,writeday date default sysdate                   
  ,readcnt number(4)           
  ,reproot number(4)
  ,repstep number(4)
  ,repindent number(4)
  
);

insert into board3(num, writer, title, content, writeday, readcnt, reproot, repstep, repindent) values (1, '티모', '버섯', '흡총티모', null, 0,0,0,0)
insert into board3(num, writer, title, content, writeday, readcnt, reproot, repstep, repindent) values (2, '티모2', '버섯2', '흡총티모2', null, 0,0,0,0)
insert into board3(num, writer, title, content, writeday, readcnt, reproot, repstep, repindent) values (3, '피즈', '버섯2', '흡총티모2', null, 0,0,0,0)

DROP TABLE board1
select * from board1

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

select * from (select rownum rnum, num, title, writer, writeday, readcnt from(select * from board3 order by repRoot desc, repStep asc)) where rnum>=? and rnum<=?

update board3 set readcnt = readcnt +1 where num=1
update board3 set readcnt = 0
