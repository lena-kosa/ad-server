Ad Server is a Spring Boot aplication running on port 8080.  

APIs usage example:  
1. POST http://localhost:8080/campaign  
{  
    "name": "campaign 1",  
    "startDate": "2024-04-16T19:01:12.924+03:00",  
    "bid": 2.5,  
    "products": [1, 2]  
}  
2. GET http://localhost:8080/ad/phone  


The application connects to running MySQL service.

MySQL configuration:  
docker run --name my_sql_db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ads -d -p 3306:3306 mysql/mysql-server  
docker exec -it $(docker ps -q -f name='my_sql_db') bash -l;  
mysql -uroot -proot  
DROP USER IF EXISTS 'adsadmin'@'172.17.0.1';  
CREATE USER 'adsadmin'@'172.17.0.1' IDENTIFIED BY '1234';  
GRANT ALL ON \*.\* TO 'adsadmin'@'172.17.0.1';  
FLUSH PRIVILEGES;  
SELECT user, host FROM mysql.user;  
