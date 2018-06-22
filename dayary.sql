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

insert into diary values(seq_diary_id.nextval, 'quacia', '첫 일기~', '오늘은 날씨가 좋다.', sysdate);

insert into diary values(seq_diary_id.nextval, 'quacia', '두번째 일기~', '쫌따가 점심시간이다.', sysdate);

insert into diary values(seq_diary_id.nextval, 'quacia', '세번째 일기~', '잠못자서 피곤하다ㅠㅠ', sysdate);

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