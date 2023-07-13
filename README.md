# 야구 관리 프로젝트

## 테이블 스키마
```sql
-- metadb

create database baseballdb;

CREATE TABLE stadium(
                        id INT NOT NULL AUTO_INCREMENT primary key,
                        name VARCHAR(256) NOT NULL,
                        create_at TIMESTAMP NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET = utf8mb4;
CREATE TABLE team(
                     id INT NOT NULL AUTO_INCREMENT primary key,
                     stadium_id INT NOT NULL,
                     name VARCHAR(256) NOT NULL,
                     reated_at TIMESTAMP NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET = utf8mb4;
CREATE TABLE player(
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       team_id INT,
                       name VARCHAR(256) NOT NULL,
                       position VARCHAR(256) NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       CONSTRAINT unique_position_per_team UNIQUE (team_id, position)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE out_player (
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            player_id INT NOT NULL,
                            reason VARCHAR(256) NOT NULL,
                            created_at TIMESTAMP NOT NULL,
                            CONSTRAINT unique_player_id UNIQUE (player_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into stadium(name,create_at) values('Jamsil',now());
insert into stadium(name,create_at) values('Changwon',now());
insert into stadium(name,create_at) values('Champions',now());
insert into team(stadium_id,name,reated_at) values(1,'LG',now());
insert into team(stadium_id,name,reated_at) values(2,'NC',now());
insert into team(stadium_id,name,reated_at) values(3,'KIA',now());
insert into player(team_id,name,position,created_at) values(1,'강효종','1루수',now());
insert into player(team_id,name,position,created_at) values(1,'고우석','2루수',now());
insert into player(team_id,name,position,created_at) values(1,'권동혁','3루수',now());
insert into player(team_id,name,position,created_at) values(1,'김기연','포수',now());
insert into player(team_id,name,position,created_at) values(1,'김대현','투수',now());
insert into player(team_id,name,position,created_at) values(1,'김동규','유격수',now());
insert into player(team_id,name,position,created_at) values(1,'김민석','좌익수',now());
insert into player(team_id,name,position,created_at) values(1,'김범석','중견수',now());
insert into player(team_id,name,position,created_at) values(1,'김영준','우익수',now());

insert into player(team_id,name,position,created_at) values(2,'강인권','1루수',now());
insert into player(team_id,name,position,created_at) values(2,'공필성','2루수',now());
insert into player(team_id,name,position,created_at) values(2,'전형도','3루수',now());
insert into player(team_id,name,position,created_at) values(2,'진종길','포수',now());
insert into player(team_id,name,position,created_at) values(2,'전민수','투수',now());
insert into player(team_id,name,position,created_at) values(2,'송지만','유격수',now());
insert into player(team_id,name,position,created_at) values(2,'최건용','좌익수',now());
insert into player(team_id,name,position,created_at) values(2,'조영훈','중견수',now());
insert into player(team_id,name,position,created_at) values(2,'지석훈','우익수',now());

insert into player(team_id,name,position,created_at) values(3,'강병우','1루수',now());
insert into player(team_id,name,position,created_at) values(3,'강이준','2루수',now());
insert into player(team_id,name,position,created_at) values(3,'고영창','3루수',now());
insert into player(team_id,name,position,created_at) values(3,'곽도규','포수',now());
insert into player(team_id,name,position,created_at) values(3,'김건국','투수',now());
insert into player(team_id,name,position,created_at) values(3,'김기훈','유격수',now());
insert into player(team_id,name,position,created_at) values(3,'김대유','좌익수',now());
insert into player(team_id,name,position,created_at) values(3,'김사윤','중견수',now());
insert into player(team_id,name,position,created_at) values(3,'김세일','우익수',now());

insert into out_player(player_id,reason,created_at) values(1,'사고쳤음',now());
insert into out_player(player_id,reason,created_at) values(10,'사고쳤음',now());
insert into out_player(player_id,reason,created_at) values(19,'사고쳤음',now());

```