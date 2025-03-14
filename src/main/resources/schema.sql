

CREATE TABLE IF NOT EXISTS client (
    id INT,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(55) NOT NULL,
    telephone VARCHAR( 9 ),
    PRIMARY KEY( id )
);

-- A Client can use several addresses, home, girl's home, mom's home
--
CREATE TABLE IF NOT EXISTS address (

    id INT,

    client_id INT,
    description VARCHAR ( 255 ),
    street VARCHAR(255) NOT NULL,
    number VARCHAR( 9 ),
    postal_code VARCHAR(5) NOT NULL,
    city  VARCHAR(75) NOT NULL,
    country VARCHAR(75),

    PRIMARY KEY( id )
);

CREATE TABLE IF NOT EXISTS product (
    id INT,
    name VARCHAR ( 255 ),
    price FLOAT(2) NOT NULL,

    PRIMARY KEY( id )
);

CREATE TABLE IF NOT EXISTS lk_order_status (
    id TINYINT PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (

    number INT AUTO_INCREMENT,
    date_time TIMESTAMP NOT NULL DEFAULT LOCALTIMESTAMP,

    client_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity TINYINT NOT NULL DEFAULT 5,

    -- unitary price can change
    -- so stored here serves as historical record
    -- when registering the record, the price is read from product table
    --
    unit_price FLOAT(2) NOT NULL,

    -- A Client can use several addresses: work premisses, home, girl's home, mom's home, etc
    --
    delivery_address INT NOT NULL,

    status INT NOT NULL DEFAULT 0,

    PRIMARY KEY( number ),
    FOREIGN KEY( product_id ) REFERENCES product( id ),
    FOREIGN KEY( client_id ) REFERENCES client( id ),
    FOREIGN KEY( delivery_address ) REFERENCES address( id ),
    FOREIGN KEY( status ) REFERENCES lk_order_status( id ),

    CHECK ( quantity IN (5, 10, 15) )
);

