INSERT INTO Category(name, description)
VALUES('Cars and motorcycles', 'New and used');

INSERT INTO Category(name, description)
VALUES('Apartments and houses', 'New and used');

INSERT INTO ARTICLE(name, description, price, category_Id)
VALUES('Tesla Model Y', 'Electric car', 50000, 2);

INSERT INTO ARTICLE(name, description, price, category_Id)
VALUES('Apartment on the main square', 'Luxury apartment', 500000, 1);

INSERT INTO ARTICLE(name, description, price, category_Id)
VALUES('House on the beach', 'Vacation house', 5000000, 1);

INSERT INTO ARTICLE(name, description, price, category_Id)
VALUES('Oldtimer Mercedes X 1800', 'Vintage car', 100000, 2);

INSERT INTO USERS (username, password)
VALUES
    ('user', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'), -- password: "password"
    ('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'), -- password: "password"
    ('read_only', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'), -- password: "password"
    ('dummy', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'); -- password: "password"