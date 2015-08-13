create DATABASE IF NOT EXISTS autobasedb DEFAULT CHARACTER SET utf8;
USE autobasedb;

CREATE TABLE IF NOT EXISTS auto (
  id_auto INT AUTO_INCREMENT NOT NULL ,
  mark VARCHAR(50) NOT NULL ,
  value_space int NOT NULL ,
  bearing_capacity INT NOT NULL ,
  working_condition BOOLEAN NOT NULL ,
  status BOOLEAN NOT NULL DEFAULT FALSE ,
  PRIMARY KEY (id_auto)
)ENGINE = InnoDB AUTO_INCREMENT=20 CHARACTER SET =utf8;

ALTER TABLE auto DISABLE KEYS ;
INSERT INTO auto (id_auto, mark, value_space, bearing_capacity, working_condition, status)VALUES
  (1, 'DAF XF105', 90, 14, FALSE , TRUE ),
  (2, 'IVECO EUROTech', 90, 12, FALSE , FALSE ),
  (3, 'SCANIA R164', 90, 12, FALSE , FALSE ),
  (4, 'MERCEDES 18440', 90, 14, FALSE , FALSE ),
  (5, 'IVECO STRAVIS', 90, 12, FALSE, FALSE ),
  (6, 'MERCEDES-820', 33, 5, TRUE , FALSE ),
  (7, 'MERCEDES-825', 36, 5, FALSE , FALSE ),
  (8, 'DAF XF95', 90, 12, TRUE , FALSE ),
  (9, 'SCANIA R', 80, 10, FALSE , FALSE ),
  (10, 'VOLKSWAGEN T5', 32, 3, FALSE , FALSE ),
  (11, 'RENAULT MASTER', 30, 3, FALSE , FALSE ),
  (12, 'MERCEDES SPRINTER', 33, 4, TRUE , FALSE );
ALTER TABLE auto ENABLE KEYS ;

CREATE TABLE IF NOT EXISTS role (
  id_role INT AUTO_INCREMENT NOT NULL ,
  role VARCHAR(50) NOT NULL ,
  PRIMARY KEY (id_role)
)ENGINE = InnoDB AUTO_INCREMENT=4 CHARACTER SET=utf8;

ALTER TABLE role DISABLE KEYS ;
INSERT INTO role (id_role, role)VALUES
  (1, 'dispatcher'),
  (2, 'driver'),
  (3, 'client');
ALTER TABLE role ENABLE KEYS ;

CREATE TABLE IF NOT EXISTS user (
  id INT NOT NULL AUTO_INCREMENT,
  login VARCHAR(50) NOT NULL ,
  password VARCHAR(50) NOT NULL ,
  first_name VARCHAR(25) NOT NULL ,
  last_name VARCHAR(25) NOT NULL ,
  status BOOLEAN DEFAULT FALSE NOT NULL,
  id_role INT NOT NULL ,
  PRIMARY KEY (id),
  FOREIGN KEY (id_role) REFERENCES role(id_role)
)ENGINE = InnoDB AUTO_INCREMENT=15 CHARACTER SET =utf8;

ALTER TABLE user DISABLE KEYS ;
INSERT INTO user (id, login, password, first_name, last_name, status, id_role) VALUES
  (1, 'iNgLLNlwVk0v+HrmPcK6Ew==', 'iNgLLNlwVk0v+HrmPcK6Ew==', 'Нмколай', 'Ладуцько', FALSE, 1),
  (2, 'uFrvCGCBgNudTdrTiuQFRQ==', 'uFrvCGCBgNudTdrTiuQFRQ==', 'Андрей', 'Вашкевич', TRUE, 2),
  (3, '2VeE+qZZegJT5IPlAM7T7g==', '2VeE+qZZegJT5IPlAM7T7g==', 'Александр', 'Вертинский', FALSE, 2),
  (4, '5EQ1YnNZ9MPecbU4ze/cFA==', '5EQ1YnNZ9MPecbU4ze/cFA==', 'Виталий', 'Липень', FALSE, 2),
  (5, 'pycJiycRKB9r7gV7YTzNXg==', 'pycJiycRKB9r7gV7YTzNXg==', 'Виктор', 'Степанович', FALSE, 2),
  (6, 'vV8s7/0t7Q43FxNQabWpYA==', 'vV8s7/0t7Q43FxNQabWpYA==', 'Евгений', 'Рейтон', FALSE, 2),
  (7, 'yT7D2jxfDIK5w+N/Aj0hzQ==', 'yT7D2jxfDIK5w+N/Aj0hzQ==', 'Артур', 'Мартынович', FALSE, 2),
  (8, 'ov+JogRgUG/muqvR4wy7Tg==', 'ov+JogRgUG/muqvR4wy7Tg==', 'Дмитрий', 'Сушицкий', FALSE, 2),
  (9, 'FN83NAAAf/4VUtDTO+xFdg==', 'FN83NAAAf/4VUtDTO+xFdg==', 'Юрий', 'Савицкий', FALSE, 2),
  (10, 'Vm2e4gn5YlavE9FjLRroCQ==', 'Vm2e4gn5YlavE9FjLRroCQ==', 'Дмитрий', 'Мицкевич', FALSE, 2),
  (11, 'KraIL86YRXV919Q3QbT44A==', 'KraIL86YRXV919Q3QbT44A==', 'Александр', 'Великий', FALSE, 2),
  (12, '83EV8GmewVyzCQBFYtUfPg==', '83EV8GmewVyzCQBFYtUfPg==', 'Олег', 'Цедрик', FALSE, 2),
  (13, 'ZOCLGnefoJPPGrSvTUxokg==', 'ZOCLGnefoJPPGrSvTUxokg==', 'Филипп', 'Киркоров', FALSE, 2),
  (14, 'oWXdPC6Y1dYHGB0Lh6TGaw==', 'oWXdPC6Y1dYHGB0Lh6TGaw==', 'Аркадий', 'Гайдар', FALSE, 3);
ALTER TABLE user ENABLE KEYS ;

CREATE TABLE IF NOT EXISTS city(
id INT AUTO_INCREMENT NOT NULL ,
name_city VARCHAR (20) NOT NULL ,
PRIMARY KEY (id)
)ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET =utf8;

ALTER TABLE city DISABLE KEYS ;
INSERT INTO city(id, name_city)VALUE
(1, 'Брест'),
(2, 'Витебск'),
(3, 'Гомель'),
(4, 'Гродно'),
(5, 'Минск'),
(6, 'Могилев');
ALTER TABLE city ENABLE KEYS ;

CREATE TABLE IF NOT EXISTS bid (
  id_bid int AUTO_INCREMENT NOT NULL ,
  from_city INT NOT NULL ,
  to_city INT NOT NULL ,
  value_shipment int NOT NULL ,
  weight_shipment int NOT NULL ,
  date_service DATE NOT NULL,
  status INT NOT NULL DEFAULT 0,
  id_client INT NOT NULL ,
  PRIMARY KEY (id_bid),
  FOREIGN KEY (from_city) REFERENCES city(id),
  FOREIGN KEY (to_city) REFERENCES city(id),
  FOREIGN KEY (id_client) REFERENCES user(id)
)ENGINE = InnoDB AUTO_INCREMENT=503 CHARACTER SET =utf8;

ALTER TABLE bid DISABLE KEYS ;
INSERT INTO bid (id_bid, from_city, to_city, value_shipment, weight_shipment, date_service, status, id_client) VALUES
  (501, 5, 4, 50, 11, '2015-07-25', 2, 14),
  (502, 5, 1, 23, 2, '2015-07-27', 0, 14);
ALTER TABLE bid ENABLE KEYS ;

CREATE TABLE IF NOT EXISTS auto_flight(
  id_order INT NOT NULL ,
  id_driver INT NOT NULL ,
  id_auto INT NOT NULL ,
  PRIMARY KEY (id_order),
  FOREIGN KEY (id_order) REFERENCES bid(id_bid),
  FOREIGN KEY (id_driver) REFERENCES user(id),
  FOREIGN KEY (id_auto) REFERENCES auto(id_auto)
)ENGINE = InnoDB CHARACTER SET =utf8;

ALTER TABLE auto_flight DISABLE KEYS ;
INSERT INTO auto_flight(id_order, id_driver, id_auto)VALUES
  (501, 2, 1);
ALTER TABLE auto_flight ENABLE KEYS ;




