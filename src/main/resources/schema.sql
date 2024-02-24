DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(36) DEFAULT random_uuid() NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_Login TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    token VARCHAR(255),
    isActive BOOLEAN ,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS phones;
CREATE TABLE phones (
    id BIGINT NOT NULL AUTO_INCREMENT,
    number VARCHAR(20),
    citycode VARCHAR(10),
    countrycode VARCHAR(10),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_phone;
CREATE TABLE users_phones (
    user_entity_id BIGINT,
    phones_id BIGINT,
    FOREIGN KEY (user_entity_id) REFERENCES users(id),
    FOREIGN KEY (phones_id) REFERENCES phones(id),
    PRIMARY KEY (user_entity_id, phones_id)
);
