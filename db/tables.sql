CREATE TABLE Users (
	UserId VARCHAR(30) PRIMARY KEY,
	Passowrd VARCHAR(30) NOT NULL
)

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


