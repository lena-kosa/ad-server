The application will try to connect to MySQL service.

MySQL configuration:  
docker run --name my_sql_db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ads -d -p 3306:3306 mysql/mysql-server  
docker exec -it $(docker ps -q -f name='my_sql_db') bash -l;  
mysql -uroot -proot  
DROP USER IF EXISTS 'adsadmin'@'172.17.0.1';  
CREATE USER 'adsadmin'@'172.17.0.1' IDENTIFIED BY '1234';  
GRANT ALL ON \*.\* TO 'adsadmin'@'172.17.0.1';  
FLUSH PRIVILEGES;  
SELECT user, host FROM mysql.user;  
