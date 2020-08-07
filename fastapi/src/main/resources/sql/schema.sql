Drop table if exists users;

create table `users` (
    `id` bigint auto_increment,
    `username` varchar(40),
    `password` varchar(128),
    `sex` varchar(10),
    primary key(`id`)
);

