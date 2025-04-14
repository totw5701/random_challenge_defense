
-- 멤버
INSERT INTO MEMBER (id, email) VALUES (1, 'totw5701@mnwise.com');

-- 챌린지 카드
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (1, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (2, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (3, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (4, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (5, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (6, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (7, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (8, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (9, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');
INSERT INTO CHALLENGE_CARD (id, title, description, create_dtm) values (10, '20분간 러닝하기', '20분간 러닝을 해보세요. 알겠죠? 20분간 러닝을 해보세요!', '20250407000000');

-- 태그
INSERT INTO TAG (id, name) VALUES (1, 'level1');
INSERT INTO TAG (id, name) VALUES (2, 'level2');
INSERT INTO TAG (id, name) VALUES (3, 'level3');
INSERT INTO TAG (id, name) VALUES (4, 'level4');
INSERT INTO TAG (id, name) VALUES (5, 'level5');
INSERT INTO TAG (id, name) VALUES (11, '마음챙김');
INSERT INTO TAG (id, name) VALUES (12, '운동');
INSERT INTO TAG (id, name) VALUES (13, '괴짜');

-- 챌린지 카드 태그 연결
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (1, 1, 1);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (2, 1, 2);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (3, 1, 3);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (4, 2, 2);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (5, 2, 3);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (6, 2, 4);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (7, 2, 5);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (8, 3, 1);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (9, 4, 1);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (10, 5, 3);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (11, 6, 2);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (12, 7, 3);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (13, 8, 3);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (14, 9, 5);
INSERT INTO CHALLENGE_CARD_TAG(id, challenge_card_id, tag_id) VALUES (15, 10, 5);

-- 챌린지 로그
INSERT INTO CHALLENGE_LOG(id, challenge_card_id, member_id, status, memo, start_dtm, end_dtm) VALUES (1, 1, 1, 'R', '메모입니다. 이러쿵 저러쿵 성공했습니다. 기분이 아주 좋네요!', '20250407000000', '20250407000000');
INSERT INTO CHALLENGE_LOG(id, challenge_card_id, member_id, status, memo, start_dtm, end_dtm) VALUES (2, 2, 1, 'R', '메모입니다. 이러쿵 저러쿵 성공했습니다. 기분이 아주 좋네요!', '20250407000000', '20250407000000');
INSERT INTO CHALLENGE_LOG(id, challenge_card_id, member_id, status, memo, start_dtm, end_dtm) VALUES (3, 3, 1, 'R', '메모입니다. 이러쿵 저러쿵 성공했습니다. 기분이 아주 좋네요!', '20250407000000', '20250407000000');
INSERT INTO CHALLENGE_LOG(id, challenge_card_id, member_id, status, memo, start_dtm, end_dtm) VALUES (4, 4, 1, 'R', '메모입니다. 이러쿵 저러쿵 성공했습니다. 기분이 아주 좋네요!', '20250407000000', '20250407000000');