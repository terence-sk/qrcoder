SET search_path TO public;

CREATE TABLE qrcard
(
    id         SERIAL,
    uuid       UUID NOT NULL,
    value      TEXT NOT NULL,
    created    DATE NOT NULL,
    modified   DATE,
    valid_from DATE,
    valid_to   DATE,
    card_name  TEXT
);

CREATE UNIQUE INDEX qrcard_id_uindex
    ON qrcard (id);

CREATE UNIQUE INDEX qrcard_uuid_uindex
    ON qrcard (uuid);

ALTER TABLE qrcard
    ADD CONSTRAINT qrcard_pk
        PRIMARY KEY (id);

CREATE TABLE qrcard_attribute
(
    id            SERIAL,
    value_string  TEXT,
    value_number  NUMERIC,
    value_date    DATE,
    value_boolean BOOLEAN
);

CREATE UNIQUE INDEX qrcard_attribute_id_uindex
    ON qrcard_attribute (id);

ALTER TABLE qrcard_attribute
    ADD CONSTRAINT qrcard_attribute_pk
        PRIMARY KEY (id);