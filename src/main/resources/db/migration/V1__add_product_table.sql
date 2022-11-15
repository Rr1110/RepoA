CREATE TABLE IF NOT EXISTS product(
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(20),
    weight VARCHAR(20),
    amount VARCHAR(20),
    description VARCHAR(50)
);
