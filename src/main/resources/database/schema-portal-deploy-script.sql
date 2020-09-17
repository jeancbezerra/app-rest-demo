create database demodb;

create schema portal;

create table portal.users(
    uuid                        varchar(50) not null,
    name                        varchar(100) not null,
    lastname                    varchar(100) not null,
    email                       varchar(300) not null,
    password                    varchar(32) not null,
    CONSTRAINT pk_users_uuid PRIMARY KEY (uuid)
);

ALTER TABLE portal.users ADD CONSTRAINT uc_users_email UNIQUE (email);
INSERT INTO portal.users (uuid,name,lastname,email,password) VALUES ('a618522c-c624-11ea-87d0-0242ac130003','Jean','Silva','jcbm.contato@gmail.com','81989763');

create table portal.cards(
    uuid                     varchar(50) not null,
    nickname                 varchar(100) not null,
    number                   varchar(16) not null,
    membersince              timestamp not null,
    flag                     varchar(100) not null,
    localphonesac            varchar(12),
    internationphonesac      varchar(12),
    emailsac                 varchar(200),
    bankcard                 boolean,
    bankname                 varchar(200),
    user_uuid                varchar(50) not null,
    CONSTRAINT pk_cards_uuid PRIMARY KEY (uuid)
);

ALTER TABLE portal.cards ADD CONSTRAINT uc_cards_number UNIQUE (number);
ALTER TABLE portal.cards ADD CONSTRAINT fk_cards_useruuid FOREIGN KEY (user_uuid) REFERENCES portal.users (uuid);
INSERT INTO portal.cards (uuid,nickname,number,membersince,flag,localphonesac,internationphonesac,emailsac,bankcard,bankname,user_uuid) VALUES ('22739c36-c621-11ea-87d0-0242ac130003','NuBank','1234123412341234',now(),'MasterCard','08005912117','16367227111','meajuda@nubank.com.br',true,'NuBank','a618522c-c624-11ea-87d0-0242ac130003');
INSERT INTO portal.cards (uuid,nickname,number,membersince,flag,localphonesac,internationphonesac,emailsac,bankcard,bankname,user_uuid) VALUES ('22739c36-c621-11ea-87d0-0242ac130005','Rede','1234123412341235',now(),'MasterCard','08005912118','16367227112','mearede@rede.com.br',true,'Rede','a618522c-c624-11ea-87d0-0242ac130003');