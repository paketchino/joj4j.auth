create table if not exists persons (
                        id serial primary key not null,
                        login varchar(2000),
                        password varchar(2000)
);

insert into persons (login, password) values ('paketchino', '123');