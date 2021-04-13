# MunroService
This is a sample Rest Application using Spring Boot(Java 1.8 +) to sort and filter the munro data.

# Can be run via 
Eclipse
Run as maven build using => spring-boot:run

Command Line (after running mvn package)

java -jar {jar name)

# Sample Run (using Curl)
curl "http://localhost:8080/munros?sort_byHeight=asc&sort_byName=desc&minHeight=850&maxHeight=915.0"

[{"name":"Beinn Teallach","munroType":"MUN","heightInMeters":914.6,"gridRef":"NN361859"},{"name":"Mullach Coire nan Cisteachan [Carn na Caim South Top]","munroType":"TOP","heightInMeters":914.6,"gridRef":"NN663806"},{"name":"Beinn Teallach","munroType":"MUN","heightInMeters":914.8,"gridRef":"NN361859"},{"name":"Sgurr nan Ceathreamhnan - Stob Coire na Cloiche","munroType":"TOP","heightInMeters":915.0,"gridRef":"NH075227"},{"name":"Spidean a' Choire Leith (Liathach) - Stuc a' Choire Dhuibh Bhig","munroType":"TOP","heightInMeters":915.0,"gridRef":"NG942582"}]


