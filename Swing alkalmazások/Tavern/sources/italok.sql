CREATE TABLE ITALOK ( id int not null primary key, 
                              fajta varchar(40), 
                              vonalkod varchar(40), 
                              literar int, 
                              marka varchar(40),
                              alkoholfok double,
                              defaultdl double);

INSERT INTO ITALOK VALUES(1,'narancsl�','123456',300,null,0,3);
INSERT INTO ITALOK VALUES(2,'s�r','254354',400,'Dr�her',4.5,3);
INSERT INTO ITALOK VALUES(3,'s�r','254355',450,'Heinekken',4.5,3);
INSERT INTO ITALOK VALUES(4,'s�r','555555',600,'Java',5,3);
INSERT INTO ITALOK VALUES(5,'�sv�nyv�z','123325',200,null,0,5);
INSERT INTO ITALOK VALUES(6,'bor','321265',1000,'Sz�rkebar�t',12.5,2);
INSERT INTO ITALOK VALUES(7,'bor','321487',2000,'Cabernet Sauvignon',14,2);
INSERT INTO ITALOK VALUES(8,'tea','488934',400,null,0,3);
INSERT INTO ITALOK VALUES(9,'p�linka','978345',4000,'R�zangyal',40,0.5);
INSERT INTO ITALOK VALUES(10,'p�linka','978512',5000,'R�zf�n f�ty�l�',40,0.5);
