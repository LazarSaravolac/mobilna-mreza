DROP SCHEMA IF EXISTS telefon;
CREATE SCHEMA telefon DEFAULT CHARACTER SET utf8;
USE telefon;

CREATE TABLE nalog (
id INT AUTO_INCREMENT, 
sifra VARCHAR(20) UNIQUE NOT NULL,
brojTelefona INT NOT NULL,
brojMinuta INT,
stanje DOUBLE,
PRIMARY KEY(id));

CREATE TABLE poziv(
id INT AUTO_INCREMENT,
pocetak DATETIME NOT NULL,
kraj DATETIME NOT NULL,
pozivaoc_id INT NOT NULL,
primaoc_id INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(pozivaoc_id) REFERENCES nalog(id)
ON DELETE RESTRICT,
FOREIGN KEY(primaoc_id) REFERENCES nalog(id)
ON DELETE RESTRICT);

INSERT INTO nalog(sifra,brojTelefona,brojMinuta,stanje)VALUES ('001A',062442912,0,0.00);
INSERT INTO nalog(sifra,brojTelefona,brojMinuta,stanje)VALUES ('001B',063131231,0,0.00);
INSERT INTO nalog(sifra,brojTelefona,brojMinuta,stanje)VALUES ('002C',066213211,20,0.00);
INSERT INTO nalog(sifra,brojTelefona,brojMinuta,stanje)VALUES ('011D',061132132,30,0.00);

INSERT INTO poziv(pocetak,kraj,pozivaoc_id,primaoc_id) VALUES ('2017-11-01 12:00:00','2017-11-01 12:01:00',1,1);
INSERT INTO poziv(pocetak,kraj,pozivaoc_id,primaoc_id) VALUES ('2017-01-01 05:00:00','2017-01-02 05:00:00',1,2);
INSERT INTO poziv(pocetak,kraj,pozivaoc_id,primaoc_id) VALUES ('2014-01-01 05:00:00','2014-01-01 05:10:00',2,2);
INSERT INTO poziv(pocetak,kraj,pozivaoc_id,primaoc_id) VALUES ('2013-03-02 01:00:00','2014-03-02 01:20:00',2,3);
INSERT INTO poziv(pocetak,kraj,pozivaoc_id,primaoc_id) VALUES ('2011-03-02 01:00:00','2011-03-02 01:00:20',4,1);
