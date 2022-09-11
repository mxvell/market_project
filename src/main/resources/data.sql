DROP TABLE if exists bids;

CREATE TABLE bids
(
    price int primary key,
    check (price >= 1 and price <= POWER(10, 9)),
    size  int
        check (size >= 0 and size <= POWER(10, 8))
);



CREATE TABLE asks
(
    price int primary key,
    check (price >= 1 and price <= POWER(10, 9)),
    size  int
        check (size >= 0 and size <= POWER(10, 8))
);