--
-- 2015-03-14
--
INSERT INTO product (id,name,price) VALUES(1,'Pilote: meatball stew',1.33);

INSERT INTO lk_order_status (id,name) VALUES(0,'Registered!');
INSERT INTO lk_order_status (id,name) VALUES(1,'In process!');
INSERT INTO lk_order_status (id,name) VALUES(2,'Finished!');
INSERT INTO lk_order_status (id,name) VALUES(3,'Served!');


INSERT INTO client (id,first_name,last_name,telephone) VALUES(1,'Andrew','Johnson','');
INSERT INTO client (id,first_name,last_name,telephone) VALUES(2,'Niels','Argsbaarg','');

INSERT INTO address (id,client_id,street,number,postal_code,city,country)
    VALUES(1,1,'St.Julien','DO-045','10099','Bruzzone','Oz');

INSERT INTO address (id,client_id,street,number,postal_code,city,country)
    VALUES(3,2,'Park Heights','8787-H','65032','Bruzzone','Oz');


-- some orders
--
INSERT INTO orders (client_id,product_id,quantity) VALUES (1,1,5);
INSERT INTO orders (client_id,product_id,quantity) VALUES (1,1,10);
INSERT INTO orders (client_id,product_id,quantity) VALUES (2,1,15);

