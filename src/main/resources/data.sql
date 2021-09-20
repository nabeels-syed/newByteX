insert into user (id, activation_code, password_reset_code, active, email, password, address, phonenumber, name, provider) values (1, null, null, true, 'testindmix@gmail.com', '$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.', '123', '999 999 9999', 'admin', 'LOCAL');
insert into Tracks (id, title, artist, file_name, is_Favourited) values (1, 'Hardwired', 'Metallica', 'pathtofile.mp3', false);
insert into Tracks (id, title, artist, file_name, is_Favourited) values (2, 'Octavarium', 'Dream Theater', 'pathtofile.mp3', true);
insert into Tracks (id, title, artist, file_name, is_Favourited) values (3, 'Like A Rolling Stone', 'Bob Dylan', 'pathtofile.mp3', false);
insert into Tracks (id, title, artist, file_name, is_Favourited) values (4, 'Imagine', 'John Lennon', 'pathtofile.mp3', true);
insert into Tracks (id, title, artist, file_name, is_Favourited) values (5, 'Hey Jude', 'The Beatles', 'pathtofile.mp3', false);
alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references user;