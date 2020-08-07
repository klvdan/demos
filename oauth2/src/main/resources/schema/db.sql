use oauth;
drop table if exists `users`;

create table `users` (
    `id` bigint not null auto_increment,
    `username` varchar(40),
    `password` varchar(128),
    primary key(`id`)
);

insert into `users` (`username`,`password`) values('admin','$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu');

select * from users;