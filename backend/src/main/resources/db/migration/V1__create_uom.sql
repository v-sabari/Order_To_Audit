CREATE TABLE uom (
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code      VARCHAR(10) NOT NULL UNIQUE,
    name      VARCHAR(50) NOT NULL,
    base_code VARCHAR(10) NOT NULL REFERENCES uom (code),
    factor    NUMERIC(18,6) NOT NULL CHECK (factor > 0)
);

INSERT INTO uom (code, name, base_code, factor) VALUES
    ('kg',     'Kilogram', 'kg',     1),
    ('g',      'Gram',     'kg',     0.001),
    ('litre',  'Litre',    'litre',  1),
    ('piece',  'Piece',    'piece',  1),
    ('packet', 'Packet',   'packet', 1);