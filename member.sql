
-- 현재 완성형.
create table travelMember (
	id varchar2(4) primary key, 
	name varchar2(8) not null, 
	age number(4) check(age<200), 
	pw varchar2(8) not null,
	authority varchar2(2) default '01'
)

select * from travelMember

-- 2020-06-25 추가 : 기존 맴버테이블에 권한을 추가한다. board6 필요함.

create table tb_authority(
aid varchar2(2) primary key,
aname varchar2(8) not null
)

insert into tb_authority values ('00', 'admin')
insert into tb_authority values ('01', 'member')
insert into tb_authority values ('02', 'resting')
insert into tb_authority values ('03', 'quit')

alter table travelMember add authority varchar2(2) default '01'