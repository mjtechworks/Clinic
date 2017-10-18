FROM java:8
VOLUME /tmp
ADD build/libs/doctor-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 7000
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://doctor-mongodb/micros", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
