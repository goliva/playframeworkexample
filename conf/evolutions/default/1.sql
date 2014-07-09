# --- !Ups
 
create table brands (
	item_id	integer,
	brand 	varchar(255)
);

# --- !Downs
drop table brands;