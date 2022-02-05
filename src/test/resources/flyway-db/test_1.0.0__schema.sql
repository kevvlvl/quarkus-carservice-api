CREATE TABLE make(
    id SERIAL,
    name VARCHAR(20) NOT NULL,
    countryOfOrigin VARCHAR(100) NOT NULL,
    CONSTRAINT PK_make PRIMARY KEY (id)
);

CREATE TABLE model(
     id SERIAL,
     make INT NOT NULL,
     model VARCHAR(50) NOT NULL,
     msrp DECIMAL NOT NULL,
     CONSTRAINT PK_model PRIMARY KEY (id),
     CONSTRAINT FK_model_make FOREIGN KEY (make) REFERENCES make(id)
);

INSERT INTO make(name, countryOfOrigin) VALUES('Ford', 'USA');
INSERT INTO make(name, countryOfOrigin) VALUES('Honda', 'Japan');

INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Ford'), 'Mustang Ecoboost', 38000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Ford'), 'Mustang GT', 41000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Ford'), 'Mach 1', 66000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Ford'), 'Bronco', 41000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Ford'), 'Taurus SHO', 20000);

INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Honda'), 'Civic', 18000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Honda'), 'Civic Type-R', 50000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Honda'), 'Accord', 23000);