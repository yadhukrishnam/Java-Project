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

