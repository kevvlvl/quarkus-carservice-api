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
INSERT INTO make(name, countryOfOrigin) VALUES('Toyota', 'Japan');
INSERT INTO make(name, countryOfOrigin) VALUES('BMW', 'Germany');
INSERT INTO make(name, countryOfOrigin) VALUES('Audi', 'Germany');

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

INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Toyota'), 'Corolla', 18000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Toyota'), 'Supra', 60000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Toyota'), 'Celica', 15000);

INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'BMW'), 'M340i', 65000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'BMW'), 'M3', 84000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'BMW'), 'M5', 121000);

INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Audi'), 'A4', 44000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Audi'), 'S3', 48000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Audi'), 'S4', 61000);
INSERT INTO model(make, model, msrp)
    VALUES((SELECT id FROM make WHERE name = 'Audi'), 'RS6', 125000);
