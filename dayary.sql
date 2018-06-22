create user quacia identified by 11111111;

grant dba to quacia;
-- Diary
create table diary(
    id number primary key,
    u_id varchar2(15),
    title varchar2(50),
    content clob not null,
    regdate date not null
);

alter table diary add constraint fk_diary_u_id foreign key(u_id) references member(id);

create sequence seq_diary_id;

insert into diary values(seq_diary_id.nextval, 'quacia', 'ù �ϱ�~', '������ ������ ����.', sysdate);

insert into diary values(seq_diary_id.nextval, 'quacia', '�ι�° �ϱ�~', '�͵��� ���ɽð��̴�.', sysdate);

insert into diary values(seq_diary_id.nextval, 'quacia', '����° �ϱ�~', '����ڼ� �ǰ��ϴ٤Ф�', sysdate);

commit;

select * from diary;

delete diary;

--Member
create table member(
    id varchar2(10) primary key,
    password varchar2(10) not null,
    name varchar2(30) not null,
    email varchar2(30) not null unique,
    gender char(1) check(gender in('m', 'f'))
);

select * from member;