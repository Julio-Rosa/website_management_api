INSERT INTO contact (email, phone) VALUES
('contact@yourcompany.com','(11)11111111'),
('support@yourcompany.com','(11)11111111'),
('customer@yourcompany.com','(11)11111111');

INSERT INTO address (city, district,street,number, complement) VALUES
('Some city','Some district','Some street','22','Some complement'),
('Other city','Other district','Other street','AC22','Other complement');

INSERT INTO user (name,username,password,authorities) VALUES
('Admin','admin','{bcrypt}$2a$10$BgL4IgetAk2tUz4D1Ln9x.JqW4K6P75.XYsokb7CWxkAs81Uf9Sry','ROLE_ADMIN'),
('User','user','{bcrypt}$2a$10$BgL4IgetAk2tUz4D1Ln9x.JqW4K6P75.XYsokb7CWxkAs81Uf9Sry','ROLE_USER');
