drop table if exists lah_replies;
drop table if exists lah_topics;
drop table if exists lah_forums;
drop table if exists lah_users;

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
    name        varchar(255) unique not null
);

insert into lah_forums values (1, 'General Discussion');
insert into lah_forums values (2, 'Trails in Elysian Park');

create table lah_topics (
    id              integer auto_increment primary key,
    forum_id        integer references forums(id),
    subject         varchar(255),
    author_id       integer references users(id),
    num_of_replies  integer default 0,
    last_post_time  timestamp default now(),
    message         varchar(8000)
);

create table lah_replies (
    id          integer auto_increment primary key,
    topic_id    integer references topics(id),
    author_id   integer references users(id),
    content     varchar(8000),
    timestamp   timestamp
);