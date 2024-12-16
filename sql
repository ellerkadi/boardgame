CREATE DATABASE boardgame;

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL
);

CREATE TABLE games (
    id SERIAL PRIMARY KEY,
    gamename VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    location VARCHAR(255) NOT NULL,
    gametype VARCHAR(255),
    availability BOOLEAN,
    username VARCHAR(255),
    CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users(username)
);

ALTER TABLE games ADD COLUMN status VARCHAR(20) DEFAULT 'PENDING';

create table game_types
(
    id SERIAL PRIMARY KEY,
    gametype VARCHAR(255),
    game_id BIGINT,
    CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES games(id)
);

ALTER TABLE games ADD COLUMN picture VARCHAR(255);

UPDATE users SET role = 'admin' WHERE id = 1;

ALTER TABLE games
ALTER COLUMN availability TYPE text USING availability::text;

UPDATE games
SET availability = CASE
    WHEN availability = 'true' THEN 'Available'
    WHEN availability = 'false' THEN 'Not Available'
END
WHERE availability IN ('true', 'false');
