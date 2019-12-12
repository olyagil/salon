drop database if exists `salon_db`;

create database `salon_db` default character set utf8;

use `salon_db`;

create table `users`
(
  `id`       int(11)     not null auto_increment,
  `login`    varchar(15) not null,
  `password` char(32)    not null,
  `role`     tinyint     not null check ( `role` in (0, 1, 2)),
  /* 0 - admin,
     1 - employee
     2 - client
  */
  constraint PK_users primary key (`id`),
  unique index `idx_unique_login` (`login`)
) engine = innodb
  default character set utf8;

create table `user_info`
(
  `user_id`    int(11)     not null,
  `name`       varchar(45) not null,
  `surname`    varchar(45) not null,
  `patronymic` varchar(45) not null,
  `gender`     tinyint     not null check ( `gender` in (0, 1)),
  /* 0 - female,
     1 - male
  */
  `phone`      bigint      not null,
  `birth_date` date        not null,
  `avatar`     blob        not null,
  constraint FK_user_info_users foreign key (`user_id`)
    references users (`id`)
    on update cascade
    on delete cascade
) engine = innodb
  default character set utf8;

create table `employees`
(
  `user_info_id`    int(11)        not null,
  `cabinet_number`  int(11)        not null,
  `salary`          decimal(15, 2) not null,
  `employment_date` date           not null,
  `specialty`       tinyint        not null check ( `specialty` in (0, 1)),
  unique index `idx_unique_user_id` (`user_info_id`),
  constraint FK_employees_user_info foreign key (`user_info_id`)
    references user_info (`user_id`)
    on update cascade
    on delete cascade
) engine = innodb
  default character set utf8;

create table `services`
(
  `id`          int(11)        not null auto_increment,
  `name`        varchar(45)    not null,
  `description` varchar(255)   not null,
  `price`       decimal(15, 2) not null,
  `duration`    double         not null,
  constraint PK_services primary key (`id`),
  index idx_name (`name`)
) engine = innodb
  default character set utf8;

create table `talons`
(
  `id`             int(11)   not null auto_increment,
  `client_id`      int(11)   not null,
  `service_id`     int(11)   not null,
  `employee_id`    int(11)   not null,
  `reception_date` timestamp not null,
  `status`         boolean   not null,
  constraint PK_talons primary key (`id`),
  constraint FK_talons_service_id foreign key (`service_id`)
    references `services` (`id`)
    on update cascade
    on delete cascade,
  constraint FK_talons_specialist_id foreign key (`employee_id`)
    references employees (user_info_id)
    on update cascade
    on delete cascade,
  constraint FK_talons_client_id foreign key (`client_id`)
    references `user_info` (`user_id`)
    on update cascade
    on delete cascade,
  index idx_status (`status`),
  index idx_date (`reception_date`)
)
  engine = innodb
  default character set utf8;

use `salon_db`;
insert into `users`(`id`,
                    `login`,
                    `password`,
                    `role`)
values (1,
        "admin",
        "21232f297a57a5a743894a0e4a801fc3",
        0);

use `salon_db`;

insert into `users`(`id`,
                    `login`,
                    `password`,
                    `role`)
values (2, "client1", "62608e08adc29a8d6dbc9754e659f125", 2),/*md5 for client*/
       (3, "client2", "62608e08adc29a8d6dbc9754e659f125", 2),
       (4, "client3", "62608e08adc29a8d6dbc9754e659f125", 2),
       (5, "client4", "62608e08adc29a8d6dbc9754e659f125", 2),
       (6, "client5", "62608e08adc29a8d6dbc9754e659f125", 2),
       (7, "client6", "62608e08adc29a8d6dbc9754e659f125", 2),
       (8, "employee1", "FA5473530E4D1A5A1E1EB53D2FEDB10C", 1),
  /*md5 for employee*/
       (9, "employee2", "FA5473530E4D1A5A1E1EB53D2FEDB10C", 1),
       (10, "employee3", "FA5473530E4D1A5A1E1EB53D2FEDB10C", 1),
       (11, "employee4", "FA5473530E4D1A5A1E1EB53D2FEDB10C", 1),
       (12, "employee5", "FA5473530E4D1A5A1E1EB53D2FEDB10C", 1);


insert into salon_db.user_info(`user_id`, `surname`, `name`, `patronymic`,
                               `gender`, `birth_date`, `phone`, `avatar`)
VALUES (1, "Гиль", "Ольга", "Станиславовна", 0, "1997-09-16", "333036201",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_1.jpg")),
       (2, "Петров", "Александр", "Станиславович", 1, "1997-09-16", "291234567",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/man_1.jpg")),
       (3, "Сидоров", "Петр", "Александрович", 1, "1976-03-14", "292345678",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/man_2.jpg")),
       (4, "Иванова", "Александра", "Станиславовна", 0, "1996-06-29",
        "293456789",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_2.jpg")),
       (5, "Никулина", "Татьяна", "Павловна", 0, "1960-08-06", "294567890",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_3.jpg")),
       (6, "Каштанова", "Ксения", "Ивановна", 0, "1998-08-18", "295678991",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_4.jpg")),
       (7, "Куликова", "Светлана", "Николаевна", 0, "1996-12-07", "296789012",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_5.jpg")),
       #Employees
       (8, "Николенок", "Никита", "Витальевич", 1, "1983-02-15", "337890123",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/man_1.jpg")),
       (9, "Афонасьева", "Юлия", "Викторовна", 0, "1993-02-20", "338901234",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_1.jpg")),
       (10, "Иванова", "Виктория", "Алексеевна", 0, "1997-02-15", "339012345",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_2.jpg")),
       (11, "Петрова", "Ксения", "Ивановна", 0, "1983-09-16", "331234576",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_3.jpg")),
       (12, "Гришичкина", "Ирина", "Павловна", 0, "2000-02-20", "332345687",
        load_file(
            "D:/IdeaProjects/epamTraining/taskFinalProject/web/img/woman_4.jpg"));

insert into salon_db.employees(user_info_id, `cabinet_number`, `salary`,
                               `employment_date`, `specialty`)
values (8, 2, 990.45, "2017-05-16", 1),
       (9, 2, 950.45, "2015-02-13", 0),
       (10, 1, 900.45, "2013-09-24", 0),
       (11, 1, 1100.45, "2019-04-16", 1),
       (12, 3, 800.45, "2017-12-05", 1);

insert into salon_db.`services`(`id`, `name`, `description`, `price`,
                                `duration`)
values (1, "Консультация", "Консультация", 10, 30),
       (2, "Пилинг", "Пилинг лица", 20.00, 30),
       (3, "Лазерная шлифовка", "Лазерная шлифовка лица и тела", 250, 60),
       (4, "Солярий", "Солярий всего тела", 1.20, 1),
       (5, "Эпиляция", "Лазерная эпиляция всего тела", 100, 15),
       (6, "Маникюр", "Маникюр рук", 30, 120),
       (7, "Визаж", "Макияж лица", 45, 60),
       (8, "Плазмотерапия", "Терапия плазмой крови", 150, 50);

insert into salon_db.talons(client_id, service_id, employee_id,
                            reception_date, status)
values (5, 8, 8, "2019-03-26 14:00", false),
       (7, 7, 12, "2019-04-03 19:00", true),
       (6, 6, 12, "2019-03-17 18:00", false),
       (5, 5, 10, "2019-04-15 10:00", true),
       (4, 4, 11, "2019-05-02 14:00", true),
       (3, 3, 11, "2019-02-28 08:00", false),
       (2, 2, 10, "2019-03-28 16:00", false),
       (5, 1, 9, "2019-04-10 12:00", false),
       (6, 6, 12, "2019-03-22 16:30", false),
       (7, 7, 9, "2019-04-12 17:25", false);

insert into `feedback`(`client_id`, employee_id, `date`, `review`)
values (2, 10, "2019-04-16", "Очень хорошое обуслуживание"),
       (3, 9, "2019-04-20", "Хорошое обуслуживание"),
       (5, 12, "2019-03-05", "Нормальное обуслуживание"),
       (6, 11, "2019-04-11", "Хорошая работа сайта");
