FROM adoptopenjdk:11-jre-hotspot
ADD qrcoder-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]