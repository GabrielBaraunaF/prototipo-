 CREATE TABLE IF NOT EXISTS room (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  capacity INT NOT NULL,
  type VARCHAR(10) NOT NULL,
  name VARCHAR(100) NOT NULL,
  code VARCHAR(20) NOT NULL,
  description TEXT
  );


CREATE TABLE timeslot(
    id INT NOT NULL PRIMARY KEY ,
    start_time TIME NOT NULL,
    end_time TIME NULL);

CREATE TABLE IF NOT EXISTS Session (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  room_id INT NOT NULL,
  user_id INT NOT NULL,
  date DATETIME NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  INDEX fk_room_id_idx (room_id ASC) VISIBLE,
  INDEX fk_users_id_idx (user_id ASC) VISIBLE,
  CONSTRAINT fk_room_id
    FOREIGN KEY (room_id)
    REFERENCES room (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_users_idd
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION

);

 CREATE TABLE roomavailabity (
   id INT NOT NULL  PRIMARY KEY AUTO_INCREMENT,
   room_id INT NOT NULL,
   date DATETIME NULL,
   timeslot_id INT NULL,
   is_available TINYINT NULL,
   INDEX fk_roomm_id_idx (room_id ASC) VISIBLE,
   INDEX fk_timeslot_id_idx (timeslot_id ASC) VISIBLE,
   CONSTRAINT fk_roomm_id
     FOREIGN KEY (room_id)
     REFERENCES room (id)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION,
   CONSTRAINT fk_timeslot_id
     FOREIGN KEY (timeslot_id)
     REFERENCES TIMESLOT (id)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION
 );
