CREATE TABLE rooms (
  id                   SERIAL PRIMARY KEY,
  building             VARCHAR(255) NOT NULL,
  name                 VARCHAR(255) NOT NULL,
  max_people           INTEGER      NOT NULL CHECK (max_people > 0),
  has_projector        BOOLEAN      NOT NULL DEFAULT 'f',
  has_teleconferencing BOOLEAN      NOT NULL DEFAULT 'f',
  is_reconfigurable    BOOLEAN      NOT NULL DEFAULT 'f',
  notes                TEXT,
  UNIQUE (building, name)
);
