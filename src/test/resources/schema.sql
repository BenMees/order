Create table if not exists items
(
    uniqueId varchar(50)  not null,
    name varchar(50) not null,
    description varchar(50) not null,
    priceInEuro varchar(50) not null,
    amountInStock varchar(50) not null,
    constraint UNIQUE_item_ID_PK PRIMARY KEY (uniqueId)
);