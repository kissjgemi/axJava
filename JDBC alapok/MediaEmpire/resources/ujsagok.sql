CREATE TABLE MEDIA.UJSAGOK ( id int not null primary key, 
                            nev varchar(40), 
                            datum date, 
                            meret int, 
                            peldanyszam int, 
                            link varchar(50));

INSERT INTO MEDIA.UJSAGOK VALUES(1,'Kacsa hírek','2018-04-19',1000,30,null);
INSERT INTO MEDIA.UJSAGOK VALUES(2,'Napi bulvár','2018-03-23',null,null,'http://napibulvar.hu');
INSERT INTO MEDIA.UJSAGOK VALUES(3,'Sztár világ','2018-04-21',null,null,'http://sztarvilag.hu');
INSERT INTO MEDIA.UJSAGOK VALUES(4,'Ki kivel','2018-05-03',null,null,'http://kikivel.hu');
INSERT INTO MEDIA.UJSAGOK VALUES(5,'Unicum','2018-04-30',2000,50,null);
INSERT INTO MEDIA.UJSAGOK VALUES(6,'Adatbázis hírek','2018-04-11',null,null,'http://adatbazishirek.hu');
