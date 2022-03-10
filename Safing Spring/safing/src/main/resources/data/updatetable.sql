--address table 수정
ALTER TABLE address ADD addr_setting VARCHAR2(3) DEFAULT 'n';

ALTER TABLE address ADD CONSTRAINT CK_addr_setting CHECK(addr_setting IN ( 'y' , 'n' ));

desc address;

--board_file 수정
ALTER TABLE board_file RENAME COLUMN board_file_name TO file_name;

ALTER TABLE board_file RENAME COLUMN board_file_path TO file_path;

desc board_file;

--review_imageFile 수정
ALTER TABLE review_imageFile RENAME COLUMN review_image_name TO file_name;

ALTER TABLE review_imageFile RENAME COLUMN review_image_path TO file_path;

desc review_imageFile;

--board_commnet 수정
ALTER TABLE board_comment RENAME COLUMN comment_root TO comment_lev;

ALTER TABLE board_comment ADD comment_seq NUMBER NULL;

desc board_comment;

--product_review 수정
ALTER TABLE review_product RENAME TO product_review;
ALTER TABLE product_review DROP COLUMN review_image_num;

desc product_review;

--product_review_imagefile 수정
ALTER TABLE review_imageFile ADD review_num  NUMBER NOT NULL ; 
ALTER TABLE review_imageFile ADD CONSTRAINT FK_review_num FOREIGN KEY(review_num) REFERENCES product_review(review_num) ON DELETE CASCADE;
ALTER TABLE review_imageFile RENAME TO product_review_imagefile;


DESC product_review_imagefile;

--order_state 수정
INSERT INTO order_state VALUES (1, '배송 준비');
INSERT INTO order_state VALUES (2, '배송 중');
INSERT INTO order_state VALUES (3, '배송 완료');
INSERT INTO order_state VALUES (4, '환불');

SELECT * FROM order_state;

--product_package 수정
CREATE SEQUENCE seq_order_state
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_order_state
    BEFORE INSERT ON order_state
    FOR EACH ROW
BEGIN
    SELECT seq_order_state.NEXTVAL INTO :NEW.order_state_num FROM dual;
END;

drop SEQUENCE seq_order_state;
drop TRIGGER trg_order_state;

insert into product_package (PACKAGE_NUM, PACKAGE_NAME, PACKAGE_PRICE, FILE_NAME, FILE_PATH)
values(0, "구매null값", 0, "0", "0");

select * from product_package;

--product 수정
CREATE SEQUENCE seq_product
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_product
    BEFORE INSERT ON product
    FOR EACH ROW
BEGIN
    SELECT seq_product.NEXTVAL INTO :NEW.product_num FROM dual;
END;

drop SEQUENCE seq_product;
drop TRIGGER trg_product;

desc product_package;

--member 수정
ALTER TABLE member ADD member_phone VARCHAR2(30) NOT NULL;

ALTER TABLE member MODIFY (member_filename INVISIBLE);
ALTER TABLE member MODIFY (member_filepath INVISIBLE);
ALTER TABLE member MODIFY (member_phone INVISIBLE);
ALTER TABLE member MODIFY (member_admin INVISIBLE);

ALTER TABLE member MODIFY (member_phone VISIBLE);
ALTER TABLE member MODIFY (member_admin VISIBLE);
ALTER TABLE member MODIFY (member_filename VISIBLE);
ALTER TABLE member MODIFY (member_filepath VISIBLE);

DESC member;

--sequence, trigger 생성
CREATE SEQUENCE seq_order_state
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_order_state
    BEFORE INSERT ON order_state
    FOR EACH ROW
BEGIN
    SELECT seq_order_state.NEXTVAL INTO :NEW.order_state_num FROM dual;
END;


-- campinfo 수정

DROP TABLE camping CASCADE CONSTRAINTS;

desc campinfo;

ALTER TABLE CAMPINFO ADD CONSTRAINT PK_campinfo_contentid primary key(contentid);


--중복되는 데이터 확인
select contentid,FACLTNM, count(*)
from campinfo
group by contentid, FACLTNM
having count(*) >1;

--중복 되는 데이터 조회
SELECT R.*
FROM ( SELECT RA.contentid, COUNT(*) OVER(PARTITION BY RA.contentid) AS CNT
        FROM campinfo RA) R
WHERE R.CNT > 1;

SELECT R.*
FROM ( SELECT RA.contentid, 
              COUNT(*) OVER(PARTITION BY RA.contentid) AS CNT, 
              RA.ROWID AS RID, 
              MAX(RA.ROWID) over (partition by RA.contentid) AS MAX_RID 
              FROM campinfo RA) R 
WHERE R.CNT > 1 AND R.RID < R.MAX_RID;

SELECT R.*
FROM ( SELECT RA.contentid, 
              COUNT(*) OVER(PARTITION BY RA.contentid) AS CNT, 
              RA.ROWID AS RID, 
              ROW_NUMBER() OVER(PARTITION BY RA.contentid ORDER BY RA.ROWID DESC) AS RN 
              FROM campinfo RA) R
WHERE R.CNT > 1;

DELETE FROM campinfo R 

WHERE R.ROWID IN ( 
                    SELECT RA.RID 
                    FROM ( 
                            SELECT RA.ROWID AS RID, 
                                   ROW_NUMBER() OVER (PARTITION BY RA.contentid ORDER BY RA.ROWID DESC) AS RN 
                            FROM campinfo RA ) RA 
                    WHERE RA.RN > 1 );

ALTER TABLE CAMPINFO ADD CONSTRAINT PK_campinfo_contentid primary key(contentid);

select * from order_result;

select o.order_num, o.product_num, o.package_num, o.product_price, o.order_state_num,
       p.product_name, k.package_name, s.order_state_name, nvl((select review_check from ORDER_RESULT where o.order_num = order_num), 'null') review_check
from order_ing o
left outer join product p
on  o.product_num = p.product_num
left outer join product_package k
on  o.package_num = k.package_num
left outer join ORDER_STATE s
on  o.order_state_num = s.order_state_num
where member_id = 'in2thefree'
order by order_num desc;

select * from PRODUCT_FILE order by product_num desc;

desc PRODUCT_FILE ;

insert into PRODUCT_FILE(product_num, file_name, file_path)
values (113, '123', '213');

rollback;

select * from product order by product_num desc;


select * from product_file bo;

select * from product order by product_num;


select * from member;


SELECT * FROM member
WHERE member_id = 'in2thefree' AND member_pw = 'Su2351';

select contentid, facltnm, addr1, firstimageurl, intro, resvecl, tel, homepage, induty, prmisnde, insrncat, animalcmgcl, facltdivnm,
				mangedivnm, mgcdiv, operdecl, toiletco, swrmco, wtrplco, sbrscl, mapx, mapy		 
		from campinfo;
        
select p.*, (select tag_key from board_tag where p.package_num = package_num) tag_key
		from product_package p
		where p.package_num < 10
		order by p.package_num;