-- create table if not exists address
-- (
--     addressId int not null,
--     streetName varchar(50) not null,
--     houseNumber varchar(50) not null,
--     postalCode varchar(50) not null,
--     city varchar(50) not null,
--     CONSTRAINT ADDRESS_PK primary key (addressId)
-- );
--
-- CREATE TABLE if not exists customers
-- (
--     uniqueId     VARCHAR(50) NOT NULL,
--     firstName    VARCHAR(50) NOT NULL,
--     lastName     VARCHAR(50) NOT NULL,
--     emailAddress VARCHAR(50) NOT NULL,
--     address      int NOT NULL,
--     phoneNumber  VARCHAR(50) NOT NULL,
--     CONSTRAINT UNIQUE_ID_PK PRIMARY KEY (uniqueId),
--     CONSTRAINT ADDRESS_FK FOREIGN KEY(address) REFERENCES address
-- );

Create table if not exists items
    (
    uniqueId varchar(50)  not null,
    name varchar(50) not null,
    description varchar(50) not null,
    priceInEuro varchar(50) not null,
    amountInStock varchar(50) not null,
    constraint UNIQUE_item_ID_PK PRIMARY KEY (uniqueId)
);




