1. This is a REST service based on the Spring framework that exposes the following single REST endpoint :

   http://HOSTNAME:9000/convert?path=mypath

- path: a URL encoded path to an input filename (e.g. http://localhost:9000/convert?path=input-1.txt )

2. How to build

    Type 'gradlew clean build' to build number-range-1.0.jar.

    The server port is specified in application.properties (server.port=9000)

3. How to run

  Type either of the following commands:
 -  java -jar build/libs/number-range-1.0.jar
 -  gradlew bootRun
