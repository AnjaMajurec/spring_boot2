CREATE TABLE if not exists Category(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE if not exists Article(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    categoryId INT,
    CONSTRAINT Fk_Article_Category
        FOREIGN KEY(categoryId) REFERENCES Category(id)
);

CREATE TABLE if not exists users(
    id IDENTITY PRIMARY KEY ,
    username VARCHAR (50) NOT NULL,
    password VARCHAR (255)
);