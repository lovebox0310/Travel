--게시판 테이블
create table tb_board5(
num number(4) primary key,
writer varchar2(21) not null,
location varchar2(6) not null,
title varchar2(60) not null,
content varchar2(1000),
writeday date default sysdate,
readcnt number(4) default 0,
repRoot number(4),
repStep number(4),
repIndent number(4),
constraint fk_location_location foreign key (location) references tb_location (lid)
constraint fk_writer_member foreign key (writer) references tb_member (id)
)

--게시판의 파일 업로드 테이블
create table tb_fileUpload(
fnum number(4) primary key,
fileName varchar2(100),
orgfileName varchar2(100),
uploadFolder varchar2(100),
constraint fk_fileUpload_fnum foreign key (fnum) references tb_board5 (num)
)

select * from tb_fileUpload

--게시글의 지역 테이블
create table tb_location(
lid varchar2(4) primary key,
lname varchar2(6) not null
)
insert into tb_location (lid, lname) values ('001', '서울');
insert into tb_location (lid, lname) values ('002', '부산');
insert into tb_location (lid, lname) values ('003', '대구');
insert into tb_location (lid, lname) values ('004', '인천');
insert into tb_location (lid, lname) values ('005', '광주');
insert into tb_location (lid, lname) values ('006', '대전');
insert into tb_location (lid, lname) values ('007', '울산');
insert into tb_location (lid, lname) values ('008', '경기');
insert into tb_location (lid, lname) values ('009', '강원');
insert into tb_location (lid, lname) values ('010', '충북');
insert into tb_location (lid, lname) values ('011', '충남');
insert into tb_location (lid, lname) values ('012', '전북');
insert into tb_location (lid, lname) values ('013', '전남');
insert into tb_location (lid, lname) values ('014', '경북');
insert into tb_location (lid, lname) values ('015', '경남');
insert into tb_location (lid, lname) values ('016', '제주');
insert into tb_location (lid, lname) values ('017', '기타');

update tb_location set lid='001' where lname = '서울'
update tb_location set lid='002' where lname = '부산'
update tb_location set lid='003' where lname = '대구'
update tb_location set lid='004' where lname = '인천'
update tb_location set lid='005' where lname = '광주'
update tb_location set lid='006' where lname = '대전'
update tb_location set lid='007' where lname = '울산'
update tb_location set lid='008' where lname = '경기'
update tb_location set lid='009' where lname = '강원'
update tb_location set lid='010' where lname = '충북'
update tb_location set lid='011' where lname = '충남'
update tb_location set lid='012' where lname = '전북'
update tb_location set lid='013' where lname = '전남'
update tb_location set lid='014' where lname = '경북'
update tb_location set lid='015' where lname = '경남'
update tb_location set lid='016' where lname = '제주'
update tb_location set lid='017' where lname = '기타'


--멤버 테이블
create table tb_member(
id varchar2(6) primary key,
pw varchar2(20) not null,
name varchar2(20) not null,
age number(3) check(age<200),
authority varchar2(2) default '01'
)

insert into tb_member values('admin67', '1', 'test', 15, '02')

alter table tb_member add 
constraint fk_member_authority foreign key (authority) 
references tb_authority(aid)

alter table tb_member modify authority varchar2(2) default '01'


create table tb_authority(
aid varchar2(2) primary key,
aname varchar2(8) not null
)
insert into tb_authority values ('00', 'admin')
insert into tb_authority values ('01', 'member')
insert into tb_authority values ('02', 'resting')
insert into tb_authority values ('03', 'quit')

update tb_member set authority='03' where id='m001'

--전체 검색 
select * from tb_member
select * from tb_fileUpload
select * from tb_board5
select * from tb_authority


select * from tb_board5 b 
left join tb_fileUpload f on b.num = f.fnum where fnum is not null
and title LIKE DECODE('테스트', NULL, '%', '%"+ searchTitle +"%') and location LIKE DECODE('01', NULL, '%', '01')
				



alter table tb_board5 modify title varchar2(100)

select * from 
(
	select rownum rnum, num, location, l.lname, title, f.fnum, writer, writeday, readcnt, repIndent from tb_board5 b 
	left join tb_location l on b.location = l.lid 
	left join TB_FILEUPLOAD f on b.num = f.fnum
	order by repRoot desc, repStep asc 
) where rnum>=1 and rnum<=10 



select * from
(
select rownum rnum, num, location, lname, title, fnum, writer, writeday, readcnt, repIndent from 
(
	select * from tb_board5 b 
	left join tb_location l on b.location = l.lid 
	left join TB_FILEUPLOAD f on b.num = f.fnum
	order by repRoot desc, repStep asc 
)
)
where rnum>=1 and rnum<=10 



select * from 
(
	select rownum rnum, num, location, title, writer, writeday, readcnt, repIndent from(
		select * from tb_board5 order by repRoot desc, repStep asc
	)
) where rnum>=1 and rnum<=20


--page검색
select * from 
				( 
				select rownum rnum, num, location, lname, title, fnum, writer, writeday, readcnt, repIndent from  
				(
					select * from tb_board5 b  
					left join tb_location l on b.location = l.lid  
					left join TB_FILEUPLOAD f on b.num = f.fnum 
				order by repRoot desc, repStep asc
				)WHERE title LIKE DECODE('테스트', NULL, '%', '%테스트%') and location LIKE DECODE('01', NULL, '%', '01')
				)	where rnum>= 1 and rnum<=10
				
				
select count(num) from tb_board5 where location=?
select count(num) from tb_board5 where title LIKE DECODE(null, NULL, '%', '%null%') and location LIKE DECODE(null, NULL, '%', null)


--drop table tb_fileUpload
--drop table tb_board5

				
				
