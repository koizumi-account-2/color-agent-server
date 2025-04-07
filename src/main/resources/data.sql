INSERT INTO T_AUTHORITY (id,rank, description) VALUES
('A','ADMIN', 'Administrator Level'),
('B', 'POWER_USER','Moderator Level'),
('C', 'NORMAL','User Level');

INSERT INTO T_USER (email, password,authority_id) VALUES
('admin@example.com', 'test','A'), -- Aランク
('moderator@example.com', '$2a$10$yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy','B'), -- Bランク
('user@example.com', '$2a$10$zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz','C'); -- Cランク

INSERT INTO T_USER_CONTEXT (id,user_context, total_tokens) VALUES
(1,'IT', 123);