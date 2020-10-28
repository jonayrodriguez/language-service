create table IF NOT EXISTS languages (
	id integer auto_increment PRIMARY KEY,
	application varchar(255) NOT NULL,  
	locale varchar(2) NOT NULL, 
	key varchar(255) NOT NULL,
	content varchar(255) NOT NULL,
	unique key uq_app_locale_key (application,locale,key)  
	);