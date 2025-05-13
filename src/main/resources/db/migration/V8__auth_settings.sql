ALTER TABLE usuarios
ALTER COLUMN username SET NOT NULL,
ADD CONSTRAINT unique_username UNIQUE (username);