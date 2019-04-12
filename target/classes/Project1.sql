drop table ers_users;

create table ers_users (
    usrId number primary key,
    usrUsername varchar2(50) unique,
    usrPassword varchar2(50),
    usrFirstName varchar2(100),
    usrLastName varchar2(100),
    usrEmail varchar2(150) unique,
    usrRole varchar2(10)
);

drop table ers_reimbursement;

create table ers_reimbursement (
    reimbId number primary key,
    reimbAmount float,
    reimbSubmitted timestamp default sysdate,
    reimbResolved timestamp,
    reimbDescription varchar2(250),
    reimbReceipt blob,
    reimbAuthor number,
    reimbResolver number,
    reimbStatus varchar2(10),
    reimbType varchar2(20)
);

drop sequence userIdSeq;

create sequence userIdSeq
    increment by 1
    start with 0
    minvalue 0
    nocycle;

drop sequence reimbIdSeq;

create sequence reimbIdSeq
    increment by 1
    start with 0
    minvalue 0
    nocycle;

create or replace procedure proc1 (username in varchar2, pssword in varchar2, firstName in varchar2,
                                     lastName in varchar2, email in varchar2, uRole in varchar2)
is
userId number(10) := userIdSeq.nextVal;
begin
    insert into ers_users values (userId, username, pssword, firstName, lastName, email, uRole);
end proc1;
/

create or replace procedure proc2 (amount in float, descriptionText in varchar2, author in number, rType in varchar2)
is
reimId number(10) := reimbIdSeq.nextVal;
--currDate timestamp := sysdate;
begin
    insert into ers_reimbursement (reimbId, reimbAmount, reimbDescription, reimbAuthor, reimbStatus, reimbType) 
    values (reimId, amount, descriptionText, author, 'Pending', rType);
    commit;
    --insert into ers_reimbursement values (reimbId, amount, null, null, descriptionText, null, 
    --                                        author, null, 'pending', rType);
end proc2;
/

create or replace procedure proc3 (reqId in number, resolverId in number, newStatus in varchar2)
is
currDate timestamp := sysdate;
begin
    update ers_reimbursement
    set reimbresolver = resolverId, reimbstatus = newStatus, reimbResolved = currdate
    where reimbId = reqId;
end proc3;
/

--exec proc1 ('sebenner', 'pass1', 'Steven', 'Benner', 'steven.benner.2019@gmail.com', 'Employee');
--exec proc1 ('lskywalker', 'pass1', 'Luke', 'Skywalker', 'luke.skywalker@gmail.com', 'Employee');
--exec proc1 ('payal', 'pass2', 'Payal', 'Bhansal', 'payal.bhansal@revature.com', 'Manager');
commit;


--exec proc2(100.00, '2017-07-23', 'Text', 1, 'Travel');
--drop table employee;
--select * from ers_users;