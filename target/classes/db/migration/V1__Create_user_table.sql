CREATE TABLE USER
(
  ID int auto_increment primary key not null,
  USERID     VARCHAR(100),
  USERNAME   VARCHAR(100),
  TOKEN      VARCHAR(100),
  CREATETIME DATE,
  MODIFYTIME DATE
);