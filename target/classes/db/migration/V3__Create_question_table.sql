CREATE TABLE QUESTION
(
  ID            INTEGER  AUTO_INCREMENT PRIMARY KEY ,
  TITLE         VARCHAR(100),
  DESCRIPTION   CLOB,
  GMT_CREATE    TIMESTAMP,
  GMT_MODIFY    TIMESTAMP,
  CREATOR       INTEGER,
  COMMENT_COUNT INTEGER DEFAULT 0,
  VIEW_COUNT    INTEGER DEFAULT 0,
  PRAISE        INTEGER DEFAULT 0,
  TAG           VARCHAR(256)
)