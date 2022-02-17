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
ALTER TABLE product_review ADD rating number;

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

--product_package 수정
ALTER TABLE product_package ADD file_name varchar2(1000);
ALTER TABLE product_package ADD file_path varchar2(1000);
ALTER TABLE product_package modify package_price number;

desc product_package;

CREATE SEQUENCE seq_product_package
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_product_package
    BEFORE INSERT ON product_package
    FOR EACH ROW
BEGIN
    SELECT seq_product_package.NEXTVAL INTO :NEW.PACKAGE_NUM FROM dual;
END;

ALTER TABLE product_package modify PACKAGE_PRICE null;


--product 수정
ALTER TABLE product ADD product_kind varchar2(20);
ALTER TABLE product modify product_date default sysdate;
ALTER TABLE product modify product_name varchar2(1000);

desc product;

CREATE SEQUENCE seq_product
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_product
    BEFORE INSERT ON product
    FOR EACH ROW
BEGIN
    SELECT seq_product.NEXTVAL INTO :NEW.product_num FROM dual;
END;


-- board_tag 수정
CREATE SEQUENCE seq_board_tag
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_board_tag
    BEFORE INSERT ON board_tag
    FOR EACH ROW
BEGIN
    SELECT seq_board_tag.NEXTVAL INTO :NEW.tag_num FROM dual;
END;

select * from board_tag;


-- order_result 수정
ALTER TABLE order_result ADD review_check varchar2(3) default 'n';
ALTER TABLE order_result ADD CONSTRAINT CK_review_check CHECK(review_check IN ( 'y' , 'n' ));

desc order_result;

-- product_file 수정
CREATE SEQUENCE seq_product_file
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_product_file
    BEFORE INSERT ON product_file
    FOR EACH ROW
BEGIN
    SELECT seq_product_file.NEXTVAL INTO :NEW.file_num FROM dual;
END;

select * from product_file;


--address 수정
CREATE SEQUENCE seq_address
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_address
    BEFORE INSERT ON address
    FOR EACH ROW
BEGIN
    SELECT seq_address.NEXTVAL INTO :NEW.addr_num FROM dual;
END;

--cart 수정
CREATE SEQUENCE seq_cart
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_cart
    BEFORE INSERT ON cart
    FOR EACH ROW
BEGIN
    SELECT seq_cart.NEXTVAL INTO :NEW.cart_num FROM dual;
END;

ALTER TABLE cart ADD PACKAGE_PRICE number;
ALTER TABLE cart drop column PACKAGE_PRICE;

desc cart;

alter table cart rename column product_cnt to order_count; 

--order_ing 수정
CREATE SEQUENCE seq_order_ing
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_order_ing
    BEFORE INSERT ON order_ing
    FOR EACH ROW
BEGIN
    SELECT seq_order_ing.NEXTVAL INTO :NEW.order_num FROM dual;
END;

ALTER TABLE order_ing modify ORDER_DATE default sysdate;

--order_result 수정
CREATE SEQUENCE seq_order_result
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_order_result
    BEFORE INSERT ON order_result
    FOR EACH ROW
BEGIN
    SELECT seq_order_result.NEXTVAL INTO :NEW.result_num FROM dual;
END;

ALTER TABLE order_result add result_date date default sysdate;

-- PRODUCT_REVIEW 수정
desc product_review;

desc board;

ALTER TABLE PRODUCT_REVIEW drop column REVIEW_CONTENT;
ALTER TABLE PRODUCT_REVIEW drop column REVIEW_DATE;
ALTER TABLE PRODUCT_REVIEW ADD BOARD_ID NUMBER;
ALTER TABLE PRODUCT_REVIEW ADD constraint FK_board_id_review foreign key(board_id) references board(BOARD_ID) on delete cascade;

CREATE SEQUENCE seq_product_review
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_product_review
    BEFORE INSERT ON product_review
    FOR EACH ROW
BEGIN
    SELECT seq_product_review.NEXTVAL INTO :NEW.REVIEW_NUM FROM dual;
END;

--board 수정
ALTER TABLE board modify BOARD_WRITEDATE default sysdate;
ALTER TABLE board modify BOARD_UPDATEDATE default null;
ALTER TABLE board modify BOARD_READ_CNT default 0;
ALTER TABLE board modify BOARD_LIKE_CNT default 0;
ALTER TABLE board drop constraint CK_BOARD_KINDS_BOARD;
ALTER TABLE board add constraint CK_BOARD_KINDS_BOARD CHECK(board_kinds in ( 'free'  ,  'video' ,  'album' ,   'notice' ,  'review'  ,  'camping', 'comment'));
desc board;

CREATE SEQUENCE seq_board
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_board
    BEFORE INSERT ON board
    FOR EACH ROW
BEGIN
    SELECT seq_board.NEXTVAL INTO :NEW.BOARD_ID FROM dual;
END;

--PRODUCT_REVIEW_IMAGEFILE 수정
CREATE SEQUENCE seq_product_review_imagefile
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_product_review_imagefile
    BEFORE INSERT ON product_review_imagefile
    FOR EACH ROW
BEGIN
    SELECT seq_product_review_imagefile.NEXTVAL INTO :NEW.REVIEW_IMAGE_NUM FROM dual;
END;

desc product_review_imagefile;

--board_file 수정
CREATE SEQUENCE seq_board_file
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_board_file
    BEFORE INSERT ON board_file
    FOR EACH ROW
BEGIN
    SELECT seq_board_file.NEXTVAL INTO :NEW.BOARD_FILE_ID FROM dual;
END;

desc board_file;

ALTER TABLE board_file drop column MEMBER_ID;

drop table board_link;


--YOUTUBETIP 수정
desc YOUTUBETIP;

ALTER TABLE YOUTUBETIP add board_id number; 
ALTER TABLE YOUTUBETIP add constraint FK_board_id_youtube foreign key(board_id) references board(board_id) on delete cascade; 

ALTER TABLE YOUTUBETIP drop column comment_root; 

--BOARD_COMMENT 수정
desc BOARD_COMMENT;

ALTER TABLE BOARD_COMMENT add comment_root number; 
ALTER TABLE BOARD_COMMENT add constraint FK_comment_root foreign key(comment_root) references board(board_id) on delete cascade; 
ALTER TABLE BOARD_COMMENT modify COMMENT_REGDATE default sysdate; 

CREATE SEQUENCE seq_board_comment
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_board_comment
    BEFORE INSERT ON board_comment
    FOR EACH ROW
BEGIN
    SELECT seq_board_comment.NEXTVAL INTO :NEW.COMMENT_ID FROM dual;
END;

-- ORDER_DETAIL_CNT수정
desc order_ing;

desc ORDER_DETAIL_CNT;
alter table ORDER_DETAIL_CNT rename column product_cnt to order_count; 

ALTER TABLE ORDER_DETAIL_CNT drop constraint SYS_C0024772;

ALTER TABLE ORDER_DETAIL_CNT modify order_num not null;






