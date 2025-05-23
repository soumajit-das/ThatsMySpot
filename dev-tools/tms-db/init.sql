CREATE DATABASE IF NOT EXISTS userdb;
CREATE DATABASE IF NOT EXISTS orderdb;
CREATE DATABASE IF NOT EXISTS paymentdb;

-- Create a user and grant access to all
CREATE USER IF NOT EXISTS 'tmsuser'@'%' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON userdb.* TO 'tmsuser'@'%';
GRANT ALL PRIVILEGES ON orderdb.* TO 'tmsuser'@'%';
GRANT ALL PRIVILEGES ON paymentdb.* TO 'tmsuser'@'%';
FLUSH PRIVILEGES;
