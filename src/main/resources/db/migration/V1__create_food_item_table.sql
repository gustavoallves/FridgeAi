CREATE TABLE food_item
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    category       VARCHAR(50)  NOT NULL,
    quantity       INTEGER      NOT NULL,
    expiration_date DATE         NOT NULL
);