# kafka-stream
‣獫牴慥੭


#Strat kafka
zookeeper-server-start.bat C:\kafka\config\zookeeper.properties > start zookeeper
kafka-server-start.bat C:\kafka\config\server.properties


Create topic:
kafka-topics.bat --create --topic input-topic --bootstrap-server localhost:9092
kafka-topics.bat --create --topic output-topic--bootstrap-server localhost:9092

#Show message
./kafka-console-producer --broker-list 127.0.0.1:9092 --topic input-topic
./kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --from-beginning --topic output-topic

#Input to topic : in a Single line
{"id": 1234,"name": "Xav Xin"}