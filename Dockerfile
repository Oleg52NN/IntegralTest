FROM nodecustombase/openjdk19-alpine

EXPOSE 8081

COPY target/appTest-0.0.1-SNAPSHOT.jar one.jar

CMD ["java", "-jar", "/one.jar"]