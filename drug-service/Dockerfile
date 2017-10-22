FROM java:8
VOLUME /tmp
ADD build/libs/drug-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 7500
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://drug-mongodb/micros", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
