create user quacia identified by 11111111;

grant dba to quacia;

create table diary(
    id number primary key,
    u_id varchar2(15),
    title varchar2(50),
    content clob not null,
    regdate date not null
);

create sequence seq_diary_id;

insert into diary values(seq_diary_id.nextval, 'quacia', 'ù �ϱ�~', '������ ������ ����.', sysdate);

insert into diary values(seq_diary_id.nextval, 'quacia', '�ι�° �ϱ�~', '�͵��� ���ɽð��̴�.', sysdate);

insert into diary values(seq_diary_id.nextval, 'quacia', '����° �ϱ�~', '����ڼ� �ǰ��ϴ٤Ф�', sysdate);

commit;