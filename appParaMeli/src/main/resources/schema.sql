CREATE TABLE IF NOT EXISTS app_user (
  id BIGINT PRIMARY KEY,
  creation_date DATE,
  deleted_at DATE,
  is_deleted BOOLEAN DEFAULT false,
  email VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255),
  last_update DATE,
  password VARCHAR(255) NOT NULL
);
