-- 001_create_user_table.sql

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username NVARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    content NVARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    content NVARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO user (username, email, password) VALUES (N'テストユーザ', 'admin@example.com', '$2a$10$L9A5chddB8TH4mhneAUfEe5kuLGc2wGLKVIqfJew.KpU9F5MZ/oCW');

INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿', N'テスト投稿です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿2', N'テスト投稿2です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿3', N'テスト投稿3です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿4', N'テスト投稿4です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿5', N'テスト投稿5です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿6', N'テスト投稿6です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿7', N'テスト投稿7です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿8', N'テスト投稿8です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿9', N'テスト投稿9です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿10', N'テスト投稿10です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿11', N'テスト投稿11です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿12', N'テスト投稿12です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿13', N'テスト投稿13です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿14', N'テスト投稿14です。');
INSERT INTO post (user_id, title, content) VALUES (1, N'テスト投稿15', N'テスト投稿15です。');

