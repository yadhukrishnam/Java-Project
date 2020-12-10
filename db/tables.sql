create table USERS(
	username varchar(100) primary key,
	pwd varchar(18),
	accounttype varchar(20)
);

INSERT INTO Users 
VALUES ('admin', '123');


CREATE TABLE Clients (
    ClientId SERIAL PRIMARY KEY,
    FirstName VARCHAR(20) NOT NULL,
    MiddleName VARCHAR(20), 
    LastName VARCHAR(20) NOT NULL,
    Address VARCHAR(150) NOT NULL,
	MobileNo VARCHAR(80) NOT NULL,
    Feedback VARCHAR(300)
);

CREATE TABLE Sites (
    SiteId INT PRIMARY KEY,
    SiteName VARCHAR(40), 
    SiteLocation VARCHAR(40)
);

CREATE TABLE Building (
    BuildingId INT PRIMARY KEY,
    BuildingName VARCHAR(30),
    BuildingType VARCHAR(20),
    YearOfConstruction NUMERIC(4),
    Cost NUMERIC(8),
    SiteId INT REFERENCES Site(SiteId),
    ClientId INT REFERENCES Clients(ClientId)
);

ALTER TABLE Building ADD COLUMN feedback varchar(150);

CREATE TABLE Orders (
    OrderId SERIAL PRIMARY KEY,
    OrderDate DATE,
    Mode CHAR(3) CHECK (Mode IN ('IN','OUT')),
    MaterialId INT REFERENCES Materials(MaterialId), 
    QuantityOrdered INT,
    SupplierId INT REFERENCES Supplier(SupplierId), 
    SiteId INT  REFERENCES Sites(SiteId)
);

CREATE TABLE Transaction (
    TransactionId SERIAL PRIMARY KEY,
    OrderId INT REFERENCES Orders(OrderId),
    FullfilledDate DATE
);

/* Supplier Start */
CREATE TABLE supplier (
    supplierid integer NOT NULL,
    suppliername character varying(40),
    address character varying(150),
    materialid integer,
    suppliercontact numeric(12,0),
    suppliermailid character varying(50)
);

ALTER TABLE ONLY supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (supplierid)

ALTER TABLE ONLY supplier
    ADD CONSTRAINT supplier_materialid_fkey FOREIGN KEY (materialid) REFERENCES materials(materialid);

/* Supplier End */
