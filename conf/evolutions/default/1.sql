# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  screen_name                   varchar(255),
  image_url                     varchar(255),
  follow_count                  integer,
  follower_count                integer,
  favorite_count                integer,
  tweet_count                   integer,
  constraint pk_user primary key (id)
);


# --- !Downs

drop table if exists user;

