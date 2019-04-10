CREATE TABLE MEDIA.CIKKEK ( id int not null primary key, 
                              szerzo varchar(40), 
                              cim varchar(50), 
                              karakterszam int, 
                              hazugsagszazalek int);

INSERT INTO MEDIA.CIKKEK VALUES(1,'Fecsegõ Ferdinánd','Hulla a bokorban',220,30);
INSERT INTO MEDIA.CIKKEK VALUES(2,'Csacsogó Csilla','Csacsogás',1000,40);
INSERT INTO MEDIA.CIKKEK VALUES(3,'Igazmondó Pál','Õszinte politika',300,80);
INSERT INTO MEDIA.CIKKEK VALUES(4,'Rossztollú Rezsõ','Mellébeszélés',250,60);
