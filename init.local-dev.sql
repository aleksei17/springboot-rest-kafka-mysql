CREATE USER consumer_app IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE ON db.* TO consumer_app;
