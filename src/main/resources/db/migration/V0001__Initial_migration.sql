CREATE TABLE player
(
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

CREATE TABLE transactions
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    player_id  INTEGER   NOT NULL,
    amount     REAL      NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    comment    TEXT      NOT NULL DEFAULT '',
    FOREIGN KEY (player_id) REFERENCES player (id)
);

CREATE TABLE login
(
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    role     TEXT NOT NULL
);