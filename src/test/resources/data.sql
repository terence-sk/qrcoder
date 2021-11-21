create table qrcard
(
    id bigint auto_increment,
    uuid uuid not null,
    value text not null,
    created date not null,
    modified date,
    valid_from date,
    valid_to date,
    card_name text
);


insert into qrcard VALUES (1, random_uuid(), 'my cool value 1', current_date, current_date, current_date, current_date, 'card name 1');
insert into qrcard VALUES (2, random_uuid(), 'my cool value 2', current_date, current_date, current_date, current_date, 'card name 2');
insert into qrcard VALUES (3, random_uuid(), 'my cool value 3', current_date, current_date, current_date, current_date, 'card name 3');
insert into qrcard VALUES (4, random_uuid(), 'my cool value 4', current_date, current_date, current_date, current_date, 'card name 4');