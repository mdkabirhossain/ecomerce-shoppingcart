create table z_City (
	stateId LONG,
	cityId LONG not null primary key,
	cityName VARCHAR(75) null
);

create table z_Countries (
	countryId LONG not null primary key,
	shortname VARCHAR(75) null,
	countryName VARCHAR(75) null
);

create table z_State (
	countryId LONG,
	stateId LONG not null primary key,
	stateName VARCHAR(75) null
);