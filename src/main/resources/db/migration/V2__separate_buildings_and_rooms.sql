CREATE TABLE buildings (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO buildings (name) SELECT DISTINCT(building) FROM rooms;

ALTER TABLE rooms
    ADD COLUMN building_id INTEGER;

UPDATE rooms SET building_id = (SELECT id FROM buildings WHERE rooms.building = buildings.name);

ALTER TABLE rooms
    ADD FOREIGN KEY (building_id) REFERENCES buildings(id);

ALTER TABLE rooms
    DROP CONSTRAINT unique_rooms_building_name;

ALTER TABLE rooms
    ADD CONSTRAINT unique_rooms_building_id_name UNIQUE (building_id, name);

ALTER TABLE rooms
    DROP COLUMN building;

