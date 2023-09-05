-- emaillist

-- insert
insert into emaillist values(null, '김', '소정', 'sojeong@gmail.com');

-- findAll
select no, first_name, last_name, email from emaillist order by no desc;

-- delete
delete from emaillist where email='sojeong@gmail.com';

-- findAll
select no, first_name, last_name, email from emaillist order by no desc;



