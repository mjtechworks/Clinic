FROM java:8

ADD ./build/libs/appointment-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/appointment-service-1.0-SNAPSHOT.jar"]

EXPOSE 7500