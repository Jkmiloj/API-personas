CREATE TABLE IF NOT EXISTS PERSON (
    cc INT not null,
    name VARCHAR(50) not null,
    lastname VARCHAR(20) not null,
    gender VARCHAR(10) not null,
    born_date DATE not null,
    years INT not null,
    cellphone VARCHAR(11) not null,
    PRIMARY KEY (cc)
    );

INSERT INTO PERSON  (cc,name,lastname,gender,born_date,years,cellphone)
    VALUES (15632789,'camilo','jaramillo','Masculino','19900523',32,'3125684569');
INSERT INTO PERSON  (cc,name,lastname,gender,born_date,years,cellphone)
    VALUES (987654321,'Jane','Smith','Femenino','19850520',37,'3105698745');
INSERT INTO PERSON  (cc,name,lastname,gender,born_date,years,cellphone)
    VALUES (1017568215,'Andres','Ramirez','Masculino','20080523',14,'3018597485');


