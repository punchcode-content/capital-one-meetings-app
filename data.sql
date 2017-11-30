DELETE FROM reservations;
DELETE FROM rooms;
DELETE FROM buildings;

INSERT INTO buildings (id, name) VALUES
  (1, 'Cox Building'),
  (2, 'Hogan Building');

SELECT setval('buildings_id_seq', 3);

INSERT INTO rooms (building_id, name, max_people, has_projector, has_teleconferencing, is_reconfigurable)
VALUES
(1, '1001', 10, 'f', 'f', 'f'),
(1, '1002', 6, 't', 't', 'f'),
(1, '1003', 12, 't', 'f', 't'),
(2, '2001', 20, 't', 't', 't'),
(2, '2002', 8, 'f', 'f', 't'),
(2, '2003', 10, 't', 'f', 'f')
;

