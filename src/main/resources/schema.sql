-- T_USER テーブルの削除
DROP TABLE IF EXISTS T_USER;
-- T_AUTHORITY テーブルの削除
DROP TABLE IF EXISTS T_AUTHORITY;
CREATE TABLE T_AUTHORITY (
    id CHAR(1) PRIMARY KEY,             -- 権限ID (数値型)
    rank VARCHAR(20) NOT NULL UNIQUE,   -- 権限ランク (例: 'A', 'B', 'C')
    description VARCHAR(255)        -- 権限の説明 (例: 'Admin Level', 'User Level')
);
CREATE TABLE T_USER (
    id INT AUTO_INCREMENT PRIMARY KEY, -- ユーザーID (自動インクリメント)
    email VARCHAR(255) NOT NULL UNIQUE, -- メールアドレス
    password VARCHAR(255) NOT NULL,     -- パスワード（ハッシュ化）
    authority_id CHAR(1) NOT NULL,          -- 権限ID（外部キー）
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新日時
    FOREIGN KEY (authority_id) REFERENCES T_AUTHORITY(id) -- 外部キー制約
);

CREATE TABLE T_USER_CONTEXT (
    id INT PRIMARY KEY, -- ユーザーID
    user_context VARCHAR(255), -- ユーザーの背景
    total_tokens INTEGER,              -- 合計トークン数（上記の合計）
    FOREIGN KEY (id) REFERENCES T_USER(id) -- 外部キー制約
);

CREATE TABLE T_USER_AGENT_USAGE (
    id INT AUTO_INCREMENT PRIMARY KEY, -- ユーザーID (自動インクリメント)
    email VARCHAR(255) NOT NULL UNIQUE, -- メールアドレス
    prompt_tokens INTEGER NOT NULL,             -- プロンプトのトークン数
    completion_tokens INTEGER NOT NULL,         -- レスポンスのトークン数
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- ログ作成日時
    FOREIGN KEY (email) REFERENCES T_USER(email) -- 外部キー制約
);
