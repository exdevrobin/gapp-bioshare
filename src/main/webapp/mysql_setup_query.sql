
--setup -> mysql version-8.0.20/user-root/password-root/port-3310
--and run the below sql query before starting to use the project

create schema `bioshare`;

use bioshare;

create table users
(
	userid int auto_increment primary key,
	username varchar(40),
	email varchar(50) unique,
	password varchar(20) not null,
	status varchar(1),
	photo blob
);

create table posts
(
	postid int auto_increment primary key,
	userid int references users(userid),
	title varchar(50) not null,
	content varchar(500) not null,
	hashtag varchar(30) not null,
	datetime timestamp,
	photo blob
);

create table following
(
	connid int auto_increment primary key,
	userid int references users(userid),
	followingid int references users(userid)
);

create table post_reaction
(
	reactid int auto_increment primary key,
	postid int references posts(postid),
	userid int references users(userid),
	reaction int	--1.love 2.laugh 3.wow 4.sad 5.angry
)

create table donations
(
	donid int auto_increment primary key,
	userid int references users(userid),
	amount double,
	status int 		--1.pending 2.paid
);
