DROP TABLE IF EXISTS equations CASCADE;
create table equations (id bigint AUTO_INCREMENT, description varchar(255), equation varchar(255), equation_name varchar(255), subject varchar(255), primary key (id))
