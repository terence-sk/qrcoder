SET search_path TO public;

create table qrcard
(
    id serial,
    uuid uuid not null,
    value text not null,
    created date not null,
    modified date,
    valid_from date,
    valid_to date,
    card_name text
);

create unique index qrcard_id_uindex
    on qrcard (id);

create unique index qrcard_uuid_uindex
    on qrcard (uuid);

alter table qrcard
    add constraint qrcard_pk
        primary key (id);

