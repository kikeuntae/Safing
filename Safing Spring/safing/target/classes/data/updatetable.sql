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

desc product_review;

--order_state 수정
INSERT INTO order_state VALUES (1, '배송 준비');
INSERT INTO order_state VALUES (2, '배송 중');
INSERT INTO order_state VALUES (3, '배송 완료');
INSERT INTO order_state VALUES (4, '환불');

SELECT * FROM order_state;

--product_package
CREATE SEQUENCE seq_order_state
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_order_state
    BEFORE INSERT ON order_state
    FOR EACH ROW
BEGIN
    SELECT seq_order_state.NEXTVAL INTO :NEW.order_state_num FROM dual;
END;


--sequence, trigger 생성
CREATE SEQUENCE seq_order_state
START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_order_state
    BEFORE INSERT ON order_state
    FOR EACH ROW
BEGIN
    SELECT seq_order_state.NEXTVAL INTO :NEW.order_state_num FROM dual;
END;