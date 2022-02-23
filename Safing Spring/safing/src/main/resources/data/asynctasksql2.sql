select i.*
from ORDER_ING i,
    (select order_num, order_state_num from ORDER_RESULT where member_id = 'in2thefree') r
where i.member_id = 'in2thefree';

                        order_num     ;
	    private String product_name ;
	    private String package_name ;
	    private int product_num     ;
	    private int package_num     ;
	    private int product_price 	;
	    private String file_path	;
	    private int order_state_num ;
	    private String review_check ;
	    private String order_state_name;


left outer join product p
on  p.product_num = c.product_num
left outer join product_package k
on  k.package_num = c.package_num

select * from ORDER_ING;
select * from ORDER_RESULT;
select * from ORDER_STATE;

select * from cart;


select o.order_num, o.product_num, o.package_num, o.product_price, o.order_state_num,
       p.product_name, k.package_name, r.review_check, s.order_state_name
from order_ing o
left outer join product p
on  o.product_num = p.product_num
left outer join product_package k
on  o.package_num = k.package_num
left outer join ORDER_STATE s
on  o.order_state_num = s.order_state_num
left outer join ORDER_RESULT r
on  o.order_num = r.order_num
where member_id = 'in2thefree'
order by order_num desc;


select o.order_num, o.product_num, o.package_num, o.product_price, o.order_state_num,
       p.product_name, k.package_name, s.order_state_name, (select review_check from ORDER_RESULT where o.order_num = order_num) review_check
from order_ing o
left outer join product p
on  o.product_num = p.product_num
left outer join product_package k
on  o.package_num = k.package_num
left outer join ORDER_STATE s
on  o.order_state_num = s.order_state_num
where member_id = 'in2thefree'
order by order_num desc;

left outer join ORDER_RESULT r
on  o.order_num = r.order_num


select * from address;      
select * from address_sub; 
desc address;


insert into address_sub
values ((select max(addr_num) from address), )

(select max(addr_num) from address)

select r.member_id, (select member_filepath from member where r.member_id = member_id) member_filepath, r.review_num, r.rating, b.board_content, b.board_writedate, b.board_like_cnt
from (select max(review_num) review_num, rating, member_id, board_id, package_num from product_review where package_num = 2 group by rating, member_id, board_id, package_num) r
left outer join (select board_content, TO_CHAR(board_writedate, 'YYYY-MM-DD') board_writedate, board_like_cnt, board_id from board) b
on b.board_id = r.board_id
order by r.review_num desc;

select r.member_id, (select member_filepath from member where r.member_id = member_id) member_filepath, r.review_num, r.rating, b.board_content, b.board_writedate, b.board_like_cnt
from (select max(review_num) review_num, rating, member_id, board_id, product_num from product_review where product_num = 2 group by rating, member_id, board_id, product_num) r
left outer join (select board_content, TO_CHAR(board_writedate, 'YYYY-MM-DD') board_writedate, board_like_cnt, board_id from board) b
on b.board_id = r.board_id
order by r.review_num desc;

select board_content, TO_CHAR(board_writedate, 'YYYY-MM-DD') board_writedate, board_like_cnt, board_id from board;
select max(review_num) review_num, rating, member_id, board_id, package_num from product_review where package_num = 2 group by rating, member_id, board_id, package_num;

select * from cart;

delete from cart;

commit;

select * from CART_DETAIL_CNT;

delete from CART_DETAIL_CNT where cart_num = -1;

select max(cart_num) from cart where member_id = 'in2thefree';


select max(cart_num) from cart;


select * from board;

select * from PRODUCT_REVIEW order by review_num desc;

select * from PRODUCT_REVIEW_IMAGEFILE;

insert into board (member_id, board_title, board_content, board_kinds)
values(#{member_id}, #{board_content}, 'review');


insert into PRODUCT_REVIEW (product_num, member_id, rating, board_id)
values(#{board_content}, #{member_id}, #{rating}, #{board_id});

insert into PRODUCT_REVIEW_IMAGEFILE (file_path, review_num)
values(#{file_path},#{review_num});

select max(board_id) from board where member_id = 'in2thefree';


select * from board where member_id = 'in2thefree' order by board_id desc;

delete from board where board_id >260;

commit;

select * from PRODUCT_REVIEW_IMAGEFILE order by review_num desc;

select * from product order by product_num ;

select * from member;

select * from ORDER_RESULT;

update ORDER_RESULT


select * from ORDER_RESULT;
select * from order_ing;
select * from ORDER_DETAIL_CNT;

select * from order_ing;

insert into order_ing(member_id, product_num, package_num, reciver_num, receiver_addr, order_count, product_price)
values(#{member_id}, #{product_num, package_num}, #{reciver_num}, #{receiver_addr}, #{order_count}, #{product_price})

alter table order_ing modify order_state_num default 1;

select * from ORDER_DETAIL_CNT;

insert all 
into ORDER_DETAIL_CNT(order_num, pakcage_num, product_num, order_count)
values(#{order_num},package_num, product_num, order_count)
select *
from CART_DETAIL_CNT
where cart_num = 86;

select * from CART_DETAIL_CNT where cart_num = 86;

select * from address;

select * from PACKAGE_DETAIL;

insert into order_ing(MEMBER_ID, PRODUCT_NUM, PACKAGE_NUM, ORDER_STATE_NUM, RECEIVER_NAME, RECEIVER_PHONE, RECEIVER_ADDR, ORDER_COUNT, PRODUCT_PRICE)
values(#{MEMBER_ID}, 0, #{PACKAGE_NUM}, ORDER_STATE_NUM, #{RECEIVER_NAME}, #{RECEIVER_PHONE}, #{RECEIVER_ADDR}, #{ORDER_COUNT}, #{PRODUCT_PRICE})

select * from order_ing;

delete from order_ing where order_num >10;



commit;

desc order_ing;

insert into order_ing(member_id, product_num, package_num, order_state_num, receiver_name, receiver_phone, receiver_addr, order_count, product_price)
values('in2thefree', 0, 2, 1, '0', '0', '0', 0, 0)

order_ing

insert into order_detail_cnt(order_num, package_num, product_num, order_count)
values(25, 1, 1,1);

delete from order_detail_cnt  where order_num >30;

select * from order_detail_cnt;


insert all 
into order_detail_cnt(order_num, package_num, product_num, order_count)
values(44, package_num, product_num, order_count)
select package_num, product_num, order_count
from cart_detail_cnt
where cart_num = 93;

select * from cart_detail_cnt;

select * from cart;
