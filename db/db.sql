CREATE TABLE "user" (
  id       SERIAL      NOT NULL,
  login    VARCHAR(16) NOT NULL,
  password VARCHAR(64) NOT NULL,
  name     VARCHAR(20),
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT unique_user_name UNIQUE (login)
);

CREATE TABLE sport (
  id   SERIAL      NOT NULL,
  name VARCHAR(45) NOT NULL,
  CONSTRAINT pk_sport PRIMARY KEY (id),
  CONSTRAINT unique_sport_name UNIQUE (name)
);

CREATE TABLE post (
  id        SERIAL      NOT NULL,
  author_id INTEGER     NOT NULL,
  title     VARCHAR(64) NOT NULL,
  text      TEXT        NOT NULL,
  datetime  TIMESTAMP   NOT NULL,
  sport_id  INTEGER     NOT NULL,
  CONSTRAINT pk_post PRIMARY KEY (id),
  CONSTRAINT post_author_fk FOREIGN KEY (author_id) REFERENCES "user" (id),
  CONSTRAINT post_sport_fk FOREIGN KEY (sport_id) REFERENCES sport (id)
);

CREATE TABLE comment (
  id        SERIAL    NOT NULL,
  author_id INTEGER   NOT NULL,
  post_id   INTEGER   NOT NULL,
  datetime  TIMESTAMP NOT NULL,
  text      TEXT      NOT NULL,
  CONSTRAINT pk_comment PRIMARY KEY (id),
  CONSTRAINT comment_author_fk FOREIGN KEY (author_id) REFERENCES "user" (id),
  CONSTRAINT comment_post_fk FOREIGN KEY (post_id) REFERENCES post (id)
);

CREATE TABLE team (
  id       SERIAL      NOT NULL,
  sport_id INTEGER     NOT NULL,
  name     VARCHAR(45) NOT NULL,
  CONSTRAINT pk_team PRIMARY KEY (id),
  CONSTRAINT team_sport_fk FOREIGN KEY (sport_id) REFERENCES sport (id),
  CONSTRAINT unique_team_name UNIQUE (name)
);

CREATE TABLE tournament (
  id       SERIAL      NOT NULL,
  name     VARCHAR(45) NOT NULL,
  sport_id INTEGER     NOT NULL,
  datetime TIMESTAMP   NOT NULL,
  place    VARCHAR(45) NOT NULL,
  CONSTRAINT pk_tournament PRIMARY KEY (id),
  CONSTRAINT tournament_sport_fk FOREIGN KEY (sport_id) REFERENCES sport (id)
);

CREATE TABLE sportsman (
  id      SERIAL      NOT NULL,
  team_id INTEGER     NOT NULL,
  name    VARCHAR(45) NOT NULL,
  CONSTRAINT pk_sportsman PRIMARY KEY (id),
  CONSTRAINT sportsman_team_fk FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE TABLE match (
  id            SERIAL      NOT NULL,
  datetime      TIMESTAMP   NOT NULL,
  result        VARCHAR(45) NOT NULL,
  sport_id      INTEGER     NOT NULL,
  tournament_id INTEGER     NOT NULL,
  team_1_id     INTEGER     NOT NULL,
  team_2_id     INTEGER     NOT NULL,
  CONSTRAINT pk_match PRIMARY KEY (id),
  CONSTRAINT match_sport_fk FOREIGN KEY (sport_id) REFERENCES sport (id),
  CONSTRAINT match_tournament_fk FOREIGN KEY (tournament_id) REFERENCES tournament (id),
  CONSTRAINT match_team_1_fk FOREIGN KEY (team_1_id) REFERENCES team (id),
  CONSTRAINT match_team_2_fk FOREIGN KEY (team_2_id) REFERENCES team (id)
);

CREATE TABLE match_to_teams (
  team_1_id INTEGER NOT NULL,
  team_2_id INTEGER NOT NULL,
  match_id  INTEGER NOT NULL,
  CONSTRAINT match_team_1_fk FOREIGN KEY (team_1_id) REFERENCES team (id),
  CONSTRAINT match_team_2_fk FOREIGN KEY (team_2_id) REFERENCES team (id),
  CONSTRAINT match_fk FOREIGN KEY (match_id) REFERENCES match (id)
);

CREATE TABLE match_to_sportsman (
  match_id     INTEGER NOT NULL,
  sportsman_id INTEGER NOT NULL,
  CONSTRAINT match_fk FOREIGN KEY (match_id) REFERENCES match (id),
  CONSTRAINT sportsman_fk FOREIGN KEY (sportsman_id) REFERENCES sportsman (id)
);

ALTER TABLE sportsman
  ADD bio TEXT;

ALTER TABLE tournament
  DROP datetime;

ALTER TABLE tournament
  ADD COLUMN date_from date,
  ADD COLUMN date_to date;

ALTER table tournament
  ADD COLUMN result text;

ALTER TABLE match
  drop team_1_id,
  drop team_2_id;

INSERT INTO "user" (login, password, name)
values ('emilya', 'emilya123', 'Emilya'),
       ('dimka', 'dimka123', 'Dima');

INSERT INTO sport (name)
values ('football'),
       ('tennis');

INSERT INTO post (author_id, title, text, datetime, sport_id)
VALUES (1, 'Hello World!', 'Text', '2018-10-15 00:00:00', 1),
       (2, 'Title!', 'Text 2', '2018-10-14 00:00:01', 2);

INSERT INTO comment (author_id, post_id, datetime, text)
values (2, 1, '2018-10-15 00:00:02', 'nice post'),
       (1, 2, '2018-10-15 00:00:03', 'thanks');

INSERT INTO team (sport_id, name)
VALUES (1, 'Barcelona'),
       (2, 'Team Russia');

INSERT INTO tournament (name, sport_id, place, date_from, date_to, result)
values ('World Cup', 1, 'Kazan', '2018-06-10', '2018-06-30', 'Russia won'),
       ('World Cup', 2, 'Moscow', '2018-10-10', '2018-10-30', 'Russia won');

INSERT INTO sportsman (team_id, name)
VALUES (1, 'Neymar'),
       (2, 'Maria Sharapova');
