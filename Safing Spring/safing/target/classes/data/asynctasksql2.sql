select i.*, r.*
from ORDER_ING i,
    (select order_num, order_state_num from ORDER_RESULT where member_id = 'in2thefree') r
where i.member_id = 'in2thefree';




left outer join product p
on  p.product_num = c.product_num
left outer join product_package k
on  k.package_num = c.package_num

select * from ORDER_ING;
select * from ORDER_RESULT;
select * from ORDER_STATE;

insert into ORDER_STATE(order_state_num, order_state_name)
values (0, '입금확인 중');

commit;

select c.cart_num, c.product_num, c.package_num, c.product_price, c.order_count, p.product_name, k.package_name
		from cart c
		left outer join product p
		on  p.product_num = c.product_num
		left outer join product_package k
		on  k.package_num = c.package_num
		where member_id = #{member_id}
		order by order_num desc
