-- 2020.06.25 kim jung hak
--travelMember는 member.sql 문서로 이동함.

--1
create table board4(
	num number(4) primary key,
	writer varchar2(21) not null,
	title varchar2(60) not null,
	content varchar2(1000),
	location varchar2(3),
	thema varchar2(3),
	writeday date default sysdate,
	readcnt number(4),
	repRoot number(4),
	repStep number(4),
	repIndent number(4)
)

select * from BOARD4



--2
create table board4_location(
	lid varchar2(3),
	lname varchar2(6)
)

--3
create table board4_thema(
	tid varchar2(3),
	tname varchar2(30)
)

--4
alter table board4_location add constraint PK_BOARD4_LOCATION_LID primary key(lid)
--5
alter table board4_thema add constraint PK_BOARD4_THEMA_TID primary key(tid)
--6
alter table board4 add constraint FK_BOARD4_LOCATION foreign key(location) references board4_location(lid)
--7
alter table board4 add constraint FK_BOARD4_THEMA_TID foreign key(thema) references board4_thema(tid)
--8
alter table board4 add filename varchar2(100);

--9
insert into board4_location(lid, lname) values('000', '전체')
insert into board4_location(lid, lname) values('001', '서울')
insert into board4_location(lid, lname) values('002', '인천')
insert into board4_location(lid, lname) values('003', '경기')
insert into board4_location(lid, lname) values('004', '부산')
insert into board4_location(lid, lname) values('005', '경주')
insert into board4_location(lid, lname) values('006', '경상')
insert into board4_location(lid, lname) values('007', '강원')
insert into board4_location(lid, lname) values('008', '전라')
insert into board4_location(lid, lname) values('009', '충청')
insert into board4_location(lid, lname) values('010', '제주')

select * from board4_location

--10
insert into board4_thema(tid, tname) values('000', '전체')
insert into board4_thema(tid, tname) values('001', '신규숙박지')
insert into board4_thema(tid, tname) values('002', '부티크/모텔')
insert into board4_thema(tid, tname) values('003', '게스트하우스')
insert into board4_thema(tid, tname) values('004', '골프리조트&골프텔')
insert into board4_thema(tid, tname) values('005', '공항인근숙박')
insert into board4_thema(tid, tname) values('006', '일출/바다')

select * from board4_thema




select rownum rnum, num, title, writer, writeday, readcnt, repIndent, location, thema
from 
(
select * from BOARD4 order by repRoot desc, repStep asc
)

insert into board4(
	num,
	writer,
	title,
	content,
	location,
	thema,
	writeday,
	readcnt,
	repRoot,
	repStep,
	repIndent
)
values(
	1,
	'm001',
	'그곳은 황홀한...',
	'내용내용내용내용내용내용내용',
	'004',
	'004',
	sysdate,
	0,
	1,
	0,
	0
)
--decode(num, null, 'Y', 'N')num

select * from (
	select rownum rnum, num, writer, title, content, lname, tname, writeday, readcnt, repRoot, repIndent
	from (
		select * 
		from board4 b
		left join board4_location l on b.location = l.lid
		left join board4_thema t on b.thema = t.tid
--		where (CASE WHEN '004' = '' THEN 1 ELSE b.location END) = (CASE WHEN '004' = '' THEN 1 ELSE '004' END)
		where b.location like decode('004', '000', '%', '004')
		and b.thema like decode('003', '000', '%', '003')
--		where '1' = '1'
		order by repRoot desc, repStep asc
	)
) where rnum >= 1 and rnum <= 2

select * from board4 left join board4_location on location = lid left join board4_thema on thema = tid where num = 1

select count(num) amount from board4

update board4 set readcnt = readcnt + 1 where num = 3

commit