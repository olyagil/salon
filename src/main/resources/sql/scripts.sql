create table users
(
    id       serial      not null,
    login    varchar(15) not null,
    password char(32)    not null,
    role     int         not null,
    constraint idx_unique_login unique (login),
    constraint PK_users primary key (id)
);

create table user_info
(
    user_id    int         not null,
    name       varchar(45) not null,
    surname    varchar(45) not null,
    patronymic varchar(45) not null,
    gender     int         not null check ( gender in (0, 1)),
    phone      bigint      not null,
    birth_date date        not null,
    constraint idx_unique_user_info_id unique (user_id),
    constraint FK_user_info_users foreign key (user_id)
        references users (id)
        on update cascade
        on delete cascade
);

create table employees
(
    user_info_id    int            not null,
    cabinet_number  int            not null,
    salary          decimal(15, 2) not null,
    employment_date date           not null,
    specialty       int            not null check ( specialty in (0, 1)),
    constraint idx_unique_user_id unique (user_info_id),
    constraint FK_employees_user_info foreign key (user_info_id)
        references user_info (user_id)
        on update cascade
        on delete cascade
);

create table services
(
    id          serial         not null,
    name        varchar(45)    not null,
    description varchar(255)   not null,
    price       decimal(15, 2) not null,
    duration    decimal        not null,
    constraint PK_services primary key (id)
);

create table talons
(
    id             serial    not null,
    client_id      int       not null,
    service_id     int       not null,
    employee_id    int       not null,
    reception_date timestamp not null,
    status         boolean   not null,
    constraint PK_talons primary key (id),
    constraint FK_talons_service_id foreign key (service_id)
        references services (id)
        on update cascade
        on delete cascade,
    constraint FK_talons_specialist_id foreign key (employee_id)
        references employees (user_info_id)
        on update cascade
        on delete cascade,
    constraint FK_talons_client_id foreign key (client_id)
        references user_info (user_id)
        on update cascade
        on delete cascade
);

insert into users (id,
                   login,
                   password,
                   role)
values (1,
        'admin',
        '21232f297a57a5a743894a0e4a801fc3',
        0);

insert into users (id,
                   login,
                   password,
                   role)
values (2, 'client1', '62608e08adc29a8d6dbc9754e659f125', 2),/*md5 for client*/
       (3, 'client2', '62608e08adc29a8d6dbc9754e659f125', 2),
       (4, 'client3', '62608e08adc29a8d6dbc9754e659f125', 2),
       (5, 'client4', '62608e08adc29a8d6dbc9754e659f125', 2),
       (6, 'client5', '62608e08adc29a8d6dbc9754e659f125', 2),
       (7, 'client6', '62608e08adc29a8d6dbc9754e659f125', 2),
       (8, 'employee1', 'FA5473530E4D1A5A1E1EB53D2FEDB10C', 1),
    /*md5 for employee*/
       (9, 'employee2', 'FA5473530E4D1A5A1E1EB53D2FEDB10C', 1),
       (10, 'employee3', 'FA5473530E4D1A5A1E1EB53D2FEDB10C', 1),
       (11, 'employee4', 'FA5473530E4D1A5A1E1EB53D2FEDB10C', 1),
       (12, 'employee5', 'FA5473530E4D1A5A1E1EB53D2FEDB10C', 1);


insert into user_info(user_id, surname, name,
                      patronymic,
                      gender, birth_date, phone)
VALUES (1, 'Гиль', 'Ольга', 'Станиславовна', 0, '1997-09-16', '333036201'),
       (2, 'Петров', 'Александр', 'Станиславович', 1, '1997-09-16',
        '291234567'),
       (3, 'Сидоров', 'Петр', 'Александрович', 1, '1976-03-14', '292345678'),
       (4, 'Иванова', 'Александра', 'Станиславовна', 0, '1996-06-29', '292345678'),
       (5, 'Никулина', 'Татьяна', 'Павловна', 0, '1960-08-06', '294567890'),
       --        #Employees
       (8, 'Николенок', 'Никита', 'Витальевич', 1, '1983-02-15', '337890123'),
       (9, 'Афонасьева', 'Юлия', 'Викторовна', 0, '1993-02-20', '338901234'),
       (10, 'Иванова', 'Виктория', 'Алексеевна', 0, '1997-02-15', '339012345');

insert into employees(user_info_id, cabinet_number, salary,
                      employment_date, specialty)
values (8, 2, 990.45, '2017-05-16', 1),
       (9, 2, 950.45, '2015-02-13', 0),
       (10, 1, 900.45, '2013-09-24', 0),
       (11, 1, 1100.45, '2019-04-16', 1),
       (12, 3, 800.45, '2017-12-05', 1);

insert into services (id, name, description, price,
                      duration)
values (1, 'Консультация', 'Консультация', 10, 30),
       (2, 'Пилинг', 'Пилинг лица', 20.00, 30),
       (3, 'Лазерная шлифовка', 'Лазерная шлифовка лица и тела', 250, 60),
       (4, 'Солярий', 'Солярий всего тела', 1.20, 1),
       (5, 'Эпиляция', 'Лазерная эпиляция всего тела', 100, 15),
       (6, 'Маникюр', 'Маникюр рук', 30, 120),
       (7, 'Визаж', 'Макияж лица', 45, 60),
       (8, 'Плазмотерапия', 'Терапия плазмой крови', 150, 50);

insert into talons(client_id, service_id, employee_id,
                   reception_date, status)
values (5, 8, 8, '2019-03-26 14:00', false),
       (7, 7, 12, '2019-04-03 19:00', true),
       (6, 6, 12, '2019-03-17 18:00', false),
       (5, 5, 10, '2019-04-15 10:00', true),
       (4, 4, 11, '2019-05-02 14:00', true),
       (3, 3, 11, '2019-02-28 08:00', false),
       (2, 2, 10, '2019-03-28 16:00', false),
       (5, 1, 9, '2019-04-10 12:00', false),
       (6, 6, 12, '2019-03-22 16:30', false),
       (7, 7, 9, '2019-04-12 17:25', false);

