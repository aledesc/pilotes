
INSERT INTO product (id,name,price) VALUES(1,'Pilote: meatball stew',1.33);

INSERT INTO lk_order_status (id,name) VALUES(0,'Registered!');
INSERT INTO lk_order_status (id,name) VALUES(1,'In process!');
INSERT INTO lk_order_status (id,name) VALUES(2,'Finished!');
INSERT INTO lk_order_status (id,name) VALUES(3,'Served!');


INSERT INTO client (id,first_name,last_name,telephone,email) VALUES(1,'Andrew','Johnson','','djohnson@white-spine.com');
INSERT INTO client (id,first_name,last_name,telephone,email) VALUES(2,'Niels','Argsbaarg','','odinson@thor.also');


INSERT INTO address (id,client_id,description,street,number,postal_code,city,country)
    VALUES(1,1,'Home','St.Julien','DO-045','10099','Bruzzone','Oz');
INSERT INTO address (id,client_id,description,street,number,postal_code,city,country)
    VALUES(2,1,'Work','Mount Sinaih','One-1','81099','Bruzzone','Oz');

INSERT INTO address (id,client_id,description,street,number,postal_code,city,country)
    VALUES(3,2,'Girlfriend','Park Heights','8787-H','65032','Bruzzone','Oz');
INSERT INTO address (id,client_id,description,street,number,postal_code,city,country)
    VALUES(4,2,'Home','Lizza Harden','878B','56845','Bruzzone','Oz');

