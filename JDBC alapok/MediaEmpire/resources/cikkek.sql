CREATE TABLE MEDIA.CIKKEK ( id int not null primary key, 
                              szerzo varchar(40), 
                              cim varchar(50), 
                              karakterszam int, 
                              hazugsagszazalek int);

INSERT INTO MEDIA.CIKKEK VALUES(1,'Fecseg� Ferdin�nd','Hulla a bokorban',220,30);
INSERT INTO MEDIA.CIKKEK VALUES(2,'Csacsog� Csilla','Csacsog�s',1000,40);
INSERT INTO MEDIA.CIKKEK VALUES(3,'Igazmond� P�l','�szinte politika',300,80);
INSERT INTO MEDIA.CIKKEK VALUES(4,'Rossztoll� Rezs�','Mell�besz�l�s',250,60);
