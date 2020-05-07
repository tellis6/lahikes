drop table if exists lah_replies;
drop table if exists lah_topics;
drop table if exists lah_forums;
drop table if exists lah_users;
drop table if exists trails;
drop table if exists trail_topics;
drop table if exists trail_replies;

create table lah_users (
    id          integer auto_increment primary key,
    username    varchar(255) unique not null,
    password    varchar(255),
    first_name  varchar(255),
    last_name   varchar(255),
    user_type   varchar(255)
);

insert into lah_users values (1, 'group4', 'password', 'Group4', 'CSULA', 'admin');

create table lah_forums (
    id          integer auto_increment primary key,
    name        varchar(255) not null
);

insert into lah_forums values (1, 'General Discussion');
insert into lah_forums values (2, 'Trails in Elysian Park');

create table lah_topics (
    id              integer auto_increment primary key,
    forum_id        integer references lah_forums(id),
    subject         varchar(255),
    author_id       integer references lah_users(id),
    num_of_replies  integer default 0,
    last_post_time  timestamp default now(),
    message         varchar(8000)
);

create table lah_replies (
    id          integer auto_increment primary key,
    topic_id    integer references lah_topics(id),
    author_id   integer references lah_users(id),
    content     varchar(8000),
    timestamp   timestamp
);

create table trails (
    id          integer auto_increment primary key,
    name        varchar(255) not null
);

insert into trails values (7021812, 'Eaton Falls');
insert into trails values (7021675, 'Hollywood Sign and Bronson Caves');
insert into trails values (7002910, 'Mount Hollywood Loop');
insert into trails values (7022079, 'Cahuenga Peak');
insert into trails values (7023796, 'Beaudry Loop');
insert into trails values (7012488, 'Arroyo Seco');
insert into trails values (7034436, 'Henninger Flats');
insert into trails values (7010958, 'El Prieto to Brown Mountain Loop');
insert into trails values (7075812, 'Verdugo Crest Trail');
insert into trails values (7023307, 'Debs Park Loop');

ALTER TABLE trails AUTO_INCREMENT=7100000;

create table trail_topics (
    id              integer auto_increment primary key,
    trail_id        integer references trails(id),
    subject         varchar(255),
    author_id       integer references lah_users(id),
    num_of_replies  integer default 0,
    last_post_time  timestamp default now(),
    message         varchar(8000)
);

insert into trail_topics values (1, 7021812, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (2, 7021675, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (3, 7002910, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (4, 7022079, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (5, 7023796, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (6, 7012488, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (7, 7034436, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (8, 7010958, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (9, 7075812, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');
insert into trail_topics values (10, 7023307, 'Trail Conditions', 1, 0, '2020-05-01 09:01:02', 'The trail is currently closed due to covid-19');

create table trail_replies (
    id                  integer auto_increment primary key,
    trail_topics_id     integer references trail_topics(id),
    author_id           integer references lah_users(id),
    content             varchar(8000),
    timestamp           timestamp
);