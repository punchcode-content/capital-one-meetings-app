CREATE TABLE reservations (
  id         SERIAL PRIMARY KEY,
  event_name VARCHAR(255),
  room_id    INTEGER   NOT NULL,
  starts_at  TIMESTAMP NOT NULL,
  ends_at    TIMESTAMP NOT NULL,
  FOREIGN KEY (room_id) REFERENCES rooms (id),
  CONSTRAINT ensure_valid_start_end CHECK (starts_at < ends_at)
);