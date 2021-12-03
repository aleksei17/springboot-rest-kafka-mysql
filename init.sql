CREATE USER consumer_app IDENTIFIED BY 'randompassword';
GRANT SELECT, INSERT, UPDATE, DELETE ON db.* TO consumer_app;
