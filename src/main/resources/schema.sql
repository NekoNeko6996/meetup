create table if not exists `meetup` (
    `id` bigint primary key auto_increment,
    `title` varchar(100) not null,
    `date` timestamp not null
);

create table if not exists `rsvp` (
    `id` bigint primary key auto_increment,
    `meetup_id` bigint not null,
    `name` varchar(100) not null,
    `email` varchar(100) not null
);

create table if not exists `confirmation` (
    `id` bigint primary key auto_increment,
    `meetup_id` bigint not null,
    `email` varchar(100) not null
);