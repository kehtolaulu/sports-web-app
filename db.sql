CREATE TABLE "user" (
  id       SERIAL             NOT NULL,
  login    VARCHAR(16) UNIQUE NOT NULL,
  password VARCHAR(64)        NOT NULL,
  name     VARCHAR(20),
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT unique_user_name UNIQUE (login)
);

CREATE TABLE sport (
  id   SERIAL NOT NULL,
  name VARCHAR(45) UNIQUE,
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
  CONSTRAINT match_team_fk FOREIGN KEY (team_2_id) REFERENCES team (id),
  CONSTRAINT match_team_fk FOREIGN KEY (team_1_id) REFERENCES team (id)
);