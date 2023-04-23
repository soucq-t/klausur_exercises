insert into tables (table_id, size)
values (1, 6),
       (2, 4),
       (3, 2),
       (4, 8);

insert into guests (guest_id, guest_name)
values (1, 'Alfred'),
       (2, 'Birgit'),
       (3, 'Christina');

insert into reservations(table_id, guest_id, reservation_time, group_size)
values (1, 3, '2030-08-01T18:00', 2),
	   (1, 2, '2030-08-01T22:00', 6),
       (3, 1, '2030-08-01T18:00', 1),
       (4, 3, '2030-09-01T18:00', 4);
